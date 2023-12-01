import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v115.browser.Browser;
import org.openqa.selenium.devtools.v115.emulation.Emulation;
import org.openqa.selenium.devtools.v115.network.Network;
import org.openqa.selenium.devtools.v115.network.model.Request;
import org.openqa.selenium.devtools.v115.network.model.RequestId;
import org.openqa.selenium.devtools.v115.network.model.Response;
import org.openqa.selenium.devtools.v115.performance.Performance;
import org.openqa.selenium.devtools.v115.performance.model.Metric;
import org.testng.Assert;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class CDP{
    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();

        options.addArguments("--incognito -–disable-notifications");

        ChromeDriver driver=new ChromeDriver(options);
        DevTools devTool =driver.getDevTools();
        devTool.createSession();

        String chromeVersion = driver.getCapabilities().getCapability("goog:chromeOptions")
                .toString().split(":")[0];

        options.setExperimentalOption("debuggerAddress", chromeVersion + ":9222");

        devTool.send(Emulation.setDeviceMetricsOverride(

                500,

                600,

                50,

                true,

                Optional.empty(),

                Optional.empty(),

                Optional.empty(),

                Optional.empty(),

                Optional.empty(),

                Optional.empty(),

                Optional.empty(),

                Optional.empty(),

                Optional.empty()

        ));

       /* driver.executeCdpCommand("Emulation.setDeviceMetricsOverride", Map.of(

                "width", 500,

                "height", 600,

                "deviceScaleFactor", 50,

                "mobile", true

        ));*/
        //browser version
        Browser.GetVersionResponse browser = devTool.send(Browser.getVersion());
        System.out.println("Browser Version => "+browser.getProduct());
        System.out.println("User Agent => "+browser.getUserAgent());

        /*Map coordinates = Map.of(
                "latitude", 30.3079823,
                "longitude", -97.893803,
                "accuracy", 1
        );
        driver.executeCdpCommand("Emulation.setGeolocationOverride", coordinates);
        //Refresh if already on the page
        driver.navigate().refresh();*/





        devTool.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
        devTool.addListener(Network.requestWillBeSent(), requestConsumer ->{
            Request request = requestConsumer.getRequest();
            //  System.out.println(request.getUrl());
        });

        RequestId[] requestId = new RequestId[1];

        devTool.addListener(Network.responseReceived(), responseConsumer -> {
            Response response = responseConsumer.getResponse();
            requestId[0] = responseConsumer.getRequestId();
            if( response.getUrl().contains("ws_api.php?")) {
                System.out.println(response.getStatus() + " "+ response.getUrl());
                Integer value=200;
                Assert.assertEquals(value,response.getStatus());
                String responseBody = devTool.send(Network.getResponseBody(requestId[0] )).getBody();
                System.out.println(responseBody);
                System.out.println("End of response");
            }

        });

        devTool.send(Performance.enable(Optional.empty()));
        List<Metric> metricList = devTool.send(Performance.getMetrics());
        driver.get("https://www.weatherstack.com/");


        System.out.println("At the end");
        driver.quit();

        for (Metric m:metricList){
            System.out.println(m.getName() + " = " + m.getValue());
        }



    }
}



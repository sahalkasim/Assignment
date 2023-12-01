import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v115.browser.Browser;

public class DriverManager {
    public static void main(String[] args) {

        ChromeOptions options = new ChromeOptions();
        options.setBrowserVersion("118");


        ChromeDriver driver = new ChromeDriver(options);

        DevTools devTool =driver.getDevTools();
        devTool.createSession();
        Browser.GetVersionResponse browser = devTool.send(Browser.getVersion());
        System.out.println("Browser Version => "+browser.getProduct());

        driver.get("http://zero.webappsecurity.com/login.html");

    }
}

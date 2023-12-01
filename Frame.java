import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;

public class Frame {
    public static void main(String[] args) throws InterruptedException, IOException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
        WebElement frame = driver.findElement(By.id("courses-iframe"));
        driver.switchTo().frame(frame);
        WebElement element = driver.findElement(By.xpath("(//a[text()='JOIN NOW'])[1]"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,1700)", "");
        TakesScreenshot takesScreenshot =(TakesScreenshot) driver;
        File file=takesScreenshot.getScreenshotAs(OutputType.FILE);
        new SimpleDateFormat("yyy");
        FileUtils.copyFile(file,new File("./Screennshot/screenhot.png"));
//        Actions actions = new Actions(driver);
//        actions.click(element).perform();
//
//        element.click();

        //driver.quit();
    }
}

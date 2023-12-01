import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;

public class Wait {
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.get("https://extendsclass.com/text-compare.html");
        Actions actions = new Actions(driver);
        WebElement source = driver.findElement(By.xpath("//*[@id='dropZone']//div[5]//pre/span"));
        actions.keyDown(source, Keys.COMMAND).sendKeys("a").build().perform();

    }
}

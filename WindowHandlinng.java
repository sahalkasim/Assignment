import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Iterator;
import java.util.List;

public class WindowHandlinng {
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
        //driver.findElement(By.id("openwindow"));
        WebElement searchbox = driver.findElement(By.id("autocomplete"));
        searchbox.sendKeys("ind");
        Thread.sleep(3000);
        List<WebElement> dropdown = driver.findElements(By.xpath("//ul[@id='ui-id-1']/li/div"));
        for (WebElement element:dropdown
             ) {
            if(element.getText().equalsIgnoreCase("British Indian Ocean Territory")){

                element.click();
            }

        }
    }
}

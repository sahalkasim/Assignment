import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Methods {
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
        Thread.sleep(3000);
        WebElement option2 = driver.findElement(By.id("checkBoxOption2"));
        option2.click();
        Thread.sleep(2000);
        WebElement option1 = driver.findElement(By.id("checkBoxOption1"));
        option1.click();
        Thread.sleep(2000);
        WebElement option3 = driver.findElement(By.id("checkBoxOption3"));
        option3.click();

    }

}

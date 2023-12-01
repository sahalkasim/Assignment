import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.locators.RelativeLocator;

import java.util.concurrent.TimeUnit;


public class Relative {
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("http://zero.webappsecurity.com/login.html");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        WebElement LoginField = driver.findElement(RelativeLocator.RelativeBy.id("user_login"));

        WebElement passwordField = driver.findElement(RelativeLocator.with(By.tagName("input")).below(LoginField));

        WebElement loginText = driver.findElement(RelativeLocator.with(By.tagName("label")).toLeftOf(LoginField));

        WebElement checkBox = driver.findElement(RelativeLocator.with(By.tagName("input")).below(loginText));

        passwordField.sendKeys("password");

        checkBox.click();

        System.out.println(loginText.getText());

        WebElement link = driver.findElement(RelativeLocator.with(By.tagName("a")).below(LoginField));

        link.click();

        driver.navigate().back();

//        WebElement passwordField2 = driver.findElement(RelativeLocator.with(By.tagName("input")).above(link));
//
//        passwordField2.clear();

    }
}

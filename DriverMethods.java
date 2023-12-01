import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.locators.RelativeLocator;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class DriverMethods {
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("http://zero.webappsecurity.com/login.html");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Dimension dm = new Dimension(450,650);
        System.out.println(driver.manage().window().getSize());
        System.out.println(driver.manage().window().getPosition());
        driver.manage().window().minimize();
        driver.manage().window().fullscreen();
        driver.manage().window().maximize();
        System.out.println(driver.manage().logs());
        //WebElement LoginText = driver.findElement(RelativeLocator.RelativeBy.xpath());

        //WebElement LoginText = driver.findElement(By.xpath("//label[text()='Login']"));
        //System.out.println(LoginText.getText());

        System.out.println(driver.getCurrentUrl());

        WebElement PasswordText = driver.findElement(By.xpath("//label[text()='Password']"));
        System.out.println(PasswordText.isDisplayed());
        WebElement LoginField = driver.findElement(By.id("user_login"));
        System.out.println(LoginField.isEnabled());
        LoginField.sendKeys("Sahal");
        WebElement PasswordField = driver.findElement(By.id("user_password"));
        PasswordField.sendKeys("Sahal1234");
        WebElement checkBox = driver.findElement(By.id("user_remember_me"));
        checkBox.click();
        System.out.println(checkBox.isSelected());
        WebElement submitButton = driver.findElement(By.name("submit"));
        submitButton.click();
        WebElement errorMessage = driver.findElement(By.className("alert"));
        System.out.println(errorMessage.isDisplayed());

        driver.navigate().to("https://rahulshettyacademy.com/AutomationPractice/");

        WebElement searchbox = driver.findElement(By.id("autocomplete"));
        searchbox.sendKeys("ind");
        Thread.sleep(3000);
        List<WebElement> dropdown = driver.findElements(By.xpath("//ul[@id='ui-id-1']/li/div"));
        for (WebElement element : dropdown
        ) {
            if (element.getText().equalsIgnoreCase("British Indian Ocean Territory")) {

                element.click();
            }

        }

        driver.navigate().back();
        System.out.println(driver.getTitle());
        driver.navigate().forward();
        System.out.println(driver.getPageSource());
        driver.navigate().refresh();

        WebElement window = driver.findElement(By.id("openwindow"));
        System.out.println(window.getSize());
        window.click();

        String parent = driver.getWindowHandle();
        System.out.println(parent);

        Set<String> s = driver.getWindowHandles();
        System.out.println(s);

// Now iterate using Iterator
        Iterator<String> I1 = s.iterator();

        while (I1.hasNext()) {

            String child_window = I1.next();


            if (!parent.equals(child_window)) {
                driver.switchTo().window(child_window);
                System.out.println(driver.switchTo().window(child_window).getTitle());
            }
        }

        driver.switchTo().window(parent);
        System.out.println(driver.getTitle());

//        WebElement alertButton = driver.findElement(By.id("confirmbtn"));
//        alertButton.click();
//        driver.switchTo().alert().accept();




        Point point = new Point(300,500);
        driver.manage().window().setPosition(point);
        driver.quit();
    }
}

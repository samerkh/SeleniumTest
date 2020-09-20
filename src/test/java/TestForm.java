import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class TestForm {
    WebDriver browser;

    @BeforeEach
    public void setup(){
        System.setProperty("webdriver.chrome.driver","C:\\Users\\Samer\\Desktop\\PSEU\\Basel\\chromedriver_win32\\chromedriver.exe");

        browser = new ChromeDriver();
        browser.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        browser.get("https://forms.office.com/Pages/ResponsePage.aspx?id=DQSIkWdsW0yxEjajBLZtrQAAAAAAAAAAAAN__tQlYTdURExDM0ZZQVBZQzIyRjQzMjNMTFk3RTYzMy4u");
    }

    @Test
    public void testCheckbox(){
        WebElement checkBox = browser.findElement(By.xpath("//input[@value=\"Option 1\" and @type=\"checkbox\"]"));
        checkBox.click();
        Assertions.assertTrue(checkBox.isSelected());
    }

    @Test
    public void testNumberField(){
        WebElement numberField = browser.findElement(By.xpath("//*[@placeholder=\"Number must be between 1 ~ 10\"]"));

        numberField.sendKeys("50");
        Assertions.assertNotEquals(browser.findElements(By.xpath("//*[text()=\"Number must be between 1 ~ 10\"]")).size(),0);

        numberField.clear();

        numberField.sendKeys("5");
        Assertions.assertEquals(browser.findElements(By.xpath("//*[text()=\"Number must be between 1 ~ 10\"]")).size(),0);
    }

    @Test
    public void testRequired(){
        WebElement textRequired = browser.findElement(By.xpath("//*[@placeholder=\"Enter your answer\"]"));
        WebElement submitBtn = browser.findElement(By.xpath("//button//div[text()=\"Submit\"]"));

        textRequired.clear();
        submitBtn.click();
        Assertions.assertNotEquals(browser.findElements(By.xpath("//*[text()=\"This question is required.\"]")).size(),0);

        textRequired.sendKeys("hello");
        Assertions.assertEquals(browser.findElements(By.xpath("//*[text()=\"This question is required.\"]")).size(),0);

        submitBtn.click();
        Assertions.assertNotEquals(browser.findElements(By.xpath("//div[@class=\"thank-you-page-root-container\"]")).size(),0);
    }

    @AfterEach
    public void finish(){
        browser.close();
    }

}

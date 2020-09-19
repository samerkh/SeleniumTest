import org.junit.Assert;
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
        browser.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        browser.get("https://forms.office.com/Pages/ResponsePage.aspx?id=DQSIkWdsW0yxEjajBLZtrQAAAAAAAAAAAAN__tQlYTdURExDM0ZZQVBZQzIyRjQzMjNMTFk3RTYzMy4u");
    }

    @Test
    public void testCheckbox(){
        WebElement checkBox1 = browser.findElement(By.xpath("//*[@id=\"form-container\"]/div/div/div[1]/div/div[1]/div[2]/div[2]/div[1]/div[2]/div[3]/div/div[1]/div/label/input"));
        checkBox1.click();

    }

    @Test
    public void testNumberField(){
        WebElement numberField = browser.findElement(By.xpath("//*[@id=\"form-container\"]/div/div/div/div/div[2]/div[2]/div[2]/div/div[2]/div/div/input"));
        numberField.sendKeys("100");
        WebElement errMsg = browser.findElement(By.linkText("Number must be between 1 ~ 10"));
        Assertions.assertTrue(errMsg.isDisplayed());
    }

    @Test
    public void testRequired(){
        WebElement textRequired = browser.findElement(By.xpath("//*[@id=\"form-container\"]/div/div/div[1]/div/div[1]/div[2]/div[2]/div[3]/div[2]/div[3]/div/div/div/input"));
        textRequired.sendKeys("");

        WebElement submitBtn = browser.findElement(By.xpath("//*[@id=\"form-container\"]/div/div/div/div/div[2]/div[3]/div[1]/button/div"));
        WebElement requiredText = browser.findElement(By.linkText("This question is required."));

        Assertions.assertTrue(requiredText.isDisplayed());
    }

    @AfterEach
    public void finish(){
        browser.close();
    }

}

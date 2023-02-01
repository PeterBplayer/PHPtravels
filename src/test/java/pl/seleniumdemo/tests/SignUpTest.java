package pl.seleniumdemo.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class SignUpTest extends BaseTest {

    @Test
    public void signUpTest() {

        driver.findElements(By.xpath("//li[@id='li_myaccount']"))
                .stream()
                .filter(WebElement::isDisplayed)
                .findFirst()
                .ifPresent(WebElement::click);
        driver.findElements(By.xpath("//a[text() ='  Sign Up']"))
                .get(1)
                .click();

        int randomNumber = (int) (Math.random() * 1000);
        String email = "bu.kropel" + randomNumber + "@tester.com";

        driver.findElement(By.name("firstname")).sendKeys("Bu");
        driver.findElement(By.name("lastname")).sendKeys("Kropel");
        driver.findElement(By.name("phone")).sendKeys("123456789");
        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.name("password")).sendKeys("Test123");
        driver.findElement(By.name("confirmpassword")).sendKeys("Test123");

        driver.findElement(By.xpath("//button[text()=' Sign Up']")).click();

        WebElement heading = driver.findElement(By.xpath("//h3[@class='RTL']"));
        Assert.assertEquals(heading.getText(), "Hi, Bu Kropel");
    }

    @Test
    public void emptyFormTest() {

        driver.findElements(By.xpath("//li[@id='li_myaccount']"))
                .stream()
                .filter(WebElement::isDisplayed)
                .findFirst()
                .ifPresent(WebElement::click);
        driver.findElements(By.xpath("//a[text() ='  Sign Up']"))
                .get(1)
                .click();

        driver.findElement(By.xpath("//button[text()=' Sign Up']")).click();

        List<String> validationAlerts = driver.findElements(By.xpath("//div[@class='alert alert-danger']/p"))
                .stream()
                .map(WebElement::getText)
                .toList();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(validationAlerts.contains("The Email field is required."));
        softAssert.assertTrue(validationAlerts.contains("The Password field is required."));
        softAssert.assertTrue(validationAlerts.contains("The First name field is required."));
        softAssert.assertTrue(validationAlerts.contains("The Last Name field is required."));
        softAssert.assertAll();
    }

    @Test
    public void invalidEmailTest() {

        driver.findElements(By.xpath("//li[@id='li_myaccount']"))
                .stream()
                .filter(WebElement::isDisplayed)
                .findFirst()
                .ifPresent(WebElement::click);
        driver.findElements(By.xpath("//a[text() ='  Sign Up']"))
                .get(1)
                .click();


        driver.findElement(By.name("firstname")).sendKeys("Bu");
        driver.findElement(By.name("lastname")).sendKeys("Kropel");
        driver.findElement(By.name("phone")).sendKeys("123456789");
        driver.findElement(By.name("email")).sendKeys("invalid email");
        driver.findElement(By.name("password")).sendKeys("Test123");
        driver.findElement(By.name("confirmpassword")).sendKeys("Test123");

        driver.findElement(By.xpath("//button[text()=' Sign Up']")).click();

        List<String> alerts = driver.findElements(By.xpath("//div[@class='alert alert-danger']//p"))
                .stream()
                .map(WebElement::getText)
                .toList();

        Assert.assertTrue(alerts.contains("The Email field must contain a valid email address."));
    }
}

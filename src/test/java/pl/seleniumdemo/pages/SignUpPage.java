package pl.seleniumdemo.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pl.seleniumdemo.model.User;
import pl.seleniumdemo.utils.SeleniumHelper;

import java.util.List;

public class SignUpPage {
    private WebDriver driver;
    private static final Logger logger = LogManager.getLogger();
    public SignUpPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(name = "firstname")
    private WebElement firstNameInput;
    @FindBy(name = "lastname")
    private WebElement lastNameInput;
    @FindBy(name = "phone")
    private WebElement phoneInput;
    @FindBy(name = "email")
    private WebElement emailInput;
    @FindBy(name = "password")
    private WebElement passwordInput;
    @FindBy(name = "confirmpassword")
    private WebElement confirmPasswordInput;
    @FindBy(xpath = "//button[text()=' Sign Up']")
    private WebElement singUpBtn;
    @FindBy(xpath = "//div[@class='alert alert-danger']/p")
    private List<WebElement> validationAlerts;

    public void setFirstName(String firstName) {
        logger.info("Setting first name: " + firstName);
        firstNameInput.sendKeys(firstName);
    }

    public void setLastName(String lastName) {
        logger.info("Setting last name: " + lastName);
        lastNameInput.sendKeys(lastName);
    }

    public void setPhone(String phoneNo) {
        logger.info("Setting phone number: " + phoneNo);
        phoneInput.sendKeys(phoneNo);
    }

    public void setEmail(String email) {
        logger.info("Setting email: " + email);
        emailInput.sendKeys(email);
    }

    public void setPassword(String password) {
        logger.info("Setting password: " + password);
        passwordInput.sendKeys(password);
    }

    public void setConfirmPassword(String password) {
        logger.info("Confirm password: " + password);
        confirmPasswordInput.sendKeys(password);
    }

    public void fillSignUpForm(String firstName, String lastName, String phoneNo, String email, String password) {
        logger.info("Filling up form...");
        firstNameInput.sendKeys(firstName);
        lastNameInput.sendKeys(lastName);
        phoneInput.sendKeys(phoneNo);
        emailInput.sendKeys(email);
        passwordInput.sendKeys(password);
        confirmPasswordInput.sendKeys(password);
    }

    public void fillSignUpForm(User user) {
        logger.info("Filling up form...");
        firstNameInput.sendKeys(user.getFirstName());
        lastNameInput.sendKeys(user.getLastName());
        phoneInput.sendKeys(user.getPhoneNo());
        emailInput.sendKeys(user.getEmail());
        passwordInput.sendKeys(user.getPassword());
        confirmPasswordInput.sendKeys(user.getPassword());
    }

    public void signUp() {
        singUpBtn.click();
    }

    public List<String> getValidationAlerts() {
        SeleniumHelper.waitForNotEmptyList(driver, By.xpath("//div[@class='alert alert-danger']/p"));
        return validationAlerts.stream()
                .map(WebElement::getText)
                .toList();
    }


}

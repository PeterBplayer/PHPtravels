package pl.seleniumdemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SignUpPage {
    public SignUpPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
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
        firstNameInput.sendKeys(firstName);
    }

    public void setLastName(String lastName) {
        lastNameInput.sendKeys(lastName);
    }

    public void setPhone(String phoneNo) {
        phoneInput.sendKeys(phoneNo);
    }

    public void setEmail(String email) {
        emailInput.sendKeys(email);
    }

    public void setPassword(String password) {
        passwordInput.sendKeys(password);
    }

    public void setConfirmPassword(String password) {
        confirmPasswordInput.sendKeys(password);
    }

    public void fillSignUpForm(String firstName, String lastName, String phoneNo, String email, String password) {
        firstNameInput.sendKeys(firstName);
        lastNameInput.sendKeys(lastName);
        phoneInput.sendKeys(phoneNo);
        emailInput.sendKeys(email);
        passwordInput.sendKeys(password);
        confirmPasswordInput.sendKeys(password);
    }

    public void signUp() {
        singUpBtn.click();
    }

    public List<String> getValidationAlerts() {
        return validationAlerts.stream()
                .map(WebElement::getText)
                .toList();
    }


}

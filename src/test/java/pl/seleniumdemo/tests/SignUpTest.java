package pl.seleniumdemo.tests;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pl.seleniumdemo.model.User;
import pl.seleniumdemo.pages.HotelSearchPage;
import pl.seleniumdemo.pages.LoggedUserPage;
import pl.seleniumdemo.pages.SignUpPage;
import pl.seleniumdemo.utils.SeleniumHelper;

import java.io.IOException;
import java.util.List;

public class SignUpTest extends BaseTest {

    @Test
    public void signUpTest() throws IOException {

        ExtentTest test = extentReports.createTest("Sign Up Test");
        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.openSignUpForm();

        int randomNumber = (int) (Math.random() * 1000);
        String email = "bu.kropel" + randomNumber + "@tester.com";

        SignUpPage signUpPage = new SignUpPage(driver);
        signUpPage.setFirstName("Bu");
        test.log(Status.PASS, "Setting first name success", SeleniumHelper.getScreenshot(driver));
        signUpPage.setLastName("Kropel");
        test.log(Status.PASS, "Setting last name success", SeleniumHelper.getScreenshot(driver));
        signUpPage.setPhone("123456789");
        test.log(Status.PASS, "Setting phone number success", SeleniumHelper.getScreenshot(driver));
        signUpPage.setEmail(email);
        test.log(Status.PASS, "Setting email success", SeleniumHelper.getScreenshot(driver));
        signUpPage.setPassword("Test123");
        test.log(Status.PASS, "Setting password success", SeleniumHelper.getScreenshot(driver));
        signUpPage.setConfirmPassword("Test123");
        test.log(Status.PASS, "Setting confirming password success", SeleniumHelper.getScreenshot(driver));

        signUpPage.signUp();


        LoggedUserPage loggedUserPage = new LoggedUserPage(driver);

        Assert.assertEquals(loggedUserPage.getHeadingText(), "Hi, Bu Kropel");
        test.log(Status.PASS, "Assertions passed", SeleniumHelper.getScreenshot(driver));

    }

    @Test
    public void signUpTestAlt() throws IOException {

        ExtentTest test = extentReports.createTest("Alternative Sign Up Test");
        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.openSignUpForm();

        int randomNumber = (int) (Math.random() * 1000);
        String email = "ping.buba" + randomNumber + "@poczta.pl";

        User user = new User();
        user.setFirstName("Buba");
        user.setLastName("Pingwin");
        user.setPhoneNo("11122233");
        user.setEmail(email);
        user.setPassword("Testowe321");

        SignUpPage signUpPage = new SignUpPage(driver);
        signUpPage.fillSignUpForm(user);
        test.log(Status.PASS, "Setting user credentials success", SeleniumHelper.getScreenshot(driver));
//        signUpPage.fillSignUpForm("Buba", "Pingwin", "11122233", email, "Testowe321"); // Alternatywa dla powyższego
        signUpPage.signUp();

        LoggedUserPage loggedUserPage = new LoggedUserPage(driver);

        Assert.assertTrue(loggedUserPage.getHeadingText().contains(user.getLastName()));
        Assert.assertEquals(loggedUserPage.getHeadingText(), "Hi, Buba Pingwin");
        test.log(Status.PASS, "Assertions passed", SeleniumHelper.getScreenshot(driver));
    }

    @Test
    public void emptyFormTest() throws IOException {

        ExtentTest test = extentReports.createTest("Empty Form Sign Up Test");
        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.openSignUpForm();

        SignUpPage signUpPage = new SignUpPage(driver);
        signUpPage.signUp();
        test.log(Status.PASS, "Trying sign up with empty fields success", SeleniumHelper.getScreenshot(driver));

        List<String> validationAlerts = signUpPage.getValidationAlerts();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(validationAlerts.contains("The Email field is required."));
        softAssert.assertTrue(validationAlerts.contains("The Password field is required."));
        softAssert.assertTrue(validationAlerts.contains("The First name field is required."));
        softAssert.assertTrue(validationAlerts.contains("The Last Name field is required."));
        softAssert.assertAll();
        test.log(Status.PASS, "Assertions passed", SeleniumHelper.getScreenshot(driver));
    }

    @Test
    public void invalidEmailTest() throws IOException {

        ExtentTest test = extentReports.createTest("Invalid Email Sign Up Test");
        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.openSignUpForm();

        int randomNumber = (int) (Math.random() * 1000);
        String invalidEmail = "bu.kropel" + randomNumber;

        SignUpPage signUpPage = new SignUpPage(driver);
        signUpPage.setFirstName("Bu");
        test.log(Status.PASS, "Setting first name success", SeleniumHelper.getScreenshot(driver));
        signUpPage.setLastName("Kropel");
        test.log(Status.PASS, "Setting last name success", SeleniumHelper.getScreenshot(driver));
        signUpPage.setPhone("123456789");
        test.log(Status.PASS, "Setting phone number success", SeleniumHelper.getScreenshot(driver));
        signUpPage.setEmail(invalidEmail);
        test.log(Status.PASS, "Setting invalid email success", SeleniumHelper.getScreenshot(driver));
        signUpPage.setPassword("Test123");
        test.log(Status.PASS, "Setting password success", SeleniumHelper.getScreenshot(driver));
        signUpPage.setConfirmPassword("Test123");
        test.log(Status.PASS, "Setting confirming password success", SeleniumHelper.getScreenshot(driver));

        signUpPage.signUp();

        Assert.assertTrue(signUpPage.getValidationAlerts().contains("The Email field must contain a valid email address."));
        test.log(Status.PASS, "Assertions passed", SeleniumHelper.getScreenshot(driver));
    }
}

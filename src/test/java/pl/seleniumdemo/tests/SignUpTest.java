package pl.seleniumdemo.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pl.seleniumdemo.pages.HotelSearchPage;
import pl.seleniumdemo.pages.LoggedUserPage;
import pl.seleniumdemo.pages.SignUpPage;

import java.util.List;

public class SignUpTest extends BaseTest {

    @Test
    public void signUpTest() {

        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.openSignUpForm();

        int randomNumber = (int) (Math.random() * 1000);
        String email = "bu.kropel" + randomNumber + "@tester.com";

        SignUpPage signUpPage = new SignUpPage(driver);
        signUpPage.setFirstName("Bu");
        signUpPage.setLastName("Kropel");
        signUpPage.setPhone("123456789");
        signUpPage.setEmail(email);
        signUpPage.setPassword("Test123");
        signUpPage.setConfirmPassword("Test123");

        signUpPage.signUp();

        LoggedUserPage loggedUserPage = new LoggedUserPage(driver);

        Assert.assertEquals(loggedUserPage.getHeadingText(), "Hi, Bu Kropel");
    }

    @Test
    public void signUpTestAlt() {

        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.openSignUpForm();

        int randomNumber = (int) (Math.random() * 1000);
        String email = "ping.buba" + randomNumber + "@poczta.pl";

        SignUpPage signUpPage = new SignUpPage(driver);
        signUpPage.fillSignUpForm("Buba", "Pingwin", "11122233", email, "Testowe321");
        signUpPage.signUp();

        LoggedUserPage loggedUserPage = new LoggedUserPage(driver);

        Assert.assertEquals(loggedUserPage.getHeadingText(), "Hi, Buba Pingwin");
    }

    @Test
    public void emptyFormTest() {

        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.openSignUpForm();

        SignUpPage signUpPage = new SignUpPage(driver);
        signUpPage.signUp();

        List<String> validationAlerts = signUpPage.getValidationAlerts();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(validationAlerts.contains("The Email field is required."));
        softAssert.assertTrue(validationAlerts.contains("The Password field is required."));
        softAssert.assertTrue(validationAlerts.contains("The First name field is required."));
        softAssert.assertTrue(validationAlerts.contains("The Last Name field is required."));
        softAssert.assertAll();
    }

    @Test
    public void invalidEmailTest() {

        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.openSignUpForm();

        int randomNumber = (int) (Math.random() * 1000);
        String invalidEmail = "bu.kropel" + randomNumber;

        SignUpPage signUpPage = new SignUpPage(driver);
        signUpPage.setFirstName("Bu");
        signUpPage.setLastName("Kropel");
        signUpPage.setPhone("123456789");
        signUpPage.setEmail(invalidEmail);
        signUpPage.setPassword("Test123");
        signUpPage.setConfirmPassword("Test123");

        signUpPage.signUp();

        Assert.assertTrue(signUpPage.getValidationAlerts().contains("The Email field must contain a valid email address."));
    }
}

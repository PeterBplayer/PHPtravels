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

        int randomNumber = (int) (Math.random() * 1000);

        LoggedUserPage loggedUserPage = new HotelSearchPage(driver)
                .openSignUpForm()
                .setFirstName("Bu")
                .setLastName("Kropel")
                .setPhone("123456789")
                .setEmail("bu.kropel" + randomNumber + "@tester.com")
                .setPassword("Test123")
                .setConfirmPassword("Test123")
                .signUp();

        Assert.assertEquals(loggedUserPage.getHeadingText(), "Hi, Bu Kropel");
    }


    @Test
    public void emptyFormTest() {

        SignUpPage signUpPage = new HotelSearchPage(driver).openSignUpForm();
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

        int randomNumber = (int) (Math.random() * 1000);
        SignUpPage signUpPage = new HotelSearchPage(driver)
                .openSignUpForm()
                .setFirstName("Bu")
                .setLastName("Kropel")
                .setPhone("123456789")
                .setEmail("bu.kropel" + randomNumber)
                .setPassword("Test123")
                .setConfirmPassword("Test123");

        signUpPage.signUp();

        Assert.assertTrue(signUpPage.getValidationAlerts().contains("The Email field must contain a valid email address."));
    }
}

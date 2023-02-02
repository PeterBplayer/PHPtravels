package pl.seleniumdemo.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pl.seleniumdemo.utils.SeleniumHelper;

import java.util.List;

public class HotelSearchPage {
    private WebDriver driver;

    private static final Logger logger = LogManager.getLogger();

    public HotelSearchPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//span[text()='Search by Hotel or City Name']")
    private WebElement searchHotelSpan;
    @FindBy(xpath = "//div[@id='select2-drop']//input")
    private WebElement searchHotelInput;
    @FindBy(name = "checkin")
    private WebElement checkInInput;
    @FindBy(name = "checkout")
    private WebElement checkOutInput;
    @FindBy(id = "travellersInput")
    private WebElement travellersInput;
    @FindBy(id = "adultPlusBtn")
    private WebElement adultPlusBtn;
    @FindBy(id = "adultMinusBtn")
    private WebElement adultMinusBtn;
    @FindBy(id = "childPlusBtn")
    private WebElement childPlusBtn;
    @FindBy(id = "childMinusBtn")
    private WebElement childMinusBtn;
    @FindBy(xpath = "//button[text()=' Search']")
    private WebElement searchBtn;
    @FindBy(xpath = "//li[@id='li_myaccount']")
    private List<WebElement> myAccountLink;
    @FindBy(xpath = "//a[text() ='  Sign Up']")
    private List<WebElement> signUpLink;

    public void setCity(String cityName) {
        logger.info("Setting city " + cityName);
        searchHotelSpan.click();
        searchHotelInput.sendKeys(cityName);
        String xpath = String.format("//span[@class='select2-match' and text()='%s']", cityName);
        SeleniumHelper.waitForElementToExist(driver, By.xpath(xpath));
        driver.findElement(By.xpath(xpath)).click();
        logger.info("Setting city done");
    }

    public void setDates(String checkin, String checkout) {
        logger.info("Setting dates check-in: " + checkin + " check-out " + checkout);
        checkInInput.sendKeys(checkin);
        checkOutInput.sendKeys(checkout);
        logger.info("Setting dates done");
    }

    private void addTraveler(WebElement travelerBtn, int numberOfTravelers) {
        for (int i = 0; i < numberOfTravelers; i++) {
            travelerBtn.click();
        }
    }

    public void setTravellers(String adultAddRemove, int adultsToAdd, String childAddRemove, int childToAdd) {
        travellersInput.click();
        if (adultAddRemove.equals("+")) {
            SeleniumHelper.waitForElementToBeVisible(driver, adultPlusBtn);
            logger.info("Adding adults: " + adultsToAdd);
            addTraveler(adultPlusBtn, adultsToAdd);
        } else if (adultAddRemove.equals("-")) {
            SeleniumHelper.waitForElementToBeVisible(driver, adultMinusBtn);
            logger.info("Removing adults: " + adultsToAdd);
            addTraveler(adultMinusBtn, adultsToAdd);
        }
        if (childAddRemove.equals("+")) {
            SeleniumHelper.waitForElementToBeVisible(driver, childPlusBtn);
            logger.info("Adding child: " + childToAdd);
            addTraveler(childPlusBtn, childToAdd);
        } else if (childAddRemove.equals("-")) {
            SeleniumHelper.waitForElementToBeVisible(driver, childMinusBtn);
            logger.info("Removing child: " + childToAdd);
            addTraveler(childMinusBtn, childToAdd);
        }
        logger.info("Set travellers done");
    }

    public void performSearch() {
        searchBtn.click();
    }

    public void openSignUpForm() {
        myAccountLink.stream()
                .filter(WebElement::isDisplayed)
                .findFirst()
                .ifPresent(WebElement::click);
        signUpLink.get(1).click();
    }


}




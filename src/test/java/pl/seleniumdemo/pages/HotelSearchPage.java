package pl.seleniumdemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class HotelSearchPage {
    private WebDriver driver;

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

    public HotelSearchPage setCity(String cityName) {
        searchHotelSpan.click();
        searchHotelInput.sendKeys(cityName);
        String xpath = String.format("//span[@class='select2-match' and text()='%s']", cityName);
        driver.findElement(By.xpath(xpath)).click();
        return this;
    }

    public HotelSearchPage setDates(String checkin, String checkout) {
        checkInInput.sendKeys(checkin);
        checkOutInput.sendKeys(checkout);
        return this;
    }

    private void addTraveler(WebElement travelerBtn, int numberOfTravelers) {
        for (int i = 0; i < numberOfTravelers; i++) {
            travelerBtn.click();
        }
    }

    public HotelSearchPage setTravellers(String adultAddRemove, int adultsToAdd, String childAddRemove, int childToAdd) {
        travellersInput.click();
        if (adultAddRemove.equals("+")) {
            addTraveler(adultPlusBtn, adultsToAdd);
        } else if (adultAddRemove.equals("-")) {
            addTraveler(adultMinusBtn, adultsToAdd);
        }
        if (childAddRemove.equals("+")) {
            addTraveler(childPlusBtn, childToAdd);
        } else if (childAddRemove.equals("-")) {
            addTraveler(childMinusBtn, childToAdd);
        }
        return this;
    }

    public ResultsPage performSearch() {
        searchBtn.click();
        return new ResultsPage(driver);
    }

    public void openSignUpForm() {
        myAccountLink.stream()
                .filter(WebElement::isDisplayed)
                .findFirst()
                .ifPresent(WebElement::click);
        signUpLink.get(1).click();
    }


}




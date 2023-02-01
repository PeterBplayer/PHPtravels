package pl.seleniumdemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ResultsPage {

    @FindBy(xpath = "//h4[contains(@class,'list_title')]//b")
    private List<WebElement> hotelLists;
    @FindBy(xpath = "//h2[@class='text-center']")
    public WebElement  noResultHeading;

    public ResultsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public List<String> getHotelNames() {
        return hotelLists.stream()
                .map(element -> element.getAttribute("textContent"))
                .toList();
    }

    public String getHeadingText() {
        return noResultHeading.getText();
    }
}

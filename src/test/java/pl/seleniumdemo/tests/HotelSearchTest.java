package pl.seleniumdemo.tests;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pl.seleniumdemo.pages.HotelSearchPage;
import pl.seleniumdemo.pages.ResultsPage;
import pl.seleniumdemo.utils.ExcelReader;
import pl.seleniumdemo.utils.SeleniumHelper;

import java.io.IOException;
import java.util.List;

public class HotelSearchTest extends BaseTest {
    @DataProvider
    public Object[][] data() throws IOException {
        return ExcelReader.readExcel("testData.xlsx");
    }

    @Test
    public void searchHotelTest() throws IOException {

        ExtentTest test = extentReports.createTest("Search Hotel Test");
        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);

        hotelSearchPage.setCity("Dubai");
        test.log(Status.PASS, "Setting city success", SeleniumHelper.getScreenshot(driver));
        hotelSearchPage.setDates("04/04/2023", "15/04/2023");
        test.log(Status.PASS, "Setting dates success", SeleniumHelper.getScreenshot(driver));
        hotelSearchPage.setTravellers("+", 1, "+", 2);
        test.log(Status.PASS, "Setting travellers success", SeleniumHelper.getScreenshot(driver));
        hotelSearchPage.performSearch();
        test.log(Status.PASS, "Performing search success", SeleniumHelper.getScreenshot(driver));

        ResultsPage resultsPage = new ResultsPage(driver);
        List<String> hotelNames = resultsPage.getHotelNames();


        Assert.assertEquals(hotelNames.get(0), "Jumeirah Beach Hotel");
        Assert.assertEquals(hotelNames.get(1), "Oasis Beach Tower");
        Assert.assertEquals(hotelNames.get(2), "Rose Rayhaan Rotana");
        Assert.assertEquals(hotelNames.get(3), "Hyatt Regency Perth");
        test.log(Status.PASS, "Assertions passed", SeleniumHelper.getScreenshot(driver));

    }

    @Test
    public void resultNotFoundTest() throws IOException {

        ExtentTest test = extentReports.createTest("Search Hotel Test Without City Name");
        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.setDates("02/02/2023", "06/02/2023");
        test.log(Status.PASS, "Setting dates success", SeleniumHelper.getScreenshot(driver));
        hotelSearchPage.setTravellers("-", 1, "+", 0);
        test.log(Status.PASS, "Setting travellers success", SeleniumHelper.getScreenshot(driver));
        hotelSearchPage.performSearch();
        test.log(Status.PASS, "Performing search success", SeleniumHelper.getScreenshot(driver));

        ResultsPage resultsPage = new ResultsPage(driver);

        Assert.assertTrue(resultsPage.noResultHeading.isDisplayed());
        Assert.assertEquals(resultsPage.getHeadingText(), "No Results Found");
        test.log(Status.PASS, "Assertions passed", SeleniumHelper.getScreenshot(driver));

    }

    @Test(dataProvider = "data")
    public void searchHotelTestWithDataProvider(String city, String hotel) throws IOException {

        ExtentTest test = extentReports.createTest("Search Hotel Test With Excel Data: " + city);
        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);

        hotelSearchPage.setCity(city);
        test.log(Status.PASS, "Setting city success", SeleniumHelper.getScreenshot(driver));
        hotelSearchPage.setDates("04/04/2023", "15/04/2023");
        test.log(Status.PASS, "Setting dates success", SeleniumHelper.getScreenshot(driver));
        hotelSearchPage.setTravellers("+", 1, "+", 2);
        test.log(Status.PASS, "Setting travellers success", SeleniumHelper.getScreenshot(driver));
        hotelSearchPage.performSearch();
        test.log(Status.PASS, "Performing search success", SeleniumHelper.getScreenshot(driver));

        ResultsPage resultsPage = new ResultsPage(driver);
        List<String> hotelNames = resultsPage.getHotelNames();


        Assert.assertEquals(hotelNames.get(0), hotel);
        test.log(Status.PASS, "Assertions passed", SeleniumHelper.getScreenshot(driver));
    }
}

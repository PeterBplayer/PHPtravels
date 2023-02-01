package pl.seleniumdemo.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {

    public static WebDriver getDriver(String name) {
        if (name.equals("chrome")) {
            WebDriverManager.chromedriver().setup();
            return new ChromeDriver();
        } else {
            WebDriverManager.firefoxdriver().setup();
            return new FirefoxDriver();
        }
    }
}

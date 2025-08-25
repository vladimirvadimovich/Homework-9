package pages;

import java.time.Duration;

import core.DriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;


public abstract class BasePage {
    protected WebDriver driver = DriverManager.getDriver();
    protected WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    public BasePage open(String url) {
        driver.get(url);
        return this;
    }
    public String getTitle() {
        return driver.getTitle();
    }

}
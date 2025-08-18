package pages;

import java.time.Duration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.DriverSingleton;

public class BasePage {
    protected static WebDriver driver;
    protected static WebDriverWait wait;

    @BeforeAll
    public static void setupDriver() {
        driver = DriverSingleton.getDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5L));
    }

    @AfterAll
    public static void tearDownDriver() {
        DriverSingleton.quitDriver();
    }
}

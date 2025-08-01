package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class DriverSingleton {

    private static WebDriver driver;

    private DriverSingleton() { }

    public static WebDriver getDriver() {
        if (driver == null) {
            // Подгружаем chromedriver через WebDriverManager
            WebDriverManager.chromedriver().clearDriverCache().setup();

            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");
            //options.addArguments("--headless");
            //options.addArguments("--disable-gpu");
            options.addArguments("--start-fullscreen");

            driver = new ChromeDriver(options);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        }
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}

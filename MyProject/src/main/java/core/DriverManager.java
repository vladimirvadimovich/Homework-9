package core;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;

public final class DriverManager {
    private DriverManager() {}

    public static WebDriver getDriver() {
        return WebDriverSingleton.getInstance().getDriver();
    }

    public static void quitDriver() {
        WebDriverSingleton.getInstance().removeDriver();
    }
}
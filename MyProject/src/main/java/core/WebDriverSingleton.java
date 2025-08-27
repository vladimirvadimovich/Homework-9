package core;

import org.openqa.selenium.WebDriver;

public class WebDriverSingleton {
    // ThreadLocal – чтобы в одном тестовом прогоне каждый поток держал свой WebDriver
    private final ThreadLocal<WebDriver> driverHolder = new ThreadLocal<>();

    // volatile + двойная проверка блокировки для ленивой инициализации
    private static volatile WebDriverSingleton instance;

    private WebDriverSingleton() {}

    public static WebDriverSingleton getInstance() {
        if (instance == null) {
            synchronized (WebDriverSingleton.class) {
                if (instance == null) {
                    instance = new WebDriverSingleton();
                }
            }
        }
        return instance;
    }

    WebDriver getDriver() {
        return driverHolder.get();
    }

    void setDriver(WebDriver driver) {
        driverHolder.set(driver);
    }

    void removeDriver() {
        driverHolder.remove();
    }
}
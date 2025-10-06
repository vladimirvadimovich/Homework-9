package core;

import org.openqa.selenium.WebDriver;

public class WebDriverSingleton {
    // ThreadLocal – каждый поток получает свой WebDriver
    private final ThreadLocal<WebDriver> driverHolder = new ThreadLocal<>();

    // Единственный экземпляр синглтона
    private static final WebDriverSingleton INSTANCE = new WebDriverSingleton();

    private WebDriverSingleton() {}

    // Получить сам синглтон
    public static WebDriverSingleton getInstance() {
        return INSTANCE;
    }

    /**
     * Получить WebDriver. Если он ещё не инициализирован в текущем потоке –
     * читаем настройки и создаём его через фабрику.
     */
    public WebDriver getDriver() {
        WebDriver driver = driverHolder.get();
        if (driver == null) {
            String browser = ConfigReader.get("browser");
            boolean headless = ConfigReader.getBoolean("headless");
            driver = DriverFactory.create(browser, headless);
            driverHolder.set(driver);
        }
        return driver;
    }

    // Позволяет вручную проставить драйвер (если надо)
    void setDriver(WebDriver driver) {
        driverHolder.set(driver);
    }

    // Удалить драйвер (например, в teardown)
    public void removeDriver() {
        WebDriver drv = driverHolder.get();
        if (drv != null) {
            drv.quit();
            driverHolder.remove();
        }
    }
}

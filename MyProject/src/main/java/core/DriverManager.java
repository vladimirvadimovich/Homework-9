package core;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;

import java.time.Duration;

public final class DriverManager {
    private DriverManager() { /* запрет создания экземпляра */ }

    /**
     * Инициализация драйвера по умолчанию (из конфигурации).
     */
    public static void initDriver() {
        String browser = ConfigReader.get("browser");
        boolean headless = ConfigReader.getBoolean("headless");
        initDriver(browser, headless);
    }

    /**
     * Инициализация драйвера с явной передачей параметров.
     */
    public static void initDriver(String browser, boolean headless) {
        WebDriverSingleton singleton = WebDriverSingleton.getInstance();
        if (singleton.getDriver() != null) {
            return; // уже инициализирован
        }

        WebDriver driver;
        switch (browser.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions co = new ChromeOptions();
                if (headless) co.addArguments("--headless=new");
                co.addArguments("--remote-allow-origins=*", "--start-maximized");
                driver = new ChromeDriver(co);
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions fo = new FirefoxOptions();
                if (headless) fo.addArguments("--headless");
                driver = new FirefoxDriver(fo);
                driver.manage().window().maximize();
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                EdgeOptions eo = new EdgeOptions();
                if (headless) eo.addArguments("--headless=new");
                driver = new EdgeDriver(eo);
                driver.manage().window().maximize();
                break;
            case "opera":
                WebDriverManager.operadriver().setup();
                OperaOptions oo = new OperaOptions();
                if (headless) oo.addArguments("--headless");
                driver = new OperaDriver(oo);
                driver.manage().window().maximize();
                break;
            default:
                throw new RuntimeException("Неизвестный браузер: " + browser);
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        singleton.setDriver(driver);
    }

    /**
     * Возвращает текущий WebDriver.
     * Если ещё не инициализирован – инициализирует "по умолчанию".
     */
    public static WebDriver getDriver() {
        WebDriverSingleton singleton = WebDriverSingleton.getInstance();
        if (singleton.getDriver() == null) {
            initDriver();
        }
        return singleton.getDriver();
    }
    public static void quitDriver() {
        WebDriverSingleton singleton = WebDriverSingleton.getInstance();
        WebDriver driver = singleton.getDriver();
        if (driver != null) {
            driver.quit();
            singleton.removeDriver();
        }
    }
}
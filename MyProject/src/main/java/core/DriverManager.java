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

public class DriverManager {


    private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();

    private DriverManager() { }

    /**
     * Инициализация драйвера с параметрами.
     *
     * @param browser  "chrome", "firefox", "edge", "opera"
     * @param headless true если нужен безголовый режим
     */
    public static void initDriver(String browser, boolean headless) {

        if (DRIVER.get() != null) {
            return;
        }

        WebDriver driver;
        switch (browser.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                if (headless) {
                    chromeOptions.addArguments("--headless=new");
                }
                chromeOptions.addArguments("--remote-allow-origins=*");
                chromeOptions.addArguments("--start-maximized");
                driver = new ChromeDriver(chromeOptions);
                break;

            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions ffOptions = new FirefoxOptions();
                if (headless) {
                    ffOptions.addArguments("--headless");
                }
                driver = new FirefoxDriver(ffOptions);
                driver.manage().window().maximize();
                break;

            case "edge":
                WebDriverManager.edgedriver().setup();
                EdgeOptions edgeOptions = new EdgeOptions();
                if (headless) {
                    edgeOptions.addArguments("--headless=new");
                }
                driver = new EdgeDriver(edgeOptions);
                driver.manage().window().maximize();
                break;

            case "opera":
                WebDriverManager.operadriver().setup();
                OperaOptions operaOptions = new OperaOptions();
                if (headless) {
                    operaOptions.addArguments("--headless");
                }
                driver = new OperaDriver(operaOptions);
                driver.manage().window().maximize();
                break;

            default:
                throw new RuntimeException("Неизвестный браузер: " + browser);
        }

        // общие настройки
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        DRIVER.set(driver);
    }

    public static void initDriver() {
        String browser = ConfigReader.get("browser");
        boolean headless = ConfigReader.getBoolean("headless");
        initDriver(browser, headless);
    }


    public static WebDriver getDriver() {
        if (DRIVER.get() == null) {
            initDriver();
        }
        return DRIVER.get();
    }


    public static void quitDriver() {
        WebDriver driver = DRIVER.get();
        if (driver != null) {
            driver.quit();
            DRIVER.remove();
        }
    }


}

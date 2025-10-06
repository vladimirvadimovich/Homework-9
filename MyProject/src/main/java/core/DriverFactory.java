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


//DriverFactory отвечает только за создание драйвера под нужный браузер и нужный режим (headless или нет).

public final class DriverFactory {
    private DriverFactory() {}

    public static WebDriver create(String browser, boolean headless) {
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
        return driver;
    }
}

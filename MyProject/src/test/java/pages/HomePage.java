package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    private WebDriver driver;
    private WebDriverWait wait;
    private static final String URL = "https://store.steampowered.com/";
    private static final By ACTION_TAB =
            By.xpath("//a[@class='gutter_item' and normalize-space(.)='Экшен']");
    private static final By DISCOUNT =
            By.xpath("//h2[@aria-label='Скидки и мероприятия']");

    // Конструктор принимает один WebDriver и создаёт WebDriverWait самостоятельно
    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public void open() {
        driver.get(URL);
    }

    public void scrollToActionButton() {
        WebElement element = wait.until(
                ExpectedConditions.visibilityOfElementLocated(DISCOUNT)
        );
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void goToActionCategory() {
        // Убеждаемся, что элемент кликабелен
        WebElement element = wait.until(
                ExpectedConditions.elementToBeClickable(ACTION_TAB)
        );
        element.click();
    }
}

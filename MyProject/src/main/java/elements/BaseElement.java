package elements;

import core.DriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BaseElement {
    protected WebDriver driver = DriverManager.getDriver();
    protected WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    protected By locator;

    protected BaseElement(By locator) {
        this.locator = locator;
    }

    protected WebElement get() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void click() {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    public boolean isDisplayed() {
        try {
            return get().isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    public String getText() {
        return get().getText();
    }

    public void scrollIntoView() {
        WebElement el = get();
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView(true);", el);
    }
}


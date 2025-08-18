package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.ConfigReader;

public class HomePage extends BasePage {
    private static final String URL = ConfigReader.get("base.url");
    private static final By ACTION_TAB = By.xpath("//a[@class='gutter_item' and normalize-space(.)='Экшен']");
    private static final By DISCOUNT = By.xpath("//div[@id='home_takeunder_ctn' and @class='home_ctn']");

    public void open() {
        driver.get(URL);
    }

    public void scrollToActionButton() {
        WebElement element = (WebElement)wait.until(ExpectedConditions.visibilityOfElementLocated(DISCOUNT));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", new Object[]{element});
    }

    public boolean goToActionCategory() {
        try {
            WebElement element = (WebElement)wait.until(ExpectedConditions.elementToBeClickable(ACTION_TAB));
            element.click();
            return true;
        } catch (TimeoutException var2) {
            return false;
        }
    }
}

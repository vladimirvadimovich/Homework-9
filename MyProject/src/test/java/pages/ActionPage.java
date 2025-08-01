package pages;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ActionPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Заголовок раздела «Popular Action Games»
    private By popularSectionHeader =
            By.xpath("//div[contains(@class, 'SaleSectionHeader') and normalize-space(text()) = 'Популярные игры']");

    // Ищем внутри этого раздела игру Counter-Strike 2
    private By cs2Tile =
            By.xpath("//img[@src='https://shared.akamai.steamstatic.com//store_item_assets/steam/apps/730/header.jpg?t=1749053861']");

    public ActionPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public void scrollToPopularSection() {
        WebElement section = wait.until(
                ExpectedConditions.visibilityOfElementLocated(popularSectionHeader)
        );
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView(true);", section);
    }

    public void assertCs2IsDisplayed() {
        // Дадим чуть времени подгрузиться
        wait.until(ExpectedConditions.presenceOfElementLocated(cs2Tile));
        WebElement cs2 = driver.findElement(cs2Tile);
        Assert.assertTrue("CS2 не отображается в разделе Popular!", cs2.isDisplayed());
    }
}


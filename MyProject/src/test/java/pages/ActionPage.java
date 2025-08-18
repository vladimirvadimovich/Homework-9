package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ActionPage extends BasePage {
    private By popularSectionHeader = By.xpath("//div[contains(@class, 'SaleSectionHeader') and normalize-space(text()) = 'Популярные игры']");
    private By cs2Tile = By.xpath("//img[@src='https://shared.akamai.steamstatic.com//store_item_assets/steam/apps/730/header.jpg?t=1749053861']");

    public void scrollToPopularSection() {
        WebElement section = (WebElement)wait.until(ExpectedConditions.visibilityOfElementLocated(this.popularSectionHeader));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", new Object[]{section});
    }

    public boolean isCs2TileDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(this.cs2Tile));
            return driver.findElement(this.cs2Tile).isDisplayed();
        } catch (TimeoutException var2) {
            return false;
        }
    }
}
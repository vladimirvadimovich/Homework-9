package pages;

import elements.Button;
import elements.Label;
import org.openqa.selenium.By;

public class ActionPage extends BasePage {

    private static final By CONTENT_HUB_TITLE = By.xpath(
            "//div[@class='saHqNV-7xE9caBAreUZiX ContentHubTitle']"
    );

    private Label pageTitle = new Label(CONTENT_HUB_TITLE);

    private Button popularSectionHeader = new Button(
            By.xpath("//div[@id='SaleSection_13268']")
    );

    private Label cs2Tile = new Label(
            By.xpath("//div[@id='SaleSection_13268']//img[@alt='Counter-Strike 2']")
    );

    public boolean isPageOpened() {
        return pageTitle.isDisplayed();
    }

    public boolean scrollToPopularSection() {
        popularSectionHeader.scrollIntoView();
        return popularSectionHeader.isDisplayed();
    }

    public boolean isCs2TileDisplayed() {
        return cs2Tile.isDisplayed();
    }
}
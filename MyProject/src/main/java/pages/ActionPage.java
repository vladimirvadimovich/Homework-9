package pages;

import elements.Button;
import elements.Label;
import org.openqa.selenium.By;

public class ActionPage extends BasePage {

    private Button popularSectionHeader = new Button(
            By.xpath("//div[contains(@class, 'CapsuleImageCtn')]/img[@alt='Counter-Strike 2']")
    );

    private Label cs2Tile = new Label(
            By.xpath("//img[@src='https://shared.akamai.steamstatic.com/store_item_assets/steam/apps/730/header.jpg?t=1749053861']")
    );

    public boolean scrollToPopularSection() {
        popularSectionHeader.scrollIntoView();
        return true;
    }

    public boolean isCs2TileDisplayed() {
        return cs2Tile.isDisplayed();
    }
}
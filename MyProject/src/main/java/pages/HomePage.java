package pages;

import core.DriverManager;
import elements.Button;
import core.ConfigReader;

import org.openqa.selenium.By;

public class HomePage extends BasePage{

    private static final String URL   = ConfigReader.get("base.url");
    private static final By ACTION_TAB = By.xpath(
            "//a[@class='gutter_item' "
                    + "and starts-with(@href,'https://store.steampowered.com/tags/ru/...')]");
    private static final By DISCOUNT_SECTION = By.xpath(
            "//div[@id='home_takeupnder_ctn' and @class='home_ctn']");

    // обёртки
    private Button actionTab = new Button(ACTION_TAB);
    private Button discountSection = new Button(DISCOUNT_SECTION);

    public boolean open() {
        DriverManager.getDriver().get(URL);
        return DriverManager.getDriver().getCurrentUrl().equals(URL);
    }

    public boolean scrollToDiscountSection() {
        discountSection.scrollIntoView();
        return discountSection.isDisplayed();
    }

    public boolean goToActionCategory() {
        try {
            actionTab.click();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

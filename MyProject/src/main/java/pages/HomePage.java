package pages;

import core.DriverManager;
import elements.Button;
import core.ConfigReader;

import org.openqa.selenium.By;

public class HomePage extends BasePage{
    private static final String URL   = ConfigReader.get("base.url");
    private static final By ACTION_TAB = By.xpath(
            "//a[@class='gutter_item'and starts-with(@href,'https://store.steampowered.com/tags/ru/%D0%AD%D0%BA%D1%88%D0%B5%D0%BD/?snr=1_4_4__125')]");
    private static final By DISCOUNT_SECTION = By.xpath(
            "//h2[@class='home_page_content_title' and @id='home_specialoffers']");
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
        if (!actionTab.isDisplayed()) {
            return false;
        }
        actionTab.click();   // <- click() по-прежнему void
        return true;
    }

}


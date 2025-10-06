package pages;

import core.DriverManager;
import elements.Button;
import core.ConfigReader;

import org.openqa.selenium.By;

public class HomePage extends BasePage{
    private static final String URL   = ConfigReader.get("base.url");

    private static final By ACTION_TAB = By.xpath(
            "(//div[contains(@class,'carousel_container')]//a[contains(@class,'content_hub_capsule_ctn')])[6]");

    private static final By DISCOUNT_SECTION = By.xpath(
            "//div[contains(@class,'home_pagecontent_ctn') and contains(@class,'no_mobile_margin')]//div[contains(@class,'content_hub_carousel_ctn') and contains(@class,'sale_capsule_carousel')]//div[contains(@class,'home_section_title')]");

    private static final By RIGHT_SELECTOR = By.xpath(
            "//div[@class='arrow right' and @data-usability='18']");


    private Button actionTab = new Button(ACTION_TAB);
    private Button discountSection = new Button(DISCOUNT_SECTION);
    private Button rightSelector = new Button(RIGHT_SELECTOR);
    public boolean open() {
        DriverManager.getDriver().get(URL);
        return DriverManager.getDriver().getCurrentUrl().equals(URL);
    }
    public boolean scrollToDiscountSection() {
        discountSection.scrollIntoView();
        return discountSection.isDisplayed();
    }

    public boolean isRightSelectorDisplayed() {
        return rightSelector.isDisplayed();
    }

    public boolean clickRightSelector() {
        if (!isRightSelectorDisplayed()) {
            return false;
        }
        rightSelector.click();
        return true;
    }

    public boolean isActionTabDisplayed() {
        return actionTab.isDisplayed();
    }

    public boolean clickActionTab() {
        if (!isActionTabDisplayed()) {
            return false;
        }
        actionTab.click();
        return true;
    }

}



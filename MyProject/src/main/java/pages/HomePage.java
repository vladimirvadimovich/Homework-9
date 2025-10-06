package pages;

import core.DriverManager;
import elements.Button;
import core.ConfigReader;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage extends BasePage{
    private static final String URL   = ConfigReader.get("base.url");

    private static final By ACTION_TAB = By.xpath(
            "//a[contains(@href,'/category/action?')]");

    private static final By DISCOUNT_SECTION = By.xpath(
            "//div[contains(@class,'home_pagecontent_ctn') and contains(@class,'no_mobile_margin')]//div[contains(@class,'content_hub_carousel_ctn')]");

    private static final By RIGHT_SELECTOR = By.xpath(
            "//div[@class='arrow right' and @data-usability='18']");
    private static final int MAX_ATTEMPTS = 20;


    private final Button actionTab = new Button(ACTION_TAB);
    private final Button discountSection = new Button(DISCOUNT_SECTION);
    private final Button rightSelector = new Button(RIGHT_SELECTOR);
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
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        for (int i = 0; i < MAX_ATTEMPTS; i++) {
            // Если таб уже виден — выходим
            if (actionTab.isDisplayed()) {
                return true;
            }

            // Ждём, пока стрелка будет кликабельна, и кликаем
            try {
                wait.until(ExpectedConditions.elementToBeClickable(RIGHT_SELECTOR));
                rightSelector.click();
            } catch (Exception e) {
                // можно логировать, но не ломать тест
            }
        }
        // после MAX_ATTEMPTS баннер так и не появился
        return actionTab.isDisplayed();
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



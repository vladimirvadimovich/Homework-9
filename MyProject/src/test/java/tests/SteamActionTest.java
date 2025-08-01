package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.ActionPage;
import pages.HomePage;
import utils.DriverSingleton;

public class SteamActionTest {


    @BeforeAll
    public static void setup() {

        // Инициализируем драйвер
        DriverSingleton.getDriver();
    }

    @Test
    public void testCs2InPopularActionGames() {
        HomePage home = new HomePage(DriverSingleton.getDriver());
        home.open();
        home.scrollToActionButton();
        home.goToActionCategory();

        ActionPage action = new ActionPage(DriverSingleton.getDriver());
        action.scrollToPopularSection();
        action.assertCs2IsDisplayed();
    }

    @AfterAll
    public static void tearDown() {
        DriverSingleton.quitDriver();
    }
}


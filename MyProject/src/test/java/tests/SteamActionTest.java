package tests;

import core.ConfigReader;
import core.DriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.ActionPage;

import pages.HomePage;

public class SteamActionTest {

    @BeforeEach
    void setUp() {

    }

    @Test
    void testCs2InPopularActionGames() {
        String browser = ConfigReader.get("browser");
        DriverManager.initDriver(browser, false);

        HomePage home = new HomePage();
        // 1) Открываем и проверяем, что страница действительно загрузилась
        boolean opened = home.open();
        Assertions.assertTrue(opened, "Не удалось открыть страницу либо неверный URL");

        // 2) Скроллим до раздела «Скидки и мероприятия»
        boolean discountVisible = home.scrollToDiscountSection();
        Assertions.assertTrue(discountVisible, "Не удалось отобразить 'Скидки и мероприятия'");

        // 3) Переходим в категорию «Экшен»
        boolean actionCategoryClicked = home.goToActionCategory();
        Assertions.assertTrue(actionCategoryClicked, "Не удалось отобразить или кликнуть по разделу 'Экшен'");

        ActionPage action = new ActionPage();
        // 4) Проверяем, что страница "Экшен" открылась
        boolean pageTitle = action.isPageOpened();
        Assertions.assertTrue(pageTitle, "Не удалось открыть страницу 'Экшены'");

        // 5) Скроллим до «Популярного»
        boolean popularVisible = action.scrollToPopularSection();
        Assertions.assertTrue(popularVisible, "Не удалось отобразить раздел 'Популярное'");

        // 6) Проверяем наличие плитки CS2
        boolean cs2Tile = action.isCs2TileDisplayed();
        Assertions.assertTrue(cs2Tile, "Не удалось отобразить игру Counter-Strike 2");
    }

    @AfterEach
    void tearDown() {
        DriverManager.quitDriver();
    }
}

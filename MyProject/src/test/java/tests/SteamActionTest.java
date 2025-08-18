package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.ActionPage;
import pages.BasePage;
import pages.HomePage;

public class SteamActionTest extends BasePage {
    @Test
    public void testCs2InPopularActionGames() {
        HomePage home = new HomePage();
        home.open();
        home.scrollToActionButton();
        Assertions.assertTrue(home.goToActionCategory(), "Не удалось отобразить или кликнуть по табу 'Экшен'");
        ActionPage action = new ActionPage();
        action.scrollToPopularSection();
        Assertions.assertTrue(action.isCs2TileDisplayed(), "CS2 не отображается в разделе Popular");
    }
}

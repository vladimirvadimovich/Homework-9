package elements;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

public class Dropdown extends BaseElement {
    public Dropdown(By locator) { super(locator); }

    public void selectByVisibleText(String text) {
        Select sel = new Select(get());
        sel.selectByVisibleText(text);
    }
    public void selectByValue(String value) {
        new Select(get()).selectByValue(value);
    }
}

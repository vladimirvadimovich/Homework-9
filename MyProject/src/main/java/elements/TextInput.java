package elements;

import elements.BaseElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class TextInput extends BaseElement {
    public TextInput(By locator) { super(locator); }

    public void type(String text) {
        WebElement el = get();
        el.clear();
        el.sendKeys(text);
    }
}

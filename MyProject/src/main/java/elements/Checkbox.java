package elements;

import org.openqa.selenium.By;

public class Checkbox extends BaseElement {
    public Checkbox(By locator) { super(locator); }

    public void check() {
        if (!get().isSelected()) click();
    }
    public void uncheck() {
        if (get().isSelected()) click();
    }
    public boolean isChecked() {
        return get().isSelected();
    }
}

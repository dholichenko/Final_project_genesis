package elements;

import org.openqa.selenium.By;

public class Field extends Element {
    public Field(By by) {
        super(by);
    }

    // заповнення sendKeys
    public void sendKeys(String text){
        compose().sendKeys(text);
    }

}

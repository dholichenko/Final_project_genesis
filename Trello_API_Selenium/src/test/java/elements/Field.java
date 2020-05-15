package elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Field extends Element {
    public Field(By by) {
        super(by);
    }

    // заповнення sendKeys
    public void sendKeys(String text){
        compose().sendKeys(text);
    }

    // виконання затирання текстового поля
    public void clear(){
        compose().clear();
    }

    // зчитування тексту
    public String getText(){
        return String.valueOf(compose().getText());
    }

//    // отримання останнього елементу
//    public WebElement composeLast(){
//        List<WebElement> elements = composeElements();
//        return elements.get(elements.size()-1);
//    }
//
//    // отримання першого елементу
//    public WebElement composeFirst(){
//        List<WebElement> elements = composeElements();
//        return elements.get(0);
//    }
}
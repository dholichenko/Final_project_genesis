package elements;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static conf.TestManager.getDriver;


public abstract class Element {
    protected By by;
    protected WebDriver driver = getDriver();

    public Element(By by){
        this.by = by;
    }


    WebElement compose(){
        return driver.findElement(by);
    }

//    List composeElements(){
//        List<WebElement> elements = driver.findElements(by);
//        return elements;
//    }

    public void click(){
        compose().click();
    }

    public boolean isExist(){
        try {
            compose();
            return true;
        }
        catch (NullPointerException e) {
            return false;
        }
        catch (NoSuchElementException  e) {
            return false;
        }


    }
}

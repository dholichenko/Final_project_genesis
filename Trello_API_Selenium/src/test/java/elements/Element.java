package elements;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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

    public void click(){
        compose().click();
    }

    public boolean isExist(){
        // якщо не знайде елемент, то буде exception. тому обробляємо це за допомогою try catch
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

    public By getSelector() {
        return by;
    }
}

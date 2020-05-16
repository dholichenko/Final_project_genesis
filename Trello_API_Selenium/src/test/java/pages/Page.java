package pages;

import elements.Element;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class Page {

    protected WebDriver driver;

    // створення конструктора
    public Page(WebDriver driver){
        this.driver = driver;
    }

    // реалізація методу для переходу на сторінку по url
    public void openPage(String url){
        driver.get(url);
    };

    protected void waitForVisibility(Element element) throws Error{
        new WebDriverWait(driver, 60)
                .until(ExpectedConditions.elementToBeClickable(element.getSelector()));
    }
}

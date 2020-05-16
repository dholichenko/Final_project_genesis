package pages;

import elements.Container;
import elements.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BoardsPage extends Page {

    public BoardsPage(WebDriver driver) {
        super(driver);
    }

    public Element getBoardCardByName(String boardName) {
        Container container = new Container(By.xpath("//div[@title=\"" + boardName + "\"]"));
        waitForVisibility(container);
        return new Container(By.xpath("//div[@title=\"" + boardName + "\"]"));
    }
}

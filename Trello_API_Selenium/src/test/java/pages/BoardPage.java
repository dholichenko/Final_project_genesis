package pages;

import elements.Container;
import elements.Element;
import elements.Field;
import elements.Link;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BoardPage extends Page{
    public BoardPage(WebDriver driver) {
        super(driver);
    }

    // Menu More
    private Link boardMenuMoreLink = new Link(By.cssSelector("a[class='board-menu-navigation-item-link js-open-more']"));

    // закрити дошку
    private Link boardClose = new Link(By.cssSelector("a[class='board-menu-navigation-item-link js-close-board']"));

    // в спливаючому вікні Закрити
    public Link boardConfirm = new Link(By.cssSelector("input[class='js-confirm full negate']"));

    // Безвозвратное удаление
    public Link boardQuietDelete = new Link(By.cssSelector("a[class='quiet js-delete']"));

    // для перевірки видаленої дошки
    public Container messageQuiet = new Container(By.cssSelector("div.big-message.quiet"));

    // копіювати список
    private Link copyList = new Link(By.cssSelector("a[class='js-copy-list']"));

    public Field newListInput = new Field(By.className("js-autofocus"));

    public Field checklistInput = new Field(By.id("id-checklist"));

    public Field checklistItemInput = new Field(By.className("js-new-checklist-item-input"));

    public Container checklistCloseButton = new Container(By.className("js-close-window"));

    private Container checklistIcon = new Container(By.className("js-checkitems-badge"));

    public Element getListMenuByName(String listName) {
        Container container = new Container(By.xpath("//h2[text()=\""+listName+"\"]//following-sibling::div"));
        waitForVisibility(container);
        return container;
    }

    public Field getListByTitle(String text) {
        return new Field(By.xpath("//h2[text()=\"" + text + "\"]"));
    }

    public Link getCopyList() {
        waitForVisibility(copyList);
        return copyList;
    }

    public Link getBoardMenuMoreLink() {
        waitForVisibility(boardMenuMoreLink);
        return boardMenuMoreLink;
    }

    public Link getBoardClose() {
        waitForVisibility(boardClose);
        return boardClose;
    }

    public Element getListCard() {
        Element container = new Container(By.className("list-card"));
        waitForVisibility(container);
        return container;
    }
    public Element getAddChecklistButton() {
        Element container = new Container(By.className("js-add-checklist-menu"));
        waitForVisibility(container);
        return container;
    }

    public Container getChecklistIcon() {
        waitForVisibility(checklistIcon);
        return checklistIcon;
    }
}

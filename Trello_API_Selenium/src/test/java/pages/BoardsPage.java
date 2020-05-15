package pages;

import elements.Container;
import elements.Field;
import elements.Link;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

// після створення класу Page, робимо наступний клас потомком цього класу - для того щоб мати перехід на сторінку по url
// Але вже конструктор не задовольняє умови (public BoardsPage(WebDriver driver){this.driver = driver;}), тому його потрібно переписати
public class BoardsPage extends Page{
    // Для того щоб було видно driver, то могли б зробити extends TestManger - це відкинули
    // Але напишемо конструктор
    //private WebDriver driver; // після назначення унаслідування класу від Page цяей рядок вже непотрібний
    // створення конструктора
    //public BoardsPage(WebDriver driver){
    //  this.driver = driver;
    //}
    // переписуємо конструктор
    public BoardsPage(WebDriver driver) {
        super(driver);
    }

    // Якщо при відкритті картки меню неактивне
    // Перевірка сховання меню
    public Container boardMenuHide = new Container(By.cssSelector("div.board-menu.js-fill-board-menu.hide"));
    // ... Menu
    public Link boardBtnMenu = new Link(By.cssSelector("a[class='board-header-btn mod-show-menu js-show-sidebar']"));

    // Menu More
    //public By boardMenuMoreLink = By.cssSelector("a[class='board-menu-navigation-item-link js-open-more']");
    public Link boardMenuMoreLink = new Link(By.cssSelector("a[class='board-menu-navigation-item-link js-open-more']"));

    // закрити дошку,
    //public By boardClose = By.cssSelector("a[class='board-menu-navigation-item-link js-close-board']");
    public Link boardClose = new Link(By.cssSelector("a[class='board-menu-navigation-item-link js-close-board']"));

    // в спливаючому вікні Закрити
    //public By boardConfirm = By.cssSelector("input[class='js-confirm full negate']");
    public Link boardConfirm = new Link(By.cssSelector("input[class='js-confirm full negate']"));
    // Безвозвратное удаление
    //driver.findElement(By.cssSelector("a[class='quiet js-delete']")).click();
    //public By boardQuietDelete = By.cssSelector("a[class='quiet js-delete']");
    public Link boardQuietDelete = new Link(By.cssSelector("a[class='quiet js-delete']"));

    // для перевірки видаленої дошки
    //public By messageQuiet = By.cssSelector("div[class='big-message quiet']");
    // Так як винесли елемент Container в окремий клас
    public Container messageQuiet = new Container(By.cssSelector("div.big-message.quiet"));

    //...
    public Link listMenuShown = new Link(By.cssSelector("#board > div:nth-child(1)>div.list.js-list-content>div.list-header.js-list-header.u-clearfix.is-menu-shown>div.list-header-extras"));
    //public Link listMenuShown = new Link(By.cssSelector("#content > div > div > div >div>div:nth-child(1)>div.list.js-list-content>div.list-header.js-list-header.u-clearfix.is-menu-shown>div.list-header-extras"));

    // копіювати список
    public Link copyList = new Link(By.cssSelector("a[class='js-copy-list']"));

    // кнопка Створити список
    public Link createList = new Link(By.cssSelector("input[class='primary wide js-submit']"));

    // Назва листа
    public Field listNameCreated = new Field(By.cssSelector("#board > div:nth-child(1) > div > div.list-header.js-list-header.u-clearfix.is-menu-shown > div.list-header-target.js-editing-target"));

    // Назва копії листа
    public Field listNameCopied = new Field(By.cssSelector("#board > div:nth-child(2) > div > div.list-header.js-list-header.u-clearfix.is-menu-shown > div.list-header-target.js-editing-target"));

}

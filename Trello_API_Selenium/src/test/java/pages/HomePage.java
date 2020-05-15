package pages;

import elements.Container;
import elements.Link;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

// після створення класу Page, робимо наступний клас потомком цього класу - для того щоб мати перехід на сторінку по url
// Але вже конструктор не задовольняє умови (public HomePage(WebDriver driver){this.driver = driver;}), тому його потрібно переписати
public class HomePage extends Page{
    // Для того щоб було видно driver, то могли б зробити extends TestManger - це відкинули
    // Напишемо конструктор
    //private WebDriver driver; // після назначення унаслідування класу від Page цяей рядок вже непотрібний
    // створення конструктора
    //public HomePage(WebDriver driver){
    //  this.driver = driver;
    //}
    // переписуємо конструктор
    public HomePage(WebDriver driver) {
        super(driver);
    }

    public String url = "https://trello.com/";
    //public String title = "Доски | Trello";
    //public String title1 = "Trello";
    public String title = "input[id='user']";

    // В класі TrelloTests - driver.findElement(By.cssSelector("a[href='/login']")) - наступна помилка
    // NullPointerException: коли робимо driver.findElement в цей момент драйвер пробує знайти елемент по вказаному локатору.
    // він почне це робити після того як створемо інстанс класу HomePage. Так як ми його створюємо на початку, то немає ще елементу loginLink, так як ми ще не перейшли на HomePage
    //public WebElement loginLink = driver.findElement(By.cssSelector("a[href='/login']"));
    // обхід цієї проблеми:
    // 1) можна зберігати в змінній String. Мінус: коли код зберігаємо просто в стрінгу, тоді невідкриваючи клас HomePage, ми не дізнаємось якого типу цей елемент
    // public String loginLink = "a[href='/login']";
    // 2) Створюємо обєкт типу By
    //public By loginLinkLocator = By.cssSelector("a[href='/login']");
    // змінемо з By на Link
    //public Link loginLinkLocator = new Link(By.cssSelector("a[href='/login']"));
    // перейменуємо з loginLinkLocator на loginLink, так як це вже не локатор, а елемент лінк
    public Link loginLink = new Link(By.cssSelector("a[href='/login']"));

    // для перевірки
    // Створюємо обєкт типу By
    //public By homeLinkLocator = By.cssSelector("a[href='/home']");
    // Так як винесли елемент Link в окремий клас
    public Link homeLinkLocator = new Link(By.cssSelector("a[href='/home']"));

}









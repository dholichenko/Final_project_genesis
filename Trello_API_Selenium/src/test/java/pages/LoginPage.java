package pages;

import elements.Field;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

// після створення класу Page, робимо наступний клас потомком цього класу  - для того щоб мати перехід на сторінку по url
// Але вже конструктор не задовольняє умови (public LoginPage(WebDriver driver){this.driver = driver;}), тому його потрібно переписати
public class LoginPage extends Page {
    // Для того щоб було видно driver, то могли б зробити extends TestManger - це відкинули
    // Напишемо конструктор
    //private WebDriver driver; // після назначення унаслідування класу від Page цей рядок вже непотрібний
    // створення конструктора
    //public LoginPage(WebDriver driver){
    //  this.driver = driver;
    //}
    // переписуємо конструктор
    public LoginPage(WebDriver driver) {
        super(driver);
    }


    // В класі TrelloTests - driver.findElement(By.cssSelector("a[href='/login']")) - наступна помилка
    // NullPointerException: коли робимо driver.findElement в цей момент драйвер пробує знайти елемент по вказаному локатору.
    // він почне це робити після того як створемо інстанс класу HomePage. Так як ми його створюємо на початку, то немає ще елементу loginLink, так як ми ще не перейшли на HomePage
    //public WebElement emailField = driver.findElement(By.cssSelector("input[id='user']"));
    // обхід цієї проблеми:
    // 1) можна зберігати в змінній String. Мінус: коли код зберігаємо просто в стрінгу, тоді невідкриваючи клас HomePage, ми не дізнаємось якого типу цей елемент
    // public String emailField = ""input[id='user']"";
    // 2) Створюємо обєкт типу By
    //public By emailField = By.cssSelector("input[id='user']");
    //public By passwordField = By.cssSelector("input[id='password']");
    // Так як винесли елемент Field в окремий клас
    public Field emailField = new Field(By.cssSelector("input[id='user']"));
    public Field passwordField = new Field(By.cssSelector("input[id='password']"));

}

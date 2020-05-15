import conf.TestManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.BoardsPage;
import pages.HomePage;
import pages.LoginPage;

import java.io.IOException;


public class CopyList extends TestManager {
// змінні ідентифікатори, які отримуємо після створення сутності
    private String  idBoard;
    private String  idList;
    private String  idCard;

    private String  urlBoard;

    // Створення дошки
    public void checkCreateBoard() throws IOException {
        // щоб змогли відправляти запити, створюємо екземпляру класу RetrofitBuilder
        RetrofitBuilder retrofitBuilder = new RetrofitBuilder();

        // створення параметрів для методу createBoard з інтерфейсу TrelloApi
        Board board = new Board(); // передаємо цей параметр, щоб Retrofit отримав ключ та токен, для виконання запиту
        String name = "Board CopyList " + System.currentTimeMillis(); // Назва дошки

        Board createdBoard =
                retrofitBuilder.getTrelloApi().createBoard(board, name).execute().body();

        // ініціалізація змінної idBoard
        idBoard = createdBoard.getId();

        urlBoard = createdBoard.getUrl();

        // Перевірка: перевірка імені - чи створилась дошка з зазначеним іменем
        // Зі створеної дошки отримуємо імя за допомогою createdBoard.getName() та порівнюємо зі змінною name
        Assert.assertEquals(createdBoard.getName(), name);
    }

    // Створення листа в дошці
    public void classCreateList() throws IOException {
        // щоб змогли відправляти запити, створюємо екземпляру класу RetrofitBuilder
        RetrofitBuilder retrofitBuilder = new RetrofitBuilder();
        // створення параметрів для методу createList з інтерфейсу TrelloApi
        List list = new List(); // передаємо цей параметр, щоб Retrofit отримав ключ та токен, для виконання запиту
        String name = "New list " + System.currentTimeMillis(); // Назва листа

        List createdList =
                retrofitBuilder.getTrelloApi()
                        .createList(list, idBoard, name).execute().body();

        // ініціалізація змінної idList
        idList = createdList.getListId();

        // Перевірка: перевірка імені - чи створився лист з зазначеним іменем
        // Зі створеного листа отримуємо імя за допомогою createdList.getName() та порівнюємо зі змінною name
        Assert.assertEquals(createdList.getName(), name);
    }

    // Створення картки в листі
    public void classCreateCard() throws IOException {
        // щоб змогли відправляти запити, створюємо екземпляру класу RetrofitBuilder
        RetrofitBuilder retrofitBuilder = new RetrofitBuilder();
        // створення параметрів для методу createCard з інтерфейсу TrelloApi
        Card card = new Card(); // передаємо цей параметр, щоб Retrofit отримав ключ та токен, для виконання запиту
        String name = "New card" + System.currentTimeMillis(); // Назва картки

        Card createdCard =
                retrofitBuilder.getTrelloApi()
                        .createCard(card, idList, name).execute().body();

        // ініціалізація змінної idCard
        idCard = createdCard.getCardId();

        // Перевірка: перевірка імені - чи створилась картка з зазначеним іменем
        // Зі створеної картки отримуємо імя за допомогою createdCard.getName() та порівнюємо зі змінною name
        Assert.assertEquals(createdCard.getName(), name);
    }

    // 6. Видалення дошки
    public void classDeleteBoard() throws IOException {
        // щоб змогли відправляти запити, створюємо екземпляру класу RetrofitBuilder
        RetrofitBuilder retrofitBuilder = new RetrofitBuilder();
        // створення параметрів для методу deleteBoard з інтерфейсу TrelloApi
        Board board = new Board(); // передаємо цей параметр, щоб Retrofit отримав ключ та токен, для виконання запиту

        // так як метод deleteBoard повертає статус, то і присвоюємо його змінній.
        int code =
                retrofitBuilder.getTrelloApi()
                        .deleteBoard(idBoard, board.getKey(), board.getToken()).execute().code();

        // Перевірка: перевірка статусу - чи було видалено дошку - статус 200
        Assert.assertEquals(code, 200);
    }

    @Test
    public void copyListSelenium() throws IOException {
        HomePage homePage = new HomePage(driver);
        // Перехід на сторінку
        homePage.openPage(homePage.url);

        // Клік на "Вхід": знайти елемент та клікнути на нього
        homePage.loginLink.click();

        // Заповнення полів "Електронна пошта" та "Пароль": знайти елемент, очистити його та ввести текст
        LoginPage loginPage = new LoginPage(driver);
        loginPage.emailField.clear();
        loginPage.emailField.sendKeys("projectqaschool@gmail.com");

        // "Пароль"
        // Після введення емейлу та пароля клік ENTER
        loginPage.passwordField.clear();
        loginPage.passwordField.sendKeys("projectschool3" + Keys.ENTER);

        if(!driver.findElement(By.cssSelector("div.home-sticky-container")).isEnabled()) {
            System.out.println("не залогинились");
        }

        // створення екземпляру класу BoardsPage
        BoardsPage boardsPage = new BoardsPage(driver);

        // створення нової дошки
        checkCreateBoard();

        // відкриття дошки
        boardsPage.openPage(urlBoard);


        // створення листа в дошці
        classCreateList();
        // створення 2х карток в листі
        for(int i = 1; i <= 2; i++){
            classCreateCard();
        }

        // натиснення ... першого листа зліва
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#board > div:nth-child(1)>div.list.js-list-content>div.list-header.js-list-header.u-clearfix.is-menu-shown>div.list-header-extras"))).click();
        //boardsPage.listMenuShown.click();

        // копіювати список
        boardsPage.copyList.click();

        // клік на кнопку створити список
        boardsPage.createList.click();

        // перевірка
        Assert.assertTrue((boardsPage.listNameCreated.getText()).equals(boardsPage.listNameCopied.getText()));

        // видалення дошки
        classDeleteBoard();
    }
}

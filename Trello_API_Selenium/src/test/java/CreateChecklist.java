import conf.TestManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.BoardsPage;
import pages.ChecklistPage;
import pages.HomePage;
import pages.LoginPage;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class CreateChecklist extends TestManager {
        // змінні ідентифікатори, які отримуємо після створення сутності
        private String  idBoard;
        private String  idList;

        private String  urlCard;
        private String  checklistName;

    // Створення дошки
    public void checkCreateBoard() throws IOException {
        // щоб змогли відправляти запити, створюємо екземпляру класу RetrofitBuilder
        RetrofitBuilder retrofitBuilder = new RetrofitBuilder();

        // створення параметрів для методу createBoard з інтерфейсу TrelloApi
        Board board = new Board(); // передаємо цей параметр, щоб Retrofit отримав ключ та токен, для виконання запиту
        String name = "Board CreateChecklist " + System.currentTimeMillis(); // Назва дошки

        Board createdBoard =
                retrofitBuilder.getTrelloApi().createBoard(board, name).execute().body();

        // ініціалізація змінної idBoard
        idBoard = createdBoard.getId();

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

        // так як метод createList повертає List, то і присвоюємо її змінній.
        // Тобто JSON, який повернеться з метода, записуємо його в цю змінну
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

        // так як метод createCard повертає Card, то і присвоюємо її змінній.
        // Тобто JSON, який повернеться з метода, записуємо його в цю змінну
        Card createdCard =
                retrofitBuilder.getTrelloApi()
                        .createCard(card, idList, name).execute().body();

        urlCard = createdCard.getUrl();

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
    public void createCheklistSelenium() throws IOException {

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

        // створення екземпляру класу BoardsPage
        BoardsPage boardsPage = new BoardsPage(driver);

        if(!driver.findElement(By.cssSelector("div.home-sticky-container")).isEnabled()) {
            System.out.println("не залогинились");
        }

        // створення нової дошки
        checkCreateBoard();
        // створення листа в дошці
        classCreateList();
        // створення картки в листі
        classCreateCard();

        boardsPage.openPage(urlCard);

        ChecklistPage checklistPage = new ChecklistPage(driver);

        // клік в боковому меню на Чекліст
        //driver.findElement(By.cssSelector("a[class='button-link js-add-checklist-menu']")).click();
        checklistPage.addChecklist.click();

        // введення назви чекліста та натиснення ENTER
        checklistName = "New checklist " + System.currentTimeMillis();
        checklistPage.checklistField.sendKeys(checklistName + Keys.ENTER);

        // перевірка
        Assert.assertTrue(checklistPage.checklistEx.getText().equals(checklistName));
        // якби було багато чеклістів та потрібно взяти новостворений
        //Assert.assertTrue(checklistPage.checklistEx.composeLast().getText().equals(checklistName));

        classDeleteBoard();
    }

}

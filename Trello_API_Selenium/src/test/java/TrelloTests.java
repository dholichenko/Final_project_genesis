import conf.TestManager;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.BoardsPage;
import pages.ChecklistPage;
import pages.HomePage;
import pages.LoginPage;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;

// при запуску цього класу, запускається Selenium WebDriver -
// це сервер, який відправляє на ChromeDriver запити, які в свою чергу піднімають Chrome та вже інтерпретують команди

// Методи тестів повинні бути публічними та void - немає зовнішніх результатів тесту.
// Тест атомарний, дані нікуди не передаються, він все в собі інкапсулює. Тобто непотрібно робити ніяких ланцюжків

public class TrelloTests extends TestManager {
    // змінні ідентифікатори, які отримуємо після створення сутності
    private String  idBoard;
    private String  idList;
    private String  idCard;
    private String  idChecklist;

    private String  urlBoard;
    private String  listNameEx;
    private String  urlCard;
    public String  checklistName;
    public String  boardNameEx;
/*

    // Створення дошки
    // запуск до всіх тестових методів
    @BeforeTest
    public void checkCreateBoard() throws IOException {
        // щоб змогли відправляти запити, створюємо екземпляру класу RetrofitBuilder
        RetrofitBuilder retrofitBuilder = new RetrofitBuilder();

        // створення параметрів для методу createBoard з інтерфейсу TrelloApi
        Board board = new Board(); // передаємо цей параметр, щоб Retrofit отримав ключ та токен, для виконання запиту
        String name = "New board " + System.currentTimeMillis(); // Назва дошки

        // так як метод createBoard повертає Board, то і присвоюємо її змінній.
        // Тобто JSON, який повернеться з метода, записуємо його в цю змінну
        Board createdBoard =
                // Від екземпляру класа RetrofitBuilder можемо викликати інтерфейс з методами
                // В даному випадку метод createBoard з параметрами board, name
                // execute() - для виконання запиту, body() - необхідно тільки боді

                // Говоримо retrofitBuilder, що хочемо створити запит, який беремо з getTrelloApi,
                // візьмемо клас TrelloApi і від нього будемо тягнути методи
                // В даному випадку метод createBoard. Виконуємо його та беремо тільки боді
                retrofitBuilder.getTrelloApi().createBoard(board, name).execute().body();

        // ініціалізація змінної idBoard
        idBoard = createdBoard.getId();

        urlBoard = createdBoard.getUrl();

        boardNameEx = createdBoard.getName();

        // Перевірка: перевірка імені - чи створилась дошка з зазначеним іменем
        // Зі створеної дошки отримуємо імя за допомогою createdBoard.getName() та порівнюємо зі змінною name
        Assert.assertEquals(boardNameEx, name);
    }

 */
/* -- це з лекції Ігора, в проекті він непотрібний
    @Test
    public void openHomePage() throws IOException {
        HomePage homePage = new HomePage(driver);

        // Перехід на сторінку
        //driver.get("https://trello.com/");
        // так як винесли посилання в змінну url в HomePage
        //driver.get(homePage.url);
        // так як створили метод openPage в Page
        homePage.openPage(homePage.url);

        // отримуємо заголовок відкритої сторінки
        //String title = driver.getTitle();
        // так як створили метод getTitle в Page
        String title = homePage.getTitle();

        // !!! Змінити !!!
        //Assertions.assertEquals(title, "Trello");
        //Assertions.assertEquals(title, homePage.title);
        //Assert.assertTrue(driver.findElement(homePage.homeLinkLocator).isEnabled());
        // так як для перевірки наявності елемента в Element створили окремий метод isExist
        Assert.assertTrue(homePage.homeLinkLocator.isExist());

    }

    @Test
    public void loginTest(){
        HomePage homePage = new HomePage(driver);
        // Перехід на сторінку
        //driver.get("https://trello.com/");
        // так як винесли посилання в змінну url в HomePage
        //driver.get(homePage.url);
        // так як створили метод openPage в Page
        homePage.openPage(homePage.url);

        // Клік на "Вхід": знайти елемент та клікнути на нього
        //driver.findElement(By.cssSelector("a[href='/login']")).click();
        // пробуємо напряму отримати loginLink з HomePage
        //homePage.loginLink.click();
        // cssSelector зберігаємо як обєкт типу By (клас HomePage) = loginLinkLocator
        //driver.findElement(homePage.loginLinkLocator).click();
        // після того як виконали деякі дії в HomePage
        homePage.loginLink.click();

        // Заповнення полів "Електронна пошта" та "Пароль": знайти елемент, очистити його та ввести текст
        // "Електронна пошта"
        // cssSelector зберігаємо як обєкт типу By (клас HomePage) = emailField
        //driver.findElement(By.cssSelector("input[id='user']")).clear();
        //driver.findElement(By.cssSelector("input[id='user']")).sendKeys("projectqaschool@gmail.com");
        // створення екземпляру класу LoginPage
        LoginPage loginPage = new LoginPage(driver);
        //driver.findElement(loginPage.emailField).clear();
        //driver.findElement(loginPage.emailField).sendKeys("projectqaschool@gmail.com");
        // В LoginPage на driver.findElement зробили обєкти класу Field
        loginPage.emailField.clear();
        loginPage.emailField.sendKeys("projectqaschool@gmail.com");

        // "Пароль"
        // Після введення емейлу та пароля клік ENTER
        //driver.findElement(By.cssSelector("input[id='password']")).clear();
        //driver.findElement(By.cssSelector("input[id='password']")).sendKeys("projectschool3" + Keys.ENTER);
        // В LoginPage lkz By.cssSelector зробили By
        // driver.findElement(loginPage.passwordField).clear();
        //driver.findElement(loginPage.passwordField).sendKeys("projectschool3" + Keys.ENTER);
        // В LoginPage на driver.findElement зробили обєкти класу Field
        loginPage.passwordField.clear();
        loginPage.passwordField.sendKeys("projectschool3" + Keys.ENTER);

        // створення екземпляру класу BoardsPage
        BoardsPage boardsPage = new BoardsPage(driver);
        // allBoardsContainer
        //Assert.assertTrue(driver.findElement(By.cssSelector("div.all-boards")).isEnabled());
        // // BoardPage на driver.findElement зробили обєкти класу Container
        //Assert.assertTrue(boardsPage.allBoardsContainer.isEnabled());
        // так як для перевірки наявності елемента в Element створили окремий метод isExist
        Assert.assertTrue(boardsPage.allBoardsContainer.isExist());
    }
*/
/*
    // Створення листа в дошці
    //@Test(priority = 1) // поміщення в группу createChecklist
    public void classCreateList() throws IOException {
        // щоб змогли відправляти запити, створюємо екземпляру класу RetrofitBuilder
        RetrofitBuilder retrofitBuilder = new RetrofitBuilder();
        // створення параметрів для методу createList з інтерфейсу TrelloApi
        List list = new List(); // передаємо цей параметр, щоб Retrofit отримав ключ та токен, для виконання запиту
        String name = "New list " + System.currentTimeMillis(); // Назва листа

        // так як метод createList повертає List, то і присвоюємо її змінній.
        // Тобто JSON, який повернеться з метода, записуємо його в цю змінну
        List createdList =
                // Від екземпляру класа RetrofitBuilder можемо викликати інтерфейс з методами
                // В даному випадку метод createList з параметрами list (з нього отримуємо key, token), idBoard, name
                // execute() - для виконання запиту, body() - необхідно тільки боді

                // Говоримо retrofitBuilder, що хочемо створити запит, який беремо з getTrelloApi,
                // візьмемо клас TrelloApi і від нього будемо тягнути методи
                // В даному випадку метод createList. Виконуємо його та беремо тільки боді
                retrofitBuilder.getTrelloApi()
                        .createList(list, idBoard, name).execute().body();

        // ініціалізація змінної idList
        idList = createdList.getListId();

        listNameEx = createdList.getListName();

        // Перевірка: перевірка імені - чи створився лист з зазначеним іменем
        // Зі створеного листа отримуємо імя за допомогою createdList.getName() та порівнюємо зі змінною name
        Assert.assertEquals(createdList.getName(), name);
    }

    @DataProvider(name = "data-provider")
    public Object[][] dataProviderMethod() {
        return new Object[][] { {"1"}, {"2"}};
    }

    // Створення картки в листі
    //@Test(dependsOnMethods={"classCreateList"}, dataProvider = "data-provider", priority = 2) // поміщення в группу createChecklist, даний тестовий метод виконуватиметься 2 рази
    public void classCreateCard(String data) throws IOException {
        // щоб змогли відправляти запити, створюємо екземпляру класу RetrofitBuilder
        RetrofitBuilder retrofitBuilder = new RetrofitBuilder();
        // створення параметрів для методу createCard з інтерфейсу TrelloApi
        Card card = new Card(); // передаємо цей параметр, щоб Retrofit отримав ключ та токен, для виконання запиту
        String name = "New card" + data; // Назва картки

        // так як метод createCard повертає Card, то і присвоюємо її змінній.
        // Тобто JSON, який повернеться з метода, записуємо його в цю змінну
        Card createdCard =
                // Від екземпляру класа RetrofitBuilder можемо викликати інтерфейс з методами
                // В даному випадку метод createCard з параметрами card (з нього отримуємо key, token), idList, name
                // execute() - для виконання запиту, body() - необхідно тільки боді

                // Говоримо retrofitBuilder, що хочемо створити запит, який беремо з getTrelloApi,
                // візьмемо клас TrelloApi і від нього будемо тягнути методи
                // В даному випадку метод createCard. Виконуємо його та беремо тільки боді
                retrofitBuilder.getTrelloApi()
                        .createCard(card, idList, name).execute().body();

        // ініціалізація змінної idCard
        idCard = createdCard.getCardId();

        urlCard = createdCard.getUrl();

        // Перевірка: перевірка імені - чи створилась картка з зазначеним іменем
        // Зі створеної картки отримуємо імя за допомогою createdCard.getName() та порівнюємо зі змінною name
        Assert.assertEquals(createdCard.getName(), name);
    }

    // 18. Створення чекліста в картці
   // @Test(dependsOnMethods={"classCreateCard"}, priority = 3) // запуск після виконання цсіх методів групи createChecklist
    public void classCreateChecklist() throws IOException {
        // щоб змогли відправляти запити, створюємо екземпляру класу RetrofitBuilder
        RetrofitBuilder retrofitBuilder = new RetrofitBuilder();
        // створення параметрів для методу createChecklist з інтерфейсу TrelloApi
        Checklist checklist = new Checklist(); // передаємо цей параметр, щоб Retrofit отримав ключ та токен, для виконання запиту
        String name = "New checklist " + System.currentTimeMillis(); // Назва чекліста

        // так як метод createChecklist повертає Checklist, то і присвоюємо її змінній.
        // Тобто JSON, який повернеться з метода, записуємо його в цю змінну
        Checklist createdChecklist =
                // Від екземпляру класа RetrofitBuilder можемо викликати інтерфейс з методами
                // В даному випадку метод createChecklist з параметрами checklist (з нього отримуємо key, token), idCard, name
                // execute() - для виконання запиту, body() - необхідно тільки боді

                // Говоримо retrofitBuilder, що хочемо створити запит, який беремо з getTrelloApi,
                // візьмемо клас TrelloApi і від нього будемо тягнути методи
                // В даному випадку метод createChecklist. Виконуємо його та беремо тільки боді
                retrofitBuilder.getTrelloApi()
                        .createChecklist(checklist, idCard, name).execute().body();

        // ініціалізація змінної idChecklist
        idChecklist = createdChecklist.getChecklistId();

        checklistName = createdChecklist.getChecklistName();

        // Перевірка: перевірка імені - чи створився чекліст з зазначеним іменем
        // Зі створеного чекліста отримуємо імя за допомогою createdChecklist.getName() та порівнюємо зі змінною name
        Assert.assertEquals(createdChecklist.getName(), name);
    }

    @Test(priority = 4)
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
            classCreateCard(String.valueOf(i));
        }

        // ...
        //driver.findElement(By.cssSelector("#content > div > div > div >div>div:nth-child(1)>div.list.js-list-content>div.list-header.js-list-header.u-clearfix.is-menu-shown>div.list-header-extras")).click();
        boardsPage.listMenuShown.click();
        //"#board > div:nth-child(1)>div.list.js-list-content>div.list-header.js-list-header.u-clearfix.is-menu-shown>div.list-header-extras"

        // копіювати список
        //driver.findElement(By.cssSelector("a[class='js-copy-list']")).click();
        boardsPage.copyList.click();

        // Створити список
        //driver.findElement(By.cssSelector("input[class='primary wide js-submit']")).click();
        boardsPage.createList.click();

        // перевірка
//        Assert.assertTrue(
//            (driver.findElement(By.cssSelector("#board > div:nth-child(1) > div > div.list-header.js-list-header.u-clearfix.is-menu-shown > div.list-header-target.js-editing-target")).getText())
//                .equals
//            (driver.findElement(By.cssSelector("#board > div:nth-child(2) > div > div.list-header.js-list-header.u-clearfix.is-menu-shown > div.list-header-target.js-editing-target")).getText())
//        );

        Assert.assertTrue((boardsPage.listNameCreated.getText()).equals(boardsPage.listNameCopied.getText()));
    }

    @Test(priority = 5)
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

        // створення нової дошки
        checkCreateBoard();
        // створення листа в дошці
        classCreateList();
        // створення картки в листі
        classCreateCard("New card " + System.currentTimeMillis());

        boardsPage.openPage(urlCard);

        ChecklistPage checklistPage = new ChecklistPage(driver);

        // клік на Чекліст
        //driver.findElement(By.cssSelector("a[class='button-link js-add-checklist-menu']")).click();
        checklistPage.addChecklist.click();

        // введення назви чекліста та натиснення ENTER
        checklistName = "New checklist " + System.currentTimeMillis();
        //driver.findElement(By.cssSelector("input[id='id-checklist']")).sendKeys("New checklist " + System.currentTimeMillis() + Keys.ENTER);
        checklistPage.checklistField.sendKeys(checklistName + Keys.ENTER);

        // !!! замінити
        // перевірка
        //Assert.assertTrue(driver.findElement(By.xpath ("//div[@class='checklist']//h3[text()='" + checklistName + "']")).isEnabled());
        // перевірка по імені
        //Assert.assertTrue((
        //        driver.findElement(By.cssSelector("#chrome-container > div > div > div  > div > div > div > div:nth-child(1) > div.window-module-title.window-module-title-no-divider.u-clearfix.ed.grab > div.editable.non-empty.checklist-title > .current.hide-on-edit")).getText())
        //        .equals(checklistName));
        Assert.assertTrue(checklistPage.checklistEx.getText().equals(checklistName));

    }

    // 6. Видалення дошки
    @Test(priority = 4)
    public void classDeleteBoard() throws IOException {
        // щоб змогли відправляти запити, створюємо екземпляру класу RetrofitBuilder
        RetrofitBuilder retrofitBuilder = new RetrofitBuilder();
        // створення параметрів для методу deleteBoard з інтерфейсу TrelloApi
        Board board = new Board(); // передаємо цей параметр, щоб Retrofit отримав ключ та токен, для виконання запиту

        // так як метод deleteBoard повертає статус, то і присвоюємо його змінній.
        int code =
                // Від екземпляру класа RetrofitBuilder можемо викликати інтерфейс з методами
                // В даному випадку метод deleteBoard з параметрами id, key, token
                // execute() - для виконання запиту, code() - необхідно тільки code

                // Говоримо retrofitBuilder, що хочемо створити запит, який беремо з getTrelloApi,
                // візьмемо клас TrelloApi і від нього будемо тягнути методи
                // В даному випадку метод deleteBoard. Виконуємо його та беремо тільки code
                retrofitBuilder.getTrelloApi()
                        .deleteBoard(idBoard, board.getKey(), board.getToken()).execute().code();

        // Перевірка: перевірка статусу - чи було видалено дошку - статус 200
        Assert.assertEquals(code, 200);
    }

    @Test(priority = 5)
    public void deleteBoardSelenium() throws IOException {
        HomePage homePage = new HomePage(driver);
        // Перехід на сторінку
        //driver.get("https://trello.com/");
        // так як винесли посилання в змінну url в HomePage
        //driver.get(homePage.url);
        // так як створили метод openPage в Page
        homePage.openPage(homePage.url);

        // Клік на "Вхід": знайти елемент та клікнути на нього
        //driver.findElement(By.cssSelector("a[href='/login']")).click();
        // пробуємо напряму отримати loginLink з HomePage
        //homePage.loginLink.click();
        // cssSelector зберігаємо як обєкт типу By (клас HomePage) = loginLinkLocator
        //driver.findElement(homePage.loginLinkLocator).click();
        // після того як виконали деякі дії в HomePage
        homePage.loginLink.click();

        // Заповнення полів "Електронна пошта" та "Пароль": знайти елемент, очистити його та ввести текст
        // "Електронна пошта"
        // cssSelector зберігаємо як обєкт типу By (клас HomePage) = emailField
        //driver.findElement(By.cssSelector("input[id='user']")).clear();
        //driver.findElement(By.cssSelector("input[id='user']")).sendKeys("projectqaschool@gmail.com");
        // створення екземпляру класу LoginPage
        LoginPage loginPage = new LoginPage(driver);
        //driver.findElement(loginPage.emailField).clear();
        //driver.findElement(loginPage.emailField).sendKeys("projectqaschool@gmail.com");
        // В LoginPage на driver.findElement зробили обєкти класу Field
        loginPage.emailField.clear();
        loginPage.emailField.sendKeys("projectqaschool@gmail.com");

        // "Пароль"
        // Після введення емейлу та пароля клік ENTER
        //driver.findElement(By.cssSelector("input[id='password']")).clear();
        //driver.findElement(By.cssSelector("input[id='password']")).sendKeys("projectschool3" + Keys.ENTER);
        // В LoginPage для By.cssSelector зробили By
        // driver.findElement(loginPage.passwordField).clear();
        //driver.findElement(loginPage.passwordField).sendKeys("projectschool3" + Keys.ENTER);
        // В LoginPage на driver.findElement зробили обєкти класу Field
        loginPage.passwordField.clear();
        loginPage.passwordField.sendKeys("projectschool3" + Keys.ENTER);

        // створення екземпляру класу BoardsPage
        BoardsPage boardsPage = new BoardsPage(driver);

        // створення нової дошки
        checkCreateBoard();

        // перехід в блок "Дошки"
        //driver.findElement(By.cssSelector("a[href='/projectschool14/boards']")).click();
        // В BoardsPage для By.cssSelector зробили By
        //driver.findElement(boardsPage.allBoards).click();
        // В BoardsPage на driver.findElement зробили обєкти класу Link
        //boardsPage.allBoards.click(); // при натисненні на Home переходимо на сторінку boards

        // Відкриття дошки
        //driver.findElement(By.cssSelector("a[class='board-tile'][href='/b/lw1XCtO5/new-board']")).click();
        // В BoardsPage для By.cssSelector зробили By
        //driver.findElement(boardsPage.boardLink).click();
        // В BoardsPage на driver.findElement зробили обєкти класу Link
        //boardsPage.boardLink.click();
        boardsPage.openPage(urlBoard);

        // Menu - відкривається автоматично
        //driver.findElement(By.cssSelector("a[class='board-header-btn mod-show-menu js-show-sidebar']")).click();
        // Menu More
        //driver.findElement(By.cssSelector("a[class='board-menu-navigation-item-link js-open-more']")).click();
        boardsPage.boardMenuMoreLink.click();
        // Закрити дошку
        //driver.findElement(By.cssSelector("a[class='board-menu-navigation-item-link js-close-board']")).click();
        boardsPage.boardClose.click();
        // в спливаючому вікні Закрити
        //driver.findElement(By.cssSelector("input[class='js-confirm full negate']")).click();
        boardsPage.boardConfirm.click();
        // Безвозвратное удаление
        //driver.findElement(By.cssSelector("a[class='quiet js-delete']")).click();
        boardsPage.boardQuietDelete.click();
        // Удалить
        //driver.findElement(By.cssSelector("input[class='js-confirm full negate']")).click();
        boardsPage.boardConfirm.click();

        // messageQuiet
        //Assert.assertTrue(driver.findElement(By.cssSelector("div[class='big-message quiet']")).isEnabled());
        // BoardPage на driver.findElement зробили обєкти класу Container
        //Assert.assertTrue(boardsPage.messageQuiet.isEnabled());
        // так як для перевірки наявності елемента в Element створили окремий метод isExist
        Assert.assertTrue(boardsPage.messageQuiet.isExist());

        Assert.assertFalse(driver.findElements(By.xpath("//div[@title='" + boardNameEx + "']")).isEmpty());

    }

 */
}

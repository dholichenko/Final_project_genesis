import conf.TestManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.BoardsPage;
import pages.HomePage;
import pages.LoginPage;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class DeleteBoard extends TestManager {

    private String  urlBoard;

    // Створення дошки
    public void checkCreateBoard() throws IOException {
        // щоб змогли відправляти запити, створюємо екземпляру класу RetrofitBuilder
        RetrofitBuilder retrofitBuilder = new RetrofitBuilder();

        // створення параметрів для методу createBoard з інтерфейсу TrelloApi
        Board board = new Board(); // передаємо цей параметр, щоб Retrofit отримав ключ та токен, для виконання запиту
        String name = "Board DeleteBoard" + System.currentTimeMillis(); // Назва дошки

        Board createdBoard =
                retrofitBuilder.getTrelloApi().createBoard(board, name).execute().body();

        urlBoard = createdBoard.getUrl();

        // Перевірка: перевірка імені - чи створилась дошка з зазначеним іменем
        // Зі створеної дошки отримуємо імя за допомогою createdBoard.getName() та порівнюємо зі змінною name
        Assert.assertEquals(createdBoard.getName(), name);
    }

    @Test
    public void deleteBoardSelenium() throws IOException {
        HomePage homePage = new HomePage(driver);
        // Перехід на сторінку
        homePage.openPage(homePage.url);

        // Клік на "Вхід": знайти елемент та клікнути на нього
        homePage.loginLink.click();

        // Заповнення полів "Електронна пошта" та "Пароль": знайти елемент, очистити його та ввести текст
        // "Електронна пошта"
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

        // Відкриття дошки

        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        boardsPage.openPage(urlBoard);

        // Menu буває не відкривається автоматично при відкритті картки
        // якщо меню не активоване, то натиснути на "... Меню"
//        if(boardsPage.boardMenuHide.isExist()){
//            boardsPage.boardBtnMenu.click();
//        };

        // Menu More
        boardsPage.boardMenuMoreLink.click();
        // Закрити дошку
        boardsPage.boardClose.click();
        // в спливаючому вікні Закрити
        boardsPage.boardConfirm.click();
        // Безвозвратное удаление
        boardsPage.boardQuietDelete.click();
        // Удалить
        boardsPage.boardConfirm.click();

        // Перевірка: наявність контейнеру messageQuiet
        Assert.assertTrue(boardsPage.messageQuiet.isExist());

        //Assert.assertFalse(driver.findElements(By.xpath("//div[@title='" + boardNameEx + "']")).isEmpty());

    }
}

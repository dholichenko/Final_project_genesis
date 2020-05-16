import conf.TestManager;
import conf.TrelloCredentials;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.BoardPage;
import pages.BoardsPage;
import pages.HomePage;
import pages.LoginPage;

import java.io.IOException;

public class DeleteBoard extends TestManager {

    private String  urlBoard;
    private String  boardName;

    // Створення дошки
    public void checkCreateBoard() throws IOException {
        // щоб змогли відправляти запити, створюємо екземпляру класу RetrofitBuilder
        RetrofitBuilder retrofitBuilder = new RetrofitBuilder();

        Board board = new Board();
        String name = "Board DeleteBoard" + System.currentTimeMillis();

        Board createdBoard =
                retrofitBuilder.getTrelloApi().createBoard(board, name).execute().body();

        urlBoard = createdBoard.getUrl();

        boardName = createdBoard.getName();

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
        // Після введення емейлу та пароля клік ENTER
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterCredentials(TrelloCredentials.email, TrelloCredentials.password);

        // створення нової дошки
        checkCreateBoard();

        // створення екземпляру класу BoardsPage
        BoardsPage boardsPage = new BoardsPage(driver);

        //boardsPage.closePopup();
        boardsPage.getBoardCardByName(boardName).click();

        // створення екземпляру класу BoardPage
        BoardPage boardPage = new BoardPage(driver);

        // Menu More
        boardPage.getBoardMenuMoreLink().click();
        // Закрити дошку
        boardPage.getBoardClose().click();
        // в спливаючому вікні Закрити
        boardPage.boardConfirm.click();
        // Безвозвратное удаление
        boardPage.boardQuietDelete.click();
        // Удалить
        boardPage.boardConfirm.click();

        // Перевірка: наявність контейнеру messageQuiet
        Assert.assertTrue(boardPage.messageQuiet.isExist());

    }
}

import conf.TestManager;
import conf.TrelloCredentials;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

import java.io.IOException;

public class CreateChecklist extends TestManager {
        // змінні ідентифікатори, які отримуємо після створення сутності
        private String  idBoard;
        private String  idList;

        private String boardName;

    // Створення дошки
    public void checkCreateBoard() throws IOException {

        RetrofitBuilder retrofitBuilder = new RetrofitBuilder();

        Board board = new Board();
        String name = "Board CreateChecklist " + System.currentTimeMillis();

        Board createdBoard =
                retrofitBuilder.getTrelloApi().createBoard(board, name).execute().body();

        // ініціалізація змінної idBoard
        idBoard = createdBoard.getId();

        boardName = createdBoard.getName();

        // Перевірка: перевірка імені - чи створилась дошка з зазначеним іменем
        // Зі створеної дошки отримуємо імя за допомогою createdBoard.getName() та порівнюємо зі змінною name
        Assert.assertEquals(createdBoard.getName(), name);
    }

    // Створення листа в дошці
    public void classCreateList() throws IOException {
        // щоб змогли відправляти запити, створюємо екземпляру класу RetrofitBuilder
        RetrofitBuilder retrofitBuilder = new RetrofitBuilder();

        List list = new List();
        String name = "New list " + System.currentTimeMillis();

        List createdList =
                retrofitBuilder.getTrelloApi()
                        .createList(list, idBoard, name).execute().body();

        idList = createdList.getListId();

        // Перевірка: перевірка імені - чи створився лист з зазначеним іменем
        // Зі створеного листа отримуємо імя за допомогою createdList.getName() та порівнюємо зі змінною name
        Assert.assertEquals(createdList.getName(), name);
    }

    // Створення картки в листі
    public void classCreateCard() throws IOException {
        // щоб змогли відправляти запити, створюємо екземпляру класу RetrofitBuilder
        RetrofitBuilder retrofitBuilder = new RetrofitBuilder();

        Card card = new Card();
        String name = "New card" + System.currentTimeMillis();

        Card createdCard =
                retrofitBuilder.getTrelloApi()
                        .createCard(card, idList, name).execute().body();

        // Перевірка: перевірка імені - чи створилась картка з зазначеним іменем
        // Зі створеної картки отримуємо імя за допомогою createdCard.getName() та порівнюємо зі змінною name
        Assert.assertEquals(createdCard.getName(), name);
    }

    @Test
    public void createCheklistSelenium() throws IOException {

        // створення нової дошки
        checkCreateBoard();
        // створення листа в дошці
        classCreateList();
        // створення картки в листі
        classCreateCard();

        HomePage homePage = new HomePage(driver);
        // Перехід на сторінку
        homePage.openPage(homePage.url);

        // Клік на "Вхід": знайти елемент та клікнути на нього
        homePage.loginLink.click();

        // Заповнення полів "Електронна пошта" та "Пароль": знайти елемент, очистити його та ввести текст
        // Після введення емейлу та пароля клік ENTER
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterCredentials(TrelloCredentials.email, TrelloCredentials.password);

        // створення екземпляру класу BoardsPage
        BoardsPage boardsPage = new BoardsPage(driver);

        boardsPage.getBoardCardByName(boardName).click();

        // створення екземпляру класу BoardPage
        BoardPage boardPage = new BoardPage(driver);

        boardPage.getListCard().click();
        boardPage.getAddChecklistButton().click();
        boardPage.checklistInput.sendKeys("Autotest" + Keys.ENTER);
        boardPage.checklistItemInput.sendKeys("Checklist item"+ Keys.ENTER);
        boardPage.checklistCloseButton.click();

        Assert.assertTrue(boardPage.getChecklistIcon().isExist());

    }

}

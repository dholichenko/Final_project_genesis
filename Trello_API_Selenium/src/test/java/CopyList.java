import conf.TestManager;
import conf.TrelloCredentials;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.BoardPage;
import pages.BoardsPage;
import pages.HomePage;
import pages.LoginPage;

import java.io.IOException;

public class CopyList extends TestManager {
// змінні ідентифікатори, які отримуємо після створення сутності
    private String  idBoard;
    private String  idList;
    private String  idCard;

    private String boardName;
    private String listName;

    // Створення дошки
    public void checkCreateBoard() throws IOException {
        // щоб змогли відправляти запити, створюємо екземпляру класу RetrofitBuilder
        RetrofitBuilder retrofitBuilder = new RetrofitBuilder();

        Board board = new Board();
        String name = "Board CopyList " + System.currentTimeMillis();

        Board createdBoard =
                retrofitBuilder.getTrelloApi().createBoard(board, name).execute().body();

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

        listName = createdList.getName();

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

        idCard = createdCard.getCardId();

        // Перевірка: перевірка імені - чи створилась картка з зазначеним іменем
        // Зі створеної картки отримуємо імя за допомогою createdCard.getName() та порівнюємо зі змінною name
        Assert.assertEquals(createdCard.getName(), name);
    }

    @Test
    public void copyListSelenium() throws IOException {

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

        // створення листа в дошці
        classCreateList();

        // створення 2х карток в листі
        for(int i = 1; i <= 2; i++){
            classCreateCard();
        }

        // створення екземпляру класу BoardsPage
        BoardsPage boardsPage = new BoardsPage(driver);

        //boardsPage.closePopup();
        boardsPage.getBoardCardByName(boardName).click();

        // створення екземпляру класу BoardPage
        BoardPage boardPage = new BoardPage(driver);

        // натиснення
        boardPage.getListMenuByName(listName).click();

        // копіювати список
        boardPage.getCopyList().click();

        // клік на кнопку створити список
        String newListName = "List Copy";
        boardPage.newListInput.sendKeys(newListName + Keys.ENTER);

        // перевірка
        Assert.assertTrue(boardPage.getListByTitle(newListName).isExist());

    }
}

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
        RetrofitBuilder retrofitBuilder = new RetrofitBuilder();

        Board board = new Board();
        String name = "Board CopyList " + System.currentTimeMillis();

        Board createdBoard =
                retrofitBuilder.getTrelloApi().createBoard(board, name).execute().body();

        idBoard = createdBoard.getId();

        urlBoard = createdBoard.getUrl();

        Assert.assertEquals(createdBoard.getName(), name);
    }

    // Створення листа в дошці
    public void classCreateList() throws IOException {
        RetrofitBuilder retrofitBuilder = new RetrofitBuilder();
        List list = new List();
        String name = "New list " + System.currentTimeMillis();

        List createdList =
                retrofitBuilder.getTrelloApi()
                        .createList(list, idBoard, name).execute().body();

        idList = createdList.getListId();

        Assert.assertEquals(createdList.getName(), name);
    }

    // Створення картки в листі
    public void classCreateCard() throws IOException {
        RetrofitBuilder retrofitBuilder = new RetrofitBuilder();
        Card card = new Card();
        String name = "New card" + System.currentTimeMillis(); // Назва картки

        Card createdCard =
                retrofitBuilder.getTrelloApi()
                        .createCard(card, idList, name).execute().body();

        idCard = createdCard.getCardId();

        Assert.assertEquals(createdCard.getName(), name);
    }

    // 6. Видалення дошки
    public void classDeleteBoard() throws IOException {
        RetrofitBuilder retrofitBuilder = new RetrofitBuilder();
        Board board = new Board();

        int code =
                retrofitBuilder.getTrelloApi()
                        .deleteBoard(idBoard, board.getKey(), board.getToken()).execute().code();

        Assert.assertEquals(code, 200);
    }

    @Test
    public void copyListSelenium() throws IOException {
        HomePage homePage = new HomePage(driver);
        homePage.openPage(homePage.url);

        homePage.loginLink.click();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.emailField.clear();
        loginPage.emailField.sendKeys("projectqaschool@gmail.com");

        loginPage.passwordField.clear();
        loginPage.passwordField.sendKeys("projectschool3" + Keys.ENTER);

        if(!driver.findElement(By.cssSelector("div.home-sticky-container")).isEnabled()) {
            System.out.println("не залогинились");
        }

        BoardsPage boardsPage = new BoardsPage(driver);

        checkCreateBoard();

        boardsPage.openPage(urlBoard);


        classCreateList();
        for(int i = 1; i <= 2; i++){
            classCreateCard();
        }

        // натиснення ... першого листа зліва
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#board > div:nth-child(1)>div.list.js-list-content>div.list-header.js-list-header.u-clearfix.is-menu-shown>div.list-header-extras"))).click();
        //boardsPage.listMenuShown.click();

        boardsPage.copyList.click();

        boardsPage.createList.click();

        Assert.assertTrue((boardsPage.listNameCreated.getText()).equals(boardsPage.listNameCopied.getText()));

        classDeleteBoard();
    }
}

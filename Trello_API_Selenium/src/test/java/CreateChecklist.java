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
        private String  idBoard;
        private String  idList;

        private String  urlCard;
        private String  checklistName;

    // Створення дошки
    public void checkCreateBoard() throws IOException {
        RetrofitBuilder retrofitBuilder = new RetrofitBuilder();

        Board board = new Board();
        String name = "Board CreateChecklist " + System.currentTimeMillis(); // Назва дошки

        Board createdBoard =
                retrofitBuilder.getTrelloApi().createBoard(board, name).execute().body();

        idBoard = createdBoard.getId();


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

        urlCard = createdCard.getUrl();

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
    public void createCheklistSelenium() throws IOException {

        HomePage homePage = new HomePage(driver);
        homePage.openPage(homePage.url);

        homePage.loginLink.click();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.emailField.clear();
        loginPage.emailField.sendKeys("projectqaschool@gmail.com");

        loginPage.passwordField.clear();
        loginPage.passwordField.sendKeys("projectschool3" + Keys.ENTER);

        BoardsPage boardsPage = new BoardsPage(driver);

        if(!driver.findElement(By.cssSelector("div.home-sticky-container")).isEnabled()) {
            System.out.println("не залогинились");
        }

        checkCreateBoard();
        classCreateList();
        classCreateCard();

        boardsPage.openPage(urlCard);

        ChecklistPage checklistPage = new ChecklistPage(driver);

        //driver.findElement(By.cssSelector("a[class='button-link js-add-checklist-menu']")).click();
        checklistPage.addChecklist.click();

        checklistName = "New checklist " + System.currentTimeMillis();
        checklistPage.checklistField.sendKeys(checklistName + Keys.ENTER);

        Assert.assertTrue(checklistPage.checklistEx.getText().equals(checklistName));
        //Assert.assertTrue(checklistPage.checklistEx.composeLast().getText().equals(checklistName));

        classDeleteBoard();
    }

}

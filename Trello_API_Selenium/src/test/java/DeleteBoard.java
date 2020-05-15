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
        RetrofitBuilder retrofitBuilder = new RetrofitBuilder();

        Board board = new Board();
        String name = "Board DeleteBoard" + System.currentTimeMillis(); // Назва дошки

        Board createdBoard =
                retrofitBuilder.getTrelloApi().createBoard(board, name).execute().body();

        urlBoard = createdBoard.getUrl();

        Assert.assertEquals(createdBoard.getName(), name);
    }

    @Test
    public void deleteBoardSelenium() throws IOException {
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


        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        boardsPage.openPage(urlBoard);


//        if(boardsPage.boardMenuHide.isExist()){
//            boardsPage.boardBtnMenu.click();
//        };

        boardsPage.boardMenuMoreLink.click();
        boardsPage.boardClose.click();
        boardsPage.boardConfirm.click();
        boardsPage.boardQuietDelete.click();
        boardsPage.boardConfirm.click();

        Assert.assertTrue(boardsPage.messageQuiet.isExist());

        //Assert.assertFalse(driver.findElements(By.xpath("//div[@title='" + boardNameEx + "']")).isEmpty());

    }
}

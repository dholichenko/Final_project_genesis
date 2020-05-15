import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class TrelloTestAPI {

        private String  idBoard;
        private String  idList;
        private String  idCard;
        private String  idChecklist;

        // Створення дошки
        @Test(priority = 0)
        public void checkCreateBoard() throws IOException {
            RetrofitBuilder retrofitBuilder = new RetrofitBuilder();

            Board board = new Board();
            String name = "Board TrelloTestAPI " + System.currentTimeMillis(); // Назва дошки

            Board createdBoard =
                    retrofitBuilder.getTrelloApi().createBoard(board, name).execute().body();

            idBoard = createdBoard.getId();

            Assert.assertEquals(createdBoard.getName(), name);
        }

        // Створення листа в дошці
        @Test(priority = 1, dependsOnMethods = "checkCreateBoard")
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

        @DataProvider(name = "data-provider")
        public Object[][] dataProviderMethod() {
            return new Object[][] { {"1"}, {"2"}};
        }

        // Створення картки в листі
        @Test(priority = 2, dependsOnMethods={"classCreateList"}, dataProvider = "data-provider")
        public void classCreateCard(String data) throws IOException {
            RetrofitBuilder retrofitBuilder = new RetrofitBuilder();
            Card card = new Card();
            String name = "New card" + data;

            Card createdCard =
                    retrofitBuilder.getTrelloApi()
                            .createCard(card, idList, name).execute().body();

            idCard = createdCard.getCardId();

            Assert.assertEquals(createdCard.getName(), name);
        }

    // 18. Створення чекліста в картці
    @Test(priority = 3, dependsOnMethods = {"classCreateCard"}) // запуск після виконання цсіх методів групи createChecklist
    public void classCreateChecklist() throws IOException {
        RetrofitBuilder retrofitBuilder = new RetrofitBuilder();
        Checklist checklist = new Checklist();
        String name = "New checklist " + System.currentTimeMillis();

        Checklist createdChecklist =
                retrofitBuilder.getTrelloApi()
                        .createChecklist(checklist, idCard, name).execute().body();

        idChecklist = createdChecklist.getChecklistId();

        Assert.assertEquals(createdChecklist.getName(), name);
    }

    // 6. Видалення дошки
    @Test(priority = 4, dependsOnMethods = {"checkCreateBoard"})
    public void classDeleteBoard() throws IOException {
        RetrofitBuilder retrofitBuilder = new RetrofitBuilder();
        Board board = new Board();

        int code =
                retrofitBuilder.getTrelloApi()
                        .deleteBoard(idBoard, board.getKey(), board.getToken()).execute().code();

        Assert.assertEquals(code, 200);
    }
}

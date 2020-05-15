import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class TrelloTestAPI {

        // змінні ідентифікатори, які отримуємо після створення сутності
        private String  idBoard;
        private String  idList;
        private String  idCard;
        private String  idChecklist;

        // Створення дошки
        @Test(priority = 0)
        public void checkCreateBoard() throws IOException {
            // щоб змогли відправляти запити, створюємо екземпляру класу RetrofitBuilder
            RetrofitBuilder retrofitBuilder = new RetrofitBuilder();

            // створення параметрів для методу createBoard з інтерфейсу TrelloApi
            Board board = new Board(); // передаємо цей параметр, щоб Retrofit отримав ключ та токен, для виконання запиту
            String name = "Board TrelloTestAPI " + System.currentTimeMillis(); // Назва дошки

            // так як метод createBoard повертає Board, то і присвоюємо її змінній.
            // Тобто JSON, який повернеться з метода, записуємо його в цю змінну
            Board createdBoard =
                    retrofitBuilder.getTrelloApi().createBoard(board, name).execute().body();

            // ініціалізація змінної idBoard
            idBoard = createdBoard.getId();

            // Перевірка: перевірка імені - чи створилась дошка з зазначеним іменем
            // Зі створеної дошки отримуємо імя за допомогою createdBoard.getName() та порівнюємо зі змінною name
            Assert.assertEquals(createdBoard.getName(), name);
        }

        // Створення листа в дошці
        @Test(priority = 1, dependsOnMethods = "checkCreateBoard")
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

        @DataProvider(name = "data-provider")
        public Object[][] dataProviderMethod() {
            return new Object[][] { {"1"}, {"2"}};
        }

        // Створення картки в листі
        @Test(priority = 2, dependsOnMethods={"classCreateList"}, dataProvider = "data-provider")
        public void classCreateCard(String data) throws IOException {
            // щоб змогли відправляти запити, створюємо екземпляру класу RetrofitBuilder
            RetrofitBuilder retrofitBuilder = new RetrofitBuilder();
            // створення параметрів для методу createCard з інтерфейсу TrelloApi
            Card card = new Card(); // передаємо цей параметр, щоб Retrofit отримав ключ та токен, для виконання запиту
            String name = "New card" + data; // Назва картки

            // так як метод createCard повертає Card, то і присвоюємо її змінній.
            // Тобто JSON, який повернеться з метода, записуємо його в цю змінну
            Card createdCard =
                    retrofitBuilder.getTrelloApi()
                            .createCard(card, idList, name).execute().body();

            // ініціалізація змінної idCard
            idCard = createdCard.getCardId();

            // Перевірка: перевірка імені - чи створилась картка з зазначеним іменем
            // Зі створеної картки отримуємо імя за допомогою createdCard.getName() та порівнюємо зі змінною name
            Assert.assertEquals(createdCard.getName(), name);
        }

    // 18. Створення чекліста в картці
    @Test(priority = 3, dependsOnMethods = {"classCreateCard"}) // запуск після виконання цсіх методів групи createChecklist
    public void classCreateChecklist() throws IOException {
        // щоб змогли відправляти запити, створюємо екземпляру класу RetrofitBuilder
        RetrofitBuilder retrofitBuilder = new RetrofitBuilder();
        // створення параметрів для методу createChecklist з інтерфейсу TrelloApi
        Checklist checklist = new Checklist(); // передаємо цей параметр, щоб Retrofit отримав ключ та токен, для виконання запиту
        String name = "New checklist " + System.currentTimeMillis(); // Назва чекліста

        // так як метод createChecklist повертає Checklist, то і присвоюємо її змінній.
        // Тобто JSON, який повернеться з метода, записуємо його в цю змінну
        Checklist createdChecklist =
                retrofitBuilder.getTrelloApi()
                        .createChecklist(checklist, idCard, name).execute().body();

        // ініціалізація змінної idChecklist
        idChecklist = createdChecklist.getChecklistId();

        // Перевірка: перевірка імені - чи створився чекліст з зазначеним іменем
        // Зі створеного чекліста отримуємо імя за допомогою createdChecklist.getName() та порівнюємо зі змінною name
        Assert.assertEquals(createdChecklist.getName(), name);
    }

    // 6. Видалення дошки
    @Test(priority = 4, dependsOnMethods = {"checkCreateBoard"})
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
}

public class Card extends List {
    // Class Model (Pojo) - клас, який відображає сутність з якою працюємо
    // Для передачі даних в запит

    public String idList;

    // гетери та сетери
    // list
    public String getListId() {
        return idList;
    }

    // card
    public String getCardId() {
        return id;
    }

    public String getUrl() {
        return url;
    }
}

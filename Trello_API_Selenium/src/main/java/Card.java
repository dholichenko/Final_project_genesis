public class Card extends List {
    // Class Model (Pojo) - клас, який відображає сутність з якою працюємо
    // Для передачі даних в запит

    // Клас Card наслідується від класу List

    // Обов'язковий параметр запиту для створення картки
    public String idList;

    // гетери та сетери - щоб не порушувати принципи ООП: звертатись до змінних не напряму
    // для гетера идентифікатору листа переприсвоюємо метод getListId.
    // Так як в відповіді запиту на створення картки: id - ідентифікатор картки, idList - ідентифікатор листа
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

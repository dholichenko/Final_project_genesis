public class Checklist extends Card {
    // Class Model (Pojo) - клас, який відображає сутність з якою працюємо
    // Для передачі даних в запит

    // Клас Checklist наслідується від класу Card

    // Обов'язковий параметр запиту для створення чекліста
    public String idCard;

    // гетери та сетери - щоб не порушувати принципи ООП: звертатись до змінних не напряму
    // для гетера идентифікатору картки переприсвоюємо метод getCardId.
    // Так як в відповіді запиту на створення чекліста: id - ідентифікатор чекліста, idCard - ідентифікатор картки
    // card
    public String getCardId() {
        return idCard;
    }

    // checklist
    public String getChecklistId() {
        return id;
    }
}

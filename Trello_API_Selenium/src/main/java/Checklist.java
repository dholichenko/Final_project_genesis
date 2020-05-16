public class Checklist extends Card {
    // Class Model (Pojo) - клас, який відображає сутність з якою працюємо
    // Для передачі даних в запит

    public String idCard;

    // гетери та сетери
    // card
    public String getCardId() {
        return idCard;
    }

    // checklist
    public String getChecklistId() {
        return id;
    }
}

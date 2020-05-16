public class Board {
    // Class Model (Pojo) - клас, який відображає сутність з якою працюємо
    // Для передачі даних в запит

    // Параметри - ключ та токен, які генеруються для акаунта з TrelloCredentials
    private String key = "c32213b73208054b340d3cc8fee6e119";
    private String token = "163431201acc40a18586899e0165ba07b70c46c6c1b7e0cbf7f5b866eeabbe9d";

    public String name;

    public String url;

    public String id;

    // гетери та сетери
    public String getKey() {
        return key;
    }

    public String getToken() {
        return token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }
}

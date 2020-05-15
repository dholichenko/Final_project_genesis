import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

// Для OkHttp формируем URL, делаем запрос, получаем объект.
// А вот Retrofit генерирует URL, по уже заданным правилам.

// Ретрофит это обертка над OkHttp

// По умолчанию ретрофит базируется на Okhttp. Т.е. вам как разработчику, использующему ретрофит необходимо написать всего один интерфейс, и потом Retrofit.Bulder() сам сгенерирует для вас реализацию. Внутри сгенерированного класса вы увидите код работающий на OkHttp

public class RetrofitBuilder {
    // Клас, який білдить запити. Це АПІ білдер з використанням ретрофіту.
    // Налаштування яким повинен бути запит, з якими хедерами, куди входити і т.д.

    // В цьому класі буде створений конструктор, який проініціалізує всі екземпляри класів, які потрібні будуть
    // І він буде викликатись при кожному створенні екземпляру RetrofitBuilder
    // Виклик екземпляр RetrofitBuilder і від нього викликатимемо запити

    // Також потрібно вказати звідки брати запити - необхідно підвязати інтерфейс з методами. Для цього створюємо змінну
    private TrelloApi trelloApi;

    public RetrofitBuilder(){
        // Блок створення екземпляру gson бібліотеки Gson
        // Цей екземпляр будемо передавати в мпеціальний GsonConventorFactoryі він буде відповідати за те, щоб перетворити наш JSON в модель та модель в JSON
        Gson gson = new GsonBuilder().create();

        // Блок створення екземпляру бібліотеки класу OkHttpClient, який прийме HTTPLogInterceptor для його використання

        // Створення екземпляру класу HTTPLogInterceptor
        HTTPLogInterceptor interceptor = new HTTPLogInterceptor();
        // Від нього назначимо йому рівень, що він повинен логувати
        // Сам клас HTTPLogInterceptor логує декілька рівнів (переглянути в коді)
        // В даному випадку потрібен тільки BODY. Тобто це те, що нам повертається від запитів  (формат відповіді), тобто це тільки модель, яка нам потрібна
        interceptor.setLevel(HTTPLogInterceptor.Level.BODY);
        // Додали HTTPLogInterceptor, тепер в addInterceptor передаємо екземпляр interceptor для того, щоб бібліотека OkHttpClient
        // прийняла цей інтерсептор та обробила його та всю інформацію, яка нам потрібна, вона повернула.
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        // Створення екземпляру класу Retrofit - це основной білдер, який відповідає за всі запити
        // В нього передаємо client - використали OkHttpClient, який працює з інтерсептором
        // Також в нього передаємо addConverterFactory та в нього передамо GsonConverterFactory. В create передаємо змінну gson - екземпляр бібліотеки Gson
        // Retrofit це бібліотека, яка відповідає за запити.
        // Простими словами: зроби нам так, щоб ми могли виконувати запити з interceptor та gson. Візьми бібліотеку Gson, яка буде все це виконувати за нас.
        // baseUrl - базовий url
        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl("https://api.trello.com")
                .build();

        // В цьому ж конструкторі виконаємо ініціалізацію змінної trelloApi ретрофітом, так щоб ретрофіт виконував всі запити з цього класу
        trelloApi = retrofit.create(TrelloApi.class);
    }

    // створення гетера, який поверне екземпляр TrelloApi
    public TrelloApi getTrelloApi() {
        return trelloApi;
    }
}

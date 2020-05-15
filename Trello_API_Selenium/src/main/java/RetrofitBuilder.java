import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitBuilder {

    private TrelloApi trelloApi;

    public RetrofitBuilder(){

        Gson gson = new GsonBuilder().create();


        // Створення екземпляру класу HTTPLogInterceptor
        HTTPLogInterceptor interceptor = new HTTPLogInterceptor();

        interceptor.setLevel(HTTPLogInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();


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

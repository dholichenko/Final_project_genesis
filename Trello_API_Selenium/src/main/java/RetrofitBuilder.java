import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitBuilder {
    // Клас, який білдить запити. Це АПІ білдер з використанням ретрофіту.

    private TrelloApi trelloApi;

    public RetrofitBuilder(){
        // Блок створення екземпляру gson бібліотеки Gson
        Gson gson = new GsonBuilder().create();

        // Створення екземпляру класу HTTPLogInterceptor
        HTTPLogInterceptor interceptor = new HTTPLogInterceptor();

        interceptor.setLevel(HTTPLogInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        // Створення екземпляру класу Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl("https://api.trello.com")
                .build();

        trelloApi = retrofit.create(TrelloApi.class);
    }

    public TrelloApi getTrelloApi() {
        return trelloApi;
    }
}

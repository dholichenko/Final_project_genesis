import retrofit2.Call;
import retrofit2.http.*;

public interface TrelloApi {

    @POST("1/boards/")

    Call<Board> createBoard(@Body Board board, @Query("name") String name);


    @POST("/1/boards/{id}/lists")
    Call<List> createList(@Body List list, @Path("id") String idBoard, @Query("name") String name);


    @POST("/1/cards")
    Call<Card> createCard(@Body Card card, @Query("idList") String idList, @Query("name") String name);


    @POST("/1/checklists")
    Call<Checklist> createChecklist(@Body Checklist checklist, @Query("idCard") String idCard, @Query("name") String name);


    @DELETE("/1/boards/{id}")
    Call <Board> deleteBoard(@Path("id") String id, @Query("key") String key, @Query("token") String token);

}

package study.zgq.com.androidstudy.retrofit;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;

public interface INetServer {
    @GET("/musicRankings")
    Call<Info> queryBookList();
}

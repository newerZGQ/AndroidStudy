package study.zgq.com.androidstudy.retrofit;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ApiTest {
    public void test(){
        INetServer request = RetrofitManager.getInstance().getDefalutRetrofit()
                .create(INetServer.class);
        Call<Info> call = request.queryBookList();
        call.enqueue(new Callback<Info>() {
            @Override
            public void onResponse(Call<Info> call, Response<Info> response) {
                Log.d("nanwei", ((Info)response.body()).message);
            }

            @Override
            public void onFailure(Call<Info> call, Throwable t) {
                Log.d("nanwei failure", call.toString());
            }
        });
    }
}

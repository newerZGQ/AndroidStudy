package study.zgq.com.androidstudy.retrofit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitManager {
    private static RetrofitManager manager;
    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.apiopen.top")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .client(new OkHttpClient())
            .build();
    public static RetrofitManager getInstance(){
        if (manager == null) {
            return new RetrofitManager();
        }
        return manager;
    }
    public void init(){
//        retrofit = ;
    }
    public Retrofit getDefalutRetrofit(){
        return retrofit;
    }
}

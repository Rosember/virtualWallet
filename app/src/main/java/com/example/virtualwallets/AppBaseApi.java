package com.example.virtualwallets;

import com.example.virtualwallets.utils.WalletApi;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @autor Ing. Carlos G. Cruz Andia
 * Creado el 2019-11-14
 */
public class AppBaseApi {
    private static final String IP_1 = "10.1.3.102";
    private static final String IP_2 = "10.1.3.104";
    private static final String BASE_URL_SERVICE = "http://" + IP_2 + ":8081/api/";
    private static AppBaseApi _Instance;
    private Retrofit mRetrofit;


    private AppBaseApi() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addInterceptor(chain -> {
                    Request original = chain.request();
                    Request.Builder requestBuilder = original.newBuilder()
                            .addHeader("Accept", "application/json")
                            .method(original.method(), original.body());
                    Request request = requestBuilder.build();
                    return chain.proceed(request);
                });
        OkHttpClient client1 = client.build();

        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL_SERVICE)
                .client(client1)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static AppBaseApi getInstance() {
        if (_Instance != null) {
            _Instance = new AppBaseApi();
        }
        return _Instance;
    }

    public WalletApi getJSONApi() {
        return mRetrofit.create(WalletApi.class);
    }
}

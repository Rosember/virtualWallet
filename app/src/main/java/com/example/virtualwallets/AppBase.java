package com.example.virtualwallets;


import android.content.Context;
import android.content.SharedPreferences;

import com.example.virtualwallets.mainComponent.Application;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class AppBase {

    public static final String IP_1 = "10.1.3.102";
    public static final String IP_2 = "10.1.3.104";
    public static final String BASE_URL_SERVICE ="http://"+IP_2+":8081/api/";
    public static final String KEY_TOKEN = "token";
    public static final String KEY_USER = "user_id";
    private static final String APP_PREFERENCE = "Wallets";
//    private static AppBase _INSTANCE = new AppBase();
//
//    public static AppBase getInstance() {
//        return _INSTANCE;
//    }

    public static  <S> S crearServicio(Class<S> sClass,String baseUrl){
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.connectTimeout(10, TimeUnit.SECONDS);

        OkHttpClient client = httpClient.build();

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient.addInterceptor(logging);
        httpClient.addInterceptor(new Interceptor(){
            @Override
            public Response intercept(Interceptor.Chain chain) throws IOException {
                Request original = chain.request();
                Request.Builder requestBuilder = original.newBuilder()
                        .addHeader("Accept", "application/json")
                        .method(original.method(), original.body());
                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        });

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit adapter = builder.client(httpClient.build()).build();
        return adapter.create(sClass);
    }

    /**
     * metodo para recuperar variables goblales de la aplicacion e.g. token.
     */
    public static String retrieveset(String name) {
        //retreive
        String res = "";
        Context c = Application.getAppContext();
        SharedPreferences sharedPref = c.getSharedPreferences(APP_PREFERENCE, Context.MODE_PRIVATE);
        res = sharedPref.getString(name, null);
        return res;
    }

    /**
     * metodo para guardar algunas variables globales token, usuario, etc.
     * Pensado para obtener valor de token y otros para los servicios.
     */
    public static void saveset(String key, String value) {
        //store
        Context c = Application.getAppContext();
        SharedPreferences sharedPref = c.getSharedPreferences(APP_PREFERENCE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static void clearAllSharedPreference() {
        Context c = Application.getAppContext();
        SharedPreferences settings = c.getSharedPreferences(APP_PREFERENCE, Context.MODE_PRIVATE);
        settings.edit().clear().apply();
    }

    public static void removeKey(String key){
        Context c = Application.getAppContext();
        SharedPreferences mySPrefs = c.getSharedPreferences(APP_PREFERENCE,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mySPrefs.edit();
        editor.remove(key);
        editor.apply();
    }


}

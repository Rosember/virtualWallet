package com.example.virtualwallets.mainComponent;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @autor Ing. Carlos G. Cruz Andia
 * Creado el 2019-11-12
 */
public class Application extends android.app.Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        this.context = getApplicationContext();
    }

    public static Context getAppContext(){
        return context;
    }
}

package com.example.virtualwallets.utils;

import android.os.AsyncTask;
import android.util.Log;

import com.example.virtualwallets.AppBase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/**
 * @autor Ing. Carlos G. Cruz Andia
 * Creado el 2019-11-12
 */
public class CheckSession extends AsyncTask<Object, Object, Boolean> {
    public static final String YYYY_MM_DD_T_HH_MM_SS_SSS = "yyyy-MM-dd'T'HH:mm:ss.SSS";
    public static final String KEY_SESSION = "hora";
    private final String TAG = getClass().getSimpleName();
    private OnTaskCompleted taskCompleted;

    public CheckSession(OnTaskCompleted taskCompleted) {
        this.taskCompleted = taskCompleted;
    }

    @Override
    protected Boolean doInBackground(Object... objects) {
        try {
            String sessionDate = AppBase.getInstance().retrieveset(KEY_SESSION);
            if (sessionDate != null) {
                Date today = Calendar.getInstance().getTime();
                SimpleDateFormat dateFormatter = new SimpleDateFormat(YYYY_MM_DD_T_HH_MM_SS_SSS, Locale.US);
                Date afterDate = dateFormatter.parse(sessionDate);
                int timePast = minutosTranscurridos(today, afterDate);

                if (timePast <= 120) {
                    return true;
                }
            }else{
                return false;
            }


        } catch (Exception ex) {
            Log.d(TAG, "doInBackground: ");
            return false;
        }
        return false;
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);
        taskCompleted.onTaskComplete(aBoolean);
    }

    private int minutosTranscurridos(Date today, Date after) {
        long diferencia = today.getTime() - after.getTime();
        return (int) TimeUnit.MILLISECONDS.toMinutes(diferencia);

    }
}

package com.example.atsushi.rk;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import static android.content.Context.MODE_WORLD_READABLE;

/**
 * Created by atsushi on 2018/03/11.
 */

public class DataProvide {

    private SharedPreferences pref;

    DataProvide(Context context){
        pref =
            PreferenceManager.getDefaultSharedPreferences(context);

    }
    int read() {
        System.out.println("読み込み " + pref.getInt("point", 0));
        return pref.getInt("point", 0);
    }

    void write(int count) {
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt("point", count);
        editor.commit();
        System.out.println("保存" + count);
    }

}


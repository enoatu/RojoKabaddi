package com.example.atsushi.rk;

import android.app.Activity;
import android.content.ContentProvider;
import android.content.ContentProviderClient;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.preference.Preference;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.File;
import java.lang.reflect.Array;
import java.net.URL;
import java.net.URLClassLoader;
import java.security.spec.ECField;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import static android.content.Context.CONTEXT_IGNORE_SECURITY;
import static android.content.Context.MODE_PRIVATE;


public class Config {
    private Point display_size;

    int seekBarMax  = 10;
    int logDelay    = 0;
    int logPeriod   = 1000;
    final Map<String, Integer> animeMap = new HashMap<String, Integer>() {
        {
            put("delay", 0);
            put("anim_y",2);
            put("scalex", 2);
            put("scaley", 2);
            put("duration", 4000);
        }
    };
    final int destWidth = 200;
    final int destHeight = 250;

    int imgId = R.drawable.ninin;
    Config(Point display_size){
        this.display_size = display_size;
        System.out.println("Y : " + getDefaultEnemyPositionY());
        System.out.println("YY : " + getEnemyRepeatPositionY());
    }

    void read(){
        //android studio 3.0 do not support
        ResourceBundle rb = null;
        try {
            //https://github.com/bazelbuild/intellij/issues/118
            String dir = "../../res";
            //propertiesファイル名(.propertiesは不要)
            String source = "appconf";

            //取得処理
            // URLClassLoader urlLoader = new URLClassLoader(new URL[]{new File(dir).toURI().toURL()});
          //rb = ResourceBundle.getBundle(source);
           rb = ResourceBundle.getBundle(source);
        }catch (MissingResourceException e){
            e.printStackTrace();
            Log.d("1", "read: ");
            System.out.println(e);
        }

        try {
            System.out.println(rb.getString("seekBarMax"));
        }catch (Exception e){
            Log.d("2", "read: ");
            System.out.println(e);
        }
    }

    int getEnemyHomingPositionY(){  //homingの座hyouを返す
        return (int) (display_size.y * 0.6);//static不可能
    }
    int getEnemyEndPositionY(){     //エネミーの最終座標
        return (int) (display_size.y * 1.2);
    }
    int getEnemyRepeatPositionY(){  //エネミーのリピート座標
        return (int) (display_size.y * 1.1);
    }
    int getDefaultEnemyPositionX(){ //エネミー出現場所X
        return (int) (display_size.x * 0.5);
    }
    int getDefaultEnemyPositionY(){ //エネミー出現場所Y
        return (int) (display_size.y * -0.1);
    }
}

class DataProvide{

    private SharedPreferences pref;

    DataProvide(Activity activity){
        this.pref = activity.getPreferences(Context.MODE_PRIVATE);
    }

    int read() {
        System.out.println("保存" + pref.getInt("point", 0));
         return  pref.getInt("point", 0);
    }

    void write(int count) {
         SharedPreferences.Editor editor = pref.edit();
         editor.putInt("point", count);
         editor.commit();
    }
 }



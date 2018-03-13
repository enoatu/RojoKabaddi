package com.example.atsushi.rk;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //BestRecordをセット
        TextView record = findViewById(R.id.record);
        setRecord(record,this);

    }

    public void onSetStartClick(View view){
        Intent intent = new Intent(this,PlayActivity.class);
        this.finish();
        startActivityForResult(intent,0);
    }

    public void onSetStoryClick(View view){
        Intent intent = new Intent(this,StoryActivity.class);
        this.finish();
        startActivityForResult(intent,0);
    }

    public void onSetZukanClick(View view){
        Intent intent = new Intent(this,ZukanActivity.class);
        this.finish();
        startActivityForResult(intent,0);
    }

    public void setRecord(TextView tv,Context context) {
        //得点を読み込んでTextViewにセット
        int count = new DataProvide(context).read();
        tv.setText(String.valueOf(count));
    }


}

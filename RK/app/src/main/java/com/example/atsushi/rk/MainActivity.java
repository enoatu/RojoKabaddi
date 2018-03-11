package com.example.atsushi.rk;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
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
        setRecord(record, this);
    }

    public void onSetStartClick(View view){
        Intent intent = new Intent(this,PlayActivity.class);
        startActivityForResult(intent,0);
    }
    public void onSetStoryClick(View view){
        Intent intent = new Intent(this,StoryActivity.class);
        startActivityForResult(intent,0);
    }
    public void onSetZukanClick(View view){
        Intent intent = new Intent(this,ZukanActivity.class);
        startActivityForResult(intent,0);
    }

    public void setRecord(TextView tv, Activity activity) {
        //得点を読み込んでTextViewにセット
        int count = new DataProvide(activity).read();
        tv.setText(String.valueOf(count));
    }


}

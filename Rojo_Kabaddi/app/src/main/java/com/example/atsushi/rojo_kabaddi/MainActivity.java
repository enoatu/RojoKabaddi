package com.example.atsushi.rojo_kabaddi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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


}

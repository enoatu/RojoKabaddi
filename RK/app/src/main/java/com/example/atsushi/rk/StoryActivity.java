package com.example.atsushi.rk;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class StoryActivity extends AppCompatActivity {

    String[][] sub_main_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);

        sub_main_text= Story.sub_main_text;//getDB
        int getAmount = new DataProvide(this).read();//解放数比較
        if(getAmount > sub_main_text.length) getAmount = sub_main_text.length;//

        ArrayList<String> story = new ArrayList<>();
        for (int i = 0; i < getAmount; i++) {
            story.add(i+" . "+ sub_main_text[i][0]+" "+sub_main_text[i][1]);
        }

        ListView listView = findViewById(R.id.listview);
        // simple_list_item_1 は、 もともと用意されている定義済みのレイアウトファイルのID
        ArrayAdapter<String> arrayAdapter =
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, story);

        listView.setAdapter(arrayAdapter);
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode != KeyEvent.KEYCODE_BACK){
            return super.onKeyDown(keyCode, event);
        }else{
            goHome();
            return false;
        }
    }

    public void goHome(){
        Intent intent = new Intent(this,MainActivity.class);
        this.finish();
        startActivityForResult(intent,0);

    }
}

package com.example.atsushi.rk;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

public class ZukanActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zukan);


        ListView listView = findViewById(R.id.listview);
        // simple_list_item_1 は、 もともと用意されている定義済みのレイアウトファイルのID
       // ArrayAdapter<String> arrayAdapter =
            //new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, g);

        //listView.setAdapter(arrayAdapter);
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

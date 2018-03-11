package com.example.atsushi.rk;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class StoryActivity extends AppCompatActivity {


    private static final String[] texts = {
            "abc ", "bcd", "cde", "def", "efg",
            "fgh", 	"ghi", "hij", "ijk", "jkl",
            "klm"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);


        ListView listView = findViewById(R.id.listview);
        // simple_list_item_1 は、 もともと用意されている定義済みのレイアウトファイルのID
        ArrayAdapter<String> arrayAdapter =
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, texts);

        listView.setAdapter(arrayAdapter);
    }
}

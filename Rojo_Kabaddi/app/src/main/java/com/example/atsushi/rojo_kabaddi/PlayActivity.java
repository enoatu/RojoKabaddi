package com.example.atsushi.rojo_kabaddi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.widget.ImageView;



public class PlayActivity extends AppCompatActivity {

    ImageView aite;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        aite = findViewById(R.id.aite);


        ViewPropertyAnimator animator=aite.animate();
        animator.setStartDelay(500);
        animator.y(1000);
        animator.scaleX(2).scaleY(2);
        animator.setDuration(2000);

    }

}

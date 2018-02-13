package com.enoatu.rojokabaddi;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    Boolean animeFlag=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView aite = (ImageView)findViewById(R.id.aite);


        Handler handler=new Handler();
        handler.postDelayed(new TextChangeRunnable("１"),2000);
        handler.postDelayed(new TextChangeRunnable("２"),4000);
        handler.postDelayed(new TextChangeRunnable("３"),6000);
    }
    public void onAnimationButtonTapped(View v){
        if(!animeFlag){

            (findViewById(R.id.button1)).setVisibility(View.INVISIBLE);
        animeFlag = true;
//        AnimationSet set = new AnimationSet(true);

        ViewPropertyAnimator animator=(findViewById(R.id.aite)).animate();
        animator.setStartDelay(500);
        animator.y(1000);
        animator.scaleX(2).scaleY(2);
        animator.setDuration(2000);

        ViewPropertyAnimator animator2=(findViewById(R.id.aite)).animate();
        animator2.setStartDelay(2501).setDuration(500).x(500);

//            anime2();
//            anime3();
       // ScaleAnimation scale = new ScaleAnimation(0.1f, 1, 0.1f, 1);
//        ScaleAnimation scale = new ScaleAnimation(1, 1, 1, 1);
//
//        set.addAnimation(scale);
//        set.setDuration(3000);
//        (findViewById(R.id.aite)).startAnimation(set);
//        animator.setListener((Animator.AnimatorListener) this);
        animeFlag = false;

        }


        (findViewById(R.id.button2)).setVisibility(View.VISIBLE);

    }
    public void restart(View v){
        if(!animeFlag){
            animeFlag=true;
            (findViewById(R.id.button1)).setVisibility(View.VISIBLE);
            (findViewById(R.id.button2)).setVisibility(View.INVISIBLE);

        ViewPropertyAnimator animator=(findViewById(R.id.aite)).animate();
        animator.setStartDelay(0);
        animator.x(450).y(-450);
        animator.scaleX(0.5f).scaleY(0.5f);
        animator.setDuration(0);

        animeFlag=false;

        }

    }

//    public void yokomove(){
//        if((animeFlag && findViewById(R.id.aite).getLocationInWindow(0,);)
//    }
    public void anime2(){
        ViewPropertyAnimator animator=(findViewById(R.id.aite)).animate();

        animator.setStartDelay(250).x(150).setDuration(0);
    }

    public void anime3(){
        ViewPropertyAnimator animator=(findViewById(R.id.aite)).animate();

        animator.y(-350).setDuration(1000);
    }

    }
class TextChangeRunnable implements Runnable{
    String moji;
    TextChangeRunnable(String moji){
        this.moji=moji;
    }
    public void run(){
        text.setText(moji);
    }
}

//    public void move_aite(){
//       int random= (int) (Math.random() * 3);
//        switch (random) {
//            case 0 :
//        }
//    }


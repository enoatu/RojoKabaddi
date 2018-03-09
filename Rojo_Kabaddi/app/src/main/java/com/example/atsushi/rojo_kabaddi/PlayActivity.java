package com.example.atsushi.rojo_kabaddi;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.solver.widgets.ConstraintAnchor;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;
//import static com.example.atsushi.rojo_kabaddi.Tools.*;

public class PlayActivity extends AppCompatActivity {
    final ImageView enemy;final Point display_size;
    final int DefaultEnemyPointX, DefaultEnemyPointY, homingY, enemyEndPointY, enemyRepeatPointY;
    final SeekBar seekBar;

    public PlayActivity(){
        this.enemy = findViewById(R.id.aite);//エネミーを特定
        this.seekBar = findViewById(R.id.seekbar);
        this.display_size = getDisplaySize(this);//アクティビティの画面サイズを取得
        this.homingY = (int) (display_size.y * 0.6);//homingの座標
        this.enemyEndPointY = (int) (display_size.y * 1.2);//エネミーの最終座標
        this.enemyRepeatPointY = (int) (display_size.y * 1.1);//エネミーのリピート座標
        this.DefaultEnemyPointX = (int) (display_size.x * 0.5);
        this.DefaultEnemyPointY = (int) (display_size.y * -0.1);
    }
        @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        final PlayActivity play = new PlayActivity();
        start();
        System.out.println("Y : " + enemyEndPointY);
        System.out.println("YY : " + enemyRepeatPointY);
        init();

        play.seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {//as collisionListener
            @Override
            public void onProgressChanged(SeekBar me, int i, boolean b) {
                if (play.enemy.getY() + play.enemy.getHeight() < play.homingY) {
                    play.enemy.setX(play.display_size.x * i / 10);//シークバーに追尾
                    System.out.println(
                            play.enemy.getX()+ " "
                            + play.display_size.x + " "
                            + play.display_size.y + " "
                            + play.homingY
                    );
                } else {//ホーミング終了時
                    if(play.enemyRepeatPointY < play.enemy.getY()){
                        System.out.println("リピート");
                        repeat();
                    }
                    if ((play.display_size.x * i / 10) < (play.enemy.getX() + play.enemy.getWidth())
                            && (play.display_size.x * i / 10) > (play.enemy.getX())
                            && me.getY() < (play.enemy.getY() + play.enemy.getHeight())
                            && me.getY() + me.getHeight() > (play.enemy.getY())
                            )
                        youAreDead();
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }

    public class EnemyMove {
        void anime(int delay, int anim_y, int duration) {
            ViewPropertyAnimator animator = enemy.animate();
            animator.setStartDelay(delay);
            animator.y(anim_y);
            animator.scaleX(2).scaleY(2);
            animator.setDuration(duration);
        }

    }

    void youAreDead() {
        Intent intent = new Intent(this, DeadActivity.class);
        this.finish();
        startActivityForResult(intent,0);
        overridePendingTransition(0, 0);
    }

    Point getDisplaySize(Activity activity) {
        Display display = activity.getWindowManager().getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);
        return point;
    }

    void repeat() {
        // drawableフォルダにある任意のイメージを設定
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ninin);
        // bitmapの画像を200×90で作成する
        Bitmap bitmap2 = Bitmap.createScaledBitmap(bitmap, 200, 45, false);
        enemy.setImageBitmap(bitmap2);
        enemy.setX(DefaultEnemyPointX);
        enemy.setY(DefaultEnemyPointY);
        start();
    }

    void start() {
        new EnemyMove().anime(0, enemyEndPointY, 4000);
    }

    private void init() {
        Timer timer = new Timer(true);
        final android.os.Handler handler = new android.os.Handler();
        timer.schedule(
                new TimerTask() {
                    @Override
                    public void run() {
                        handler.post( new Runnable(){
                            public void run(){
                                System.out.println("エネミーの座標は : X" + enemy.getX() + " Y" + enemy.getY() +
                                "R: " + enemyRepeatPointY);
                            }
                        });
                    }
                }
                , 0, 1000   //開始遅延(何ミリ秒後に開始するか)と、周期(何ミリ秒ごとに実行するか)
        );
    }
}




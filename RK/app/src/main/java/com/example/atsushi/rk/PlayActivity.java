package com.example.atsushi.rk;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.ViewPropertyAnimator;
import android.widget.ImageView;
import android.widget.SeekBar;

import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class PlayActivity extends AppCompatActivity{

    ImageView enemy;
    SeekBar seekBar;
    Point display_size;
    Config conf;
    static int count;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        enemy         = findViewById(R.id.aite);//onCreate内必須
        seekBar       = findViewById(R.id.seekbar);//onCreate内必須
        display_size  = getDisplaySize(this);//アクティビティの画面サイズを取得　onCreate内必須
        conf = new Config(display_size);//config
        seekBar.setMax(conf.seekBarMax);
        new CountPoint().start();
        new Play().start();//プレイ開始
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            //as collisionListener
            @Override
            public void onProgressChanged(SeekBar me, int i, boolean b) {
                if (enemy.getY() + enemy.getHeight() < conf.getEnemyHomingPositionY()) {
                    enemy.setX(display_size.x * i / conf.seekBarMax);//シークバーに追尾
                    System.out.println(
                        enemy.getX() + " "
                        + display_size.x + " "
                        + display_size.y + " "
                        + conf.getEnemyHomingPositionY()
                    );
                } else {
                    //ホーミング終了時
                    if ((display_size.x * i / conf.seekBarMax) < (enemy.getX() + enemy.getWidth())
                        && (display_size.x * i / conf.seekBarMax) > (enemy.getX())
                        && me.getY() < (enemy.getY() + enemy.getHeight())
                        && me.getY() + me.getHeight() > (enemy.getY()))
                        //衝突判定
                        youAreDead();
                    }
                    if (conf.getEnemyHomingPositionY() < enemy.getY()) {
                        //回避成功時
                        System.out.println("リピート");
                        EnemyRepeat repeat = new EnemyRepeat(getResources());
                        repeat.repeat(enemy);
                    }
                }
                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {
                }
                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                }
            }
        );
    }

    void youAreDead() {
        //死亡時
        //deadアクティビティに遷移
        new DataProvide(this).write(count);
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

    class Play {

        void start() {
            new PlayActivity.EnemyMove().anime(
                0,
                conf.getEnemyEndPositionY(),
                2,2,
                3000);
            logEnemyPosition(enemy);
            //new ResourceBundleRead().getProperty();

            conf.read();
        }

        private void logEnemyPosition(final ImageView enemy) {
            //logger
            Timer timer = new Timer(true);
            final android.os.Handler handler = new android.os.Handler();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    handler.post( new Runnable(){
                        public void run(){
                            System.out.println("エネミーの座標は : X"
                                + enemy.getX()
                                + " Y" + enemy.getY()
                                + "R: " + conf.getEnemyRepeatPositionY()
                            );
                        }
                    });
                }}
                , conf.logDelay, conf.logPeriod//開始遅延(何ミリ秒後に開始するか)と、周期(何ミリ秒ごとに実行するか)
            );
        }
    }

    class EnemyMove extends Play{
    //落下
        ViewPropertyAnimator animator;
        EnemyMove(){
            super();
            this.animator = enemy.animate();
        }
        void anime(int delay, int anim_y,
                   int scalex, int scaley, int duration) {
            animator.setStartDelay(delay);
            animator.y(anim_y);
            animator.scaleX(scalex).scaleY(scaley);
            animator.setDuration(duration);
        }
    }

    class EnemyRepeat extends Play{
     //リピート
        private Resources res;

        EnemyRepeat(Resources res){
            super();
            this.res = res;//プレイアクティビティでリソースを取得
        }
        void repeat(ImageView enemy){
            // drawableフォルダにある任意のイメージを設定
            Bitmap bitmap = BitmapFactory.decodeResource(res, conf.imgId);
            // bitmapの画像を200×90で作成する
            Bitmap bitmap2 = Bitmap.createScaledBitmap(
                bitmap, conf.destWidth, conf.destHeight, false);
            enemy.setImageBitmap(bitmap2);
            enemy.setX(conf.getDefaultEnemyPositionX());
            enemy.setY(conf.getDefaultEnemyPositionY());
            new CountPoint().add();//加点
            start();
        }
    }

    class CountPoint{
        //得点をカウントする
        void start(){
            count = 0;
        }
        void add(){
            count++;
            System.out.println("counter " + count);
        }
    }
    
}
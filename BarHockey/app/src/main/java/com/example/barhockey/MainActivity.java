package com.example.barhockey;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

public class MainActivity extends Activity implements Runnable {
    Ball ball;
    Bar myBar, yourBar;
    Handler handler;
    int width, height;
    int time = 2;
    int touchSpaceRate = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        RelativeLayout relativeLayout = new RelativeLayout(this);
        relativeLayout.setBackgroundColor(Color.parseColor("#424242"));
        setContentView(relativeLayout);

        handler = new Handler();
        handler.postDelayed(this, time);

        WindowManager windowManager =
                (WindowManager)getSystemService(WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);
        width = point.x;
        height = point.y;
        // ボール
        ball = new Ball(this, width, height);
        relativeLayout.addView(ball);
        // プレイヤーバー
        myBar = new Bar(this, width, height);
        myBar.setPosition(width/2, height-(height/touchSpaceRate));
        relativeLayout.addView(myBar);
        // 相手プレイヤーバー
        yourBar = new Bar(this, width, height);
        yourBar.setPosition(width/2, height/touchSpaceRate);
        relativeLayout.addView(yourBar);


    }
    @Override
    public void run() {
        ball.update();

        int ballTop = ball.y-ball.radius;
        int ballBottom = ball.y+ball.radius;
        if(ballTop <=  yourBar.bottom) {
            ball.y = yourBar.bottom+ball.radius;
            ball.vy = ball.vy > 0 ? -1 * (ball.vy + ball.defaultV/5) : -1 * (ball.vy - ball.defaultV/5);
        } else if(ballBottom >=  myBar.top) {
            ball.y = myBar.top-ball.radius;
            ball.vy =ball.vy > 0 ? -1 * (ball.vy + ball.defaultV/5) : -1 * (ball.vy - ball.defaultV/5);
        }

        ball.invalidate();
        myBar.invalidate();
        yourBar.invalidate();
        handler.postDelayed(this, time);
    }
    public void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(this);
    }
}

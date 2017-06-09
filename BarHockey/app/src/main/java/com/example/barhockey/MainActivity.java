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
    Handler handler;
    int width, height;
    int time = 10;

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
        //display.getSize(point);
        width = point.x;
        height = point.y;
        ball = new Ball(this);
        ball.x = width / 2;
        ball.y = height / 2;
        relativeLayout.addView(ball);
    }
    @Override
    public void run() {
        ball.update(width, height);

        ball.invalidate();
        handler.postDelayed(this, time);
    }
    public void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(this);
    }
}

package com.example.barhockey;

/**
 * Created by m-kodama on 2017/06/05.
 */

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class Ball extends View {
    int x, y, radius, vx, vy, screenWidth, screenHight;
    int defaultV, maxV;
    Paint paint;

    public Ball(Context context, int width, int height) {
        super(context);
        screenWidth = width;
        screenHight = height;
        radius = 30;
        x = width / 2;
        y = height / 2;
        vx = 0;
        vy = defaultV = 10;
        maxV = 100;
        paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(x, y, radius, paint);
    }

    public void update() {
        x += vx;
        y += vy;

        if (x <= radius) {
            x = radius;
            vx = -vx;
        } else if (x >= screenWidth - radius) {
            x = screenWidth - radius;
            vx = -vx;
        }

        if (y <= radius) {
            y = radius;
            vy = - vy;
        } else if (y >= screenHight - radius) {
            y = screenHight - radius;
            vy = - vy;
        }
    }
}

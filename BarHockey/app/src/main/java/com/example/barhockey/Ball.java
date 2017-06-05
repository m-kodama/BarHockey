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
    int x, y, radius, vx, vy;
    Paint paint;

    public Ball(Context context) {
        super(context);
        radius = 30;
        x = y = 0;
        vx = 0;
        vy = 10;
        paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(x, y, radius, paint);
    }

    public void update(int width, int height) {
        x += vx;
        y += vy;

        if (x <= radius) {
            x = radius;
            vx = -vx;
        } else if (x >= width - radius) {
            x = width - radius;
            vx = -vx;
        }

        if (y <= radius) {
            y = radius;
            vy = - vy;
        } else if (y >= height - radius) {
            y = height - radius;
            vy = - vy;
        }
    }
}

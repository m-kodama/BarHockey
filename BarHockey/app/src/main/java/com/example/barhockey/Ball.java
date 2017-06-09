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
    int  maxV;
    double reflectionRate;
    Paint paint;

    public Ball(Context context, int width, int height) {
        super(context);
        screenWidth = width;
        screenHight = height;
        radius = 30;
        x = width / 2;
        y = height / 2;
        vx = 3;
        vy = 10;
        maxV = 100;
        reflectionRate = 1.1;
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

    public void reflect(int collisionPoint, Bar bar) {
        // collisionPoint 1:上で衝突，2:右で衝突，3:下で衝突，4:左で衝突，5:上で衝突，6:右で衝突，7:下で衝突，8:左で衝突
        switch(collisionPoint) {
            case 1:
                y = bar.top-radius;
                vy = (int)(vy * reflectionRate * -1);
                break;
            case 2:
                x = bar.right+radius;
                vx = (int)(vx * reflectionRate * -1);
                break;
            case 3:
                y = bar.bottom+radius;
                vy = (int)(vy * reflectionRate * -1);
                break;
            case 4:
                x = bar.left-radius;
                vx = (int)(vx * reflectionRate * -1);
                break;
            case 5:
                vx = vx < 0 ? -vx : vx;
                vy = -vy;
                break;
            case 6:
                vx = vx < 0 ? -vx : vx;
                vy = -vy;
                break;
            case 7:
                vx = vx < 0 ? vx : -vx;
                vy = -vy;
                break;
            case 8:
                vx = vx < 0 ? vx : -vx;
                vy = -vy;
                break;
            case 9:
                y = bar.top-radius;
                vy = (int)(vy * reflectionRate * -1.5);
                break;
            default:
                break;
        }
    }
}

package com.example.barhockey;

/**
 * Created by m-kodama on 2017/06/05.
 */

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class Bar extends View {
    int x,y, width, height, screenWidth, screenHight;
    int left, top, right, bottom;
    Paint paint;

    public Bar(Context context, int screenWidthwidth, int screenHightheight) {
        super(context);
        screenWidth = screenWidthwidth;
        screenHight = screenHightheight;
        x = y = 0;
        width = screenWidthwidth / 4;
        height = screenHightheight / 60;
        paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        left = x-(width/2);
        top = y-(height/2);
        right = x+(width/2);
        bottom = y+(height/2);
        canvas.drawRect(left, top, right, bottom, paint);
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

}

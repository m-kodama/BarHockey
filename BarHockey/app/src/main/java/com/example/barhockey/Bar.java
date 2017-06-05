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
    int x, y, width, height;
    Paint paint;

    public Bar(Context context) {
        super(context);
        x = y = 0;
        paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //canvas.drawCircle(x, y, radius, paint);
    }
}

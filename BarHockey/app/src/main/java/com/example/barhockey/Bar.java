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
    int x,y, width, height, screenWidth, screenHight, zone;
    int left, top, right, bottom;
    Paint paint,zonePaint;

    public Bar(Context context, int screenWidthwidth, int screenHightheight) {
        super(context);
        screenWidth = screenWidthwidth;
        screenHight = screenHightheight;
        x = y = 0;
        width = screenWidthwidth / 4;
        height = screenHightheight / 60;
        zone = 60;  // ball.radius * 2

        paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL);

        zonePaint = new Paint();
        zonePaint.setColor(Color.parseColor("#69F0AE"));
        zonePaint.setStyle(Paint.Style.FILL);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        left = x-(width/2);
        top = y-(height/2);
        right = x+(width/2);
        bottom = y+(height/2);
        canvas.drawRect(left, top, right, bottom, paint);
        // ストライクゾーンを描画
        int distance = (width-zone)/2;
        canvas.drawRect(left+distance, top, right-distance, bottom, zonePaint);
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // return 0:衝突なし，1:上で衝突，2:右で衝突，3:下で衝突，4:左で衝突，5:上で衝突，6:右で衝突，7:下で衝突，8:左で衝突
    public int isCollision(Ball ball) {
        if(top-ball.radius > y || bottom+ball.radius < y || left-ball.radius > x || right+ball.radius < x) { // 衝突なし
            return 0;
        } else if(top-ball.radius <= ball.y && ball.y <= y && left <= ball.x && ball.x <= right) {      // 上で衝突
            return 1;
        } else if(top <= ball.y && ball.y <= bottom && right+ball.radius >= ball.x && ball.x >= x) {    // 右で衝突
            return 2;
        } else if(bottom+ball.radius >= ball.y && ball.y >= y && left <= ball.x && ball.x <= right) {   // 下で衝突
            return 3;
        } else if(top <= ball.y && ball.y <= bottom && left-ball.radius <= ball.x && ball.x <= x) {     // 左で衝突
            return 4;
        } else if( (right-ball.x)*(right-ball.x)+(top-ball.y)*(top-ball.y) <= ball.radius*ball.radius && top >= ball.y) {      // 右上で衝突
            return 5;
        } else if( (right-ball.x)*(right-ball.x)+(bottom-ball.y)*(bottom-ball.y) <= ball.radius*ball.radius && bottom <= ball.y) {     // 右下で衝突
            return 6;
        } else if( (left-ball.x)*(left-ball.x)+(bottom-ball.y)*(bottom-ball.y) <= ball.radius*ball.radius && bottom <= ball.y) {   // 左下で衝突
            return 7;
        } else if( (left-ball.x)*(left-ball.x)+(top-ball.y)*(top-ball.y) <= ball.radius*ball.radius && top >= ball.y) {    // 左上で衝突
            return 8;
        }
        return 0;
    }

}

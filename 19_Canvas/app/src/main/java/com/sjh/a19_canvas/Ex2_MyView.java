package com.sjh.a19_canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.MotionEvent;
import android.view.View;

public class Ex2_MyView extends View {

    Paint paint = new Paint();
    Path path = new Path();
    int x, y;

    public Ex2_MyView(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(10);
        paint.setStyle(Paint.Style.STROKE);

        canvas.drawPath(path, paint);
    }//onDraw()

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //현재 뷰에 터치 이벤트 감지자 지정
        x = (int)event.getX();
        y = (int)event.getY();

        switch ( event.getAction() ){

            case MotionEvent.ACTION_DOWN:
                path.moveTo(x, y);
                break;

            case MotionEvent.ACTION_MOVE:
                x = (int)event.getX();
                y = (int)event.getY();

                path.lineTo(x, y);
                break;

        }//switch

        //변경된 path값을 onDraw()에서 갱신
        invalidate();//onDraw()호출을 위한 메서드
        return true;
    }
}



















































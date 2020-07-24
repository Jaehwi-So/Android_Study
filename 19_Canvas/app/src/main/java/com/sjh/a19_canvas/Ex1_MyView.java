package com.sjh.a19_canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;

public class Ex1_MyView extends View {

    //Path는 그리기 명령을 버퍼에 모아뒀다가 한번에 출력해주는 클래스
    Path path = new Path();

    public Ex1_MyView(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //그림을 그리기 위한 메서드
        //그림을 그리기 위한 붓과 같은 객체
        Paint paint = new Paint();

        //붓의 색상을 지정
        paint.setColor(Color.BLUE);

        canvas.drawRect(100, 200, 300, 400, paint);

        paint.setColor(Color.RED);
        paint.setStyle( Paint.Style.STROKE );
        paint.setStrokeWidth(15);
        canvas.drawCircle( 200, 600, 100, paint );


        paint.setColor(Color.GREEN);
        //테두리만 있는 paint만들기
        paint.setStyle( Paint.Style.STROKE );
        paint.setStrokeWidth(15);//붓의 두께

        //Path를 통해 삼각형 그리기
        path.moveTo(400, 200);//그림을 그리기위해 시작할 좌표
        path.lineTo( 400, 500 );
        path.lineTo(700, 500);
        path.lineTo(400, 200);

        canvas.drawPath(path, paint);
    }
}





















































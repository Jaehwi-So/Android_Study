package com.sjh.a03_view;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class TouchEventActivity extends AppCompatActivity {
    Button btn;
    TextView box, txt_view;
    boolean isCheck; //터치가능 여부. false일 시 터치불가능

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch_event);

        btn = findViewById(R.id.btn_event);
        box = findViewById(R.id.box);
        txt_view = findViewById(R.id.txt_view);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isCheck = !isCheck;
                if(isCheck){
                    box.setText("이벤트 사용가능");
                }
                else{
                    box.setText("이벤트 사용불가능");
                }
            }
        });
        //new 띄고 자동완성
        box.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //txt_view의 갱신을 위한 문자열
                String msg = "";
                //어느 지점을 터치햇는지의 좌표
                int x = 0;  //오른쪽로갈수록증가
                int y = 0;  //아래로갈수록증가

                //파라미터인 MotionEvent는 크게 세가지로 나눌 수 있다.
                switch(event.getAction()){
                    case MotionEvent.ACTION_DOWN:   //1. 화면을 터치했을 경우
                        msg = "ACTION DOWN";
                        break;
                    case MotionEvent.ACTION_UP: //2. 화면에 터치를 뗀 경우
                        msg = "ACTION UP";
                        break;
                    case MotionEvent.ACTION_MOVE:   //3. 터치한 상태에서 손가락을 움직이는 경우
                        x = (int)event.getX();   //터치중인 x좌표
                        y = (int)event.getY();  //터치중인 y좌표
                        msg = String.format("ACTION MOVE (x : %d, y : %d)", x, y);
                        break;
                }

                txt_view.setText(msg);
                //return값이 false인 경우 아직 이벤트 처리가 더 남아있다고 생각하여
                //화면에 ui를 갱신하지 않는다!!

                //단 ACTION_DOWN은 return값과 관계없이 무조건 실행된다.
                return isCheck;
            }
        });
    }
}
package com.sjh.a03_view;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ButtonEventActivity extends AppCompatActivity {

    Button btn1, btn2, btn3;
    TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button_event);

        btn1 = findViewById(R.id.btn_red);
        btn2 = findViewById(R.id.btn_green);
        btn3 = findViewById(R.id.btn_blue);
        txt = findViewById(R.id.txt);

        //1번
        //btn1(red버튼)클릭시 이벤트 처리
 /*       btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //버튼 클릭시 실행할 내용을 정의하는 메서드
                txt.setBackgroundColor( Color.RED );
                txt.setText("빨강");
            }
        });

        btn2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                txt.setBackgroundColor(Color.BLUE);
                txt.setText("초록");
            }
        });*/

        //2번
/*        btn1.setOnClickListener(click);
        btn2.setOnClickListener(click);
        btn3.setOnClickListener(click);*/

        btn1.setOnClickListener(click2);
        btn2.setOnClickListener(click2);
        btn3.setOnClickListener(click2);
    }//onCreate()

    //btn1, btn2, btn3에게 감지자를 묶어서 한 군데에서 처리
    //2번, ID로 구분하기. 이벤트 감지자중 가장 많이 사용하는 형태
    View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.btn_red:  //클릭된 버튼의 id를 구분한다.
                    txt.setBackgroundColor(Color.RED);
                    break;
                case R.id.btn_green:
                    txt.setBackgroundColor(Color.GREEN);
                    break;
            }
        }
    };

    //3번, 버튼으로 구분하기
    View.OnClickListener click2 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String msg = ((Button)v).getText().toString();  //버튼에 쓰여져있는 텍스트 가져오기
            Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
            if(v == btn1){
                txt.setBackgroundColor(Color.RED);
            }
            else if(v == btn2){
                txt.setBackgroundColor(Color.GREEN);
            }
            else if(v == btn3){
                txt.setBackgroundColor(Color.BLUE);
            }
        }
    };

}






















































package com.sjh.a20_handler;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class HandlerActivity extends AppCompatActivity {

    TextView txt_count;
    Button btn_start, btn_stop, btn_reset;
    int num = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);

        txt_count = findViewById(R.id.txt_count);
        btn_start = findViewById(R.id.btn_start);
        btn_reset = findViewById(R.id.btn_reset);
        btn_stop = findViewById(R.id.btn_stop);

        btn_start.setOnClickListener(click);
        btn_reset.setOnClickListener(click);
        btn_stop.setOnClickListener(click);

    }//onCreate()

    View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            switch ( view.getId() ) {

                case R.id.btn_start://핸들러 동작
                    //핸들러의 handleMessage()를 실행
                    myHandler.sendEmptyMessage(0);

                    //핸들러 실행시 start버튼의 중복클릭 방지
                    btn_start.setEnabled(false);//버튼 비활성화
                    break;

                case R.id.btn_stop://핸들러 정지
                    myHandler.removeMessages(0);
                    btn_start.setEnabled(true);//버튼 활성화
                    break;

                case R.id.btn_reset:
                    num = 0;
                    txt_count.setText( String.valueOf( num ) );
                    break;
            }//switch
        }
    };

    //핸들러 생성
    Handler myHandler = new Handler(){

        @Override
        public void handleMessage(Message msg) {

            myHandler.sendEmptyMessageDelayed(0, 1000);

            txt_count.setText("" + ++num);

        }
    };
}


























































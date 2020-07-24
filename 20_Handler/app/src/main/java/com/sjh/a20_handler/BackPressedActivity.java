package com.sjh.a20_handler;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class BackPressedActivity extends AppCompatActivity {

    int num = 3;//3초 카운트 변수

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_back_pressed);

    }//onCreate()

    @Override
    public void onBackPressed() {
        //뒤로가기 버튼 감지
        if( num != 3 ){
            finish();
        }else{
            Toast.makeText(getApplicationContext(),
                    "한 번 더 누르면 종료", Toast.LENGTH_SHORT).show();
        }

        bHandler.sendEmptyMessage(0);
    }//onBackPressed()

    Handler bHandler = new Handler(){

        @Override
        public void handleMessage(Message msg) {

            bHandler.sendEmptyMessageDelayed(0, 1000);

            if(num > 0)
                --num;
            else {
                num = 3;
                bHandler.removeMessages(0);
            }

        }
    };

}










































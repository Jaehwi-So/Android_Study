package com.sjh.a20_handler;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class HandlerWhatActivity extends AppCompatActivity {

    TextView txt;
    Button btn0, btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_what);

        txt = findViewById(R.id.txt);
        btn0 = findViewById(R.id.btn0);
        btn1 = findViewById(R.id.btn1);

        btn0.setOnClickListener(click);
        btn1.setOnClickListener(click);

    }//onCreate()

    View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            switch ( view.getId() ){

                case R.id.btn0:
                    handler.sendEmptyMessage(0);
                    break;

                case R.id.btn1:
                    handler.sendEmptyMessage(1);
                    break;

            }//switch
        }
    };

    Handler handler = new Handler(){

        @Override
        public void handleMessage(Message msg) {

            if( msg.what == 0 ){
                txt.setText("안녕");
            }else{
                txt.setText("반갑습니다");
            }

        }
    };

}






































package com.sjh.a22_rspgame;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button btnStart, btnExit, btnP, btnS, btnR;
    ImageView comR, comS, comP, userR, userS, userP;
    TextView res;
    int com_num = 1;
    int user_num = 1;

    public void animationRSP(boolean done){

        com_num = new Random().nextInt(3) + 1;
        res.setText("" + com_num);
        if(done == false) {
            user_num = new Random().nextInt(3) + 1;
        }
        if(com_num == 1){
            comR.setVisibility(View.INVISIBLE);
            comP.setVisibility(View.INVISIBLE);
            comS.setVisibility(View.VISIBLE);
        }
        else if(com_num == 2){
            comR.setVisibility(View.INVISIBLE);
            comP.setVisibility(View.VISIBLE);
            comS.setVisibility(View.INVISIBLE);
        }
        else{
            comR.setVisibility(View.VISIBLE);
            comP.setVisibility(View.INVISIBLE);
            comS.setVisibility(View.INVISIBLE);
        }

        if(user_num == 1){  //가위
            userR.setVisibility(View.INVISIBLE);
            userP.setVisibility(View.INVISIBLE);
            userS.setVisibility(View.VISIBLE);
        }
        else if(user_num == 2){  //보
            userR.setVisibility(View.INVISIBLE);
            userP.setVisibility(View.VISIBLE);
            userS.setVisibility(View.INVISIBLE);
        }
        else{  //바위
            userR.setVisibility(View.VISIBLE);
            userP.setVisibility(View.INVISIBLE);
            userS.setVisibility(View.INVISIBLE);
        }
    }

    public void get_result(){
        if(com_num - user_num == 1 || com_num - user_num == -2){
            res.setText("당신이 이겼습니다");
        }
        else{
            res.setText("당신이 졌습니다");
        }
        btnStart.setEnabled(true);
        btnR.setEnabled(false);
        btnS.setEnabled(false);
        btnP.setEnabled(false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnStart = findViewById(R.id.btnStart);
        btnExit = findViewById(R.id.btnExit);
        btnR = findViewById(R.id.btnR);
        btnS = findViewById(R.id.btnS);
        btnP = findViewById(R.id.btnP);
        comR = findViewById(R.id.comR);
        comS = findViewById(R.id.comS);
        comP = findViewById(R.id.comP);
        userP = findViewById(R.id.userP);
        userS = findViewById(R.id.userS);
        userR = findViewById(R.id.userR);
        res = findViewById(R.id.res);

        btnStart.setOnClickListener(click);
        btnP.setOnClickListener(click);
        btnS.setOnClickListener(click);
        btnR.setOnClickListener(click);
        btnExit.setOnClickListener(click);
        btnR.setEnabled(false);
        btnS.setEnabled(false);
        btnP.setEnabled(false);
    }

    View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            switch ( view.getId() ) {

                case R.id.btnStart://핸들러 동작
                    myHandler.sendEmptyMessage(0);
                    btnStart.setEnabled(false);
                    btnR.setEnabled(true);
                    btnS.setEnabled(true);
                    btnP.setEnabled(true);
                    break;

                case R.id.btnExit://종료
                    finish();
                    break;

                case R.id.btnP :
                    myHandler.removeMessages(0);
                    user_num = 2;
                    animationRSP(true);
                    get_result();
                    break;
                case R.id.btnS :
                    myHandler.removeMessages(0);
                    user_num = 1;
                    animationRSP(true);
                    get_result();
                    break;
                case R.id.btnR :
                    myHandler.removeMessages(0);
                    user_num = 3;
                    animationRSP(true);
                    get_result();
                    break;

            }//switch
        }
    };

    Handler myHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            myHandler.sendEmptyMessageDelayed(0, 500);
            animationRSP(false);
        }
    };
}
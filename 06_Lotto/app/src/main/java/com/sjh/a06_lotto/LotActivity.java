package com.sjh.a06_lotto;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class LotActivity extends AppCompatActivity {
    //그림자 분신술 쓰기 : 알트누른채로 끌기 혹은 휠누른채로 휠 채로 끌기
    TextView tb;
    Button btn1, btn2, btn3, btn4, btn_retry;
    int number;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lot);
        number = new Random().nextInt(4) + 1;
        tb = findViewById(R.id.tb);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn_retry = findViewById(R.id.btn_retry);

        btn1.setOnClickListener(click);
        btn2.setOnClickListener(click);
        btn3.setOnClickListener(click);
        btn4.setOnClickListener(click);
        btn_retry.setOnClickListener(retry_click);
    }

/*    View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.btn1:  //클릭된 버튼의 id를 구분한다.
                    if(number == 1){
                        tb.setText(number + " 당첨");
                    }
                    else{
                        tb.setText(number + " 실패");
                    }
                    break;
                case R.id.btn2:  //클릭된 버튼의 id를 구분한다.
                    if(number == 2){
                        tb.setText(number + " 당첨");
                    }
                    else{
                        tb.setText(number + " 실패");
                    }
                    break;
                case R.id.btn3:  //클릭된 버튼의 id를 구분한다.
                    if(number == 3){
                        tb.setText(number + " 당첨");
                    }
                    else{
                        tb.setText(number + " 실패");
                    }
                    break;
                case R.id.btn4:  //클릭된 버튼의 id를 구분한다.
                    if(number == 4){
                        tb.setText(number + " 당첨");
                    }
                    else{
                        tb.setText(number + " 실패");
                    }
                    break;
            }
        }
    };*/

    View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int n = Integer.parseInt(((Button)v).getText().toString());
            if(n == number){
                tb.setText(number + " 당첨");
            }
            else{
                tb.setText(number + " 실패");
            }
        }
    };

    View.OnClickListener retry_click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            number = new Random().nextInt(4) + 1;
        }
    };
}






package com.sjh.a05_huaimunsu;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText et;
    Button btn_ok;
    TextView txt_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et = findViewById(R.id.et); //레이아웃에서 ID 검색. setContentView가 잡고있는 레이아웃에 있는 요소만 검색하자
        btn_ok = findViewById(R.id.btn_ok);
        txt_result = findViewById(R.id.txt_result);

        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //EditText에 작성된 원본 문자열
                String str = et.getText().toString().trim();
                String rev = "";
                //원본 문자열을 뒤집어 rev변수에 저장
                for(int i = str.length() - 1; i >= 0; i--){
                    rev += str.charAt(i);
                }

                if(str.equals(rev)){
                    txt_result.setText("회문수 입니다");
                }
                else{
                    txt_result.setText("회문수가 아닙니다");
                }
            }
        });

    }
}
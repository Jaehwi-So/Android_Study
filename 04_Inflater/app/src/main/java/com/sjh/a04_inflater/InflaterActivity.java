package com.sjh.a04_inflater;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

//1번째 줄과 3번째 줄 사이에 2번째 줄을 추가시킨다.
public class InflaterActivity extends AppCompatActivity {
    FrameLayout frame;
    TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inflater);

        frame = findViewById(R.id.frame);

        //LayourInflater : layout(xml)을 View로 객체화 시키기 위한 클래스
        LayoutInflater linf = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);    //시스템에서 레이아웃 서비스를 사용하겠다.

        //View는 (텍스트,버튼..)들의 부모 클래스, R.layout은 ViewGroup으로 layout(여러개의 자식 view을 가질수 있는 layout)이라고 생각하자
        /*View v = linf.inflate(R.layout.inflater_layout, null);  //레이아웃의 주소를 받아서 View 클래스로 변환
        frame.addView(v);*/

        linf.inflate(R.layout.inflater_layout, frame);  //View 클래스로 변환 후 frame에 추가
        txt = findViewById(R.id.txt);
        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "토스트", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
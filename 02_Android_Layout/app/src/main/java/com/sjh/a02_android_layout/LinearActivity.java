package com.sjh.a02_android_layout;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class LinearActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //액티비티가 화면표시를 위해 참조하는 layout은 이름에 대문자가 섞여있으면 오류가 난다!!
        setContentView(R.layout.activity_linear);
    }
}
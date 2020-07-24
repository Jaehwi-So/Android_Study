package com.sjh.a19_canvas;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class Ex1_CanvasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_ex1_canvas);
        setContentView( new Ex1_MyView(this) );
    }
}









































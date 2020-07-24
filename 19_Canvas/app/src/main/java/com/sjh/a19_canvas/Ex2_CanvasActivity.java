package com.sjh.a19_canvas;

import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;

public class Ex2_CanvasActivity extends AppCompatActivity {

    FrameLayout frame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex2_canvas);

        frame = findViewById(R.id.frame);
        frame.addView( new Ex2_MyView(this) );

    }//onCreate()
}
















































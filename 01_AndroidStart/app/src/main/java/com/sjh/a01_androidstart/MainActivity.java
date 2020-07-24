package com.sjh.a01_androidstart;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    /*ctrl + alt + i : 자동정렬 */
    /*AndroidManifest에서 실행흐름 제어*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
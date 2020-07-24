package com.sjh.a02_android_layout;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RelativeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relative);
        Log.i("MY", "--onCreate()--");
    }//onCreate()

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("MY", "--onDestroy()--");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("MY", "--onPause()--");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("MY", "--onRestart()--");

        //Context : 화면제어권자
        Toast.makeText(
                getApplicationContext(), "나는 토스트여라", Toast.LENGTH_SHORT ).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("MY", "--onResume()--");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("MY", "--onStart()--");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("MY", "--onStop()--");
    }
}

/*
앱을 실행하면
onCreate() -- 처음 실행시켰을 때 딱 한번 호출되는 메서드
onStart()
onResume()

홈버튼을 눌러 앱을 빠져나가면
onPause()
onStop()

다시 앱으로 복귀하면
onRestart()
onStart()
onResume()

뒤로가기 버튼으로 앱을 완전히 종료하면
onPause()
onStop()
onDestroy() 앱을 완전히 종료시켰을 때 딱 한번 호출되는 메서드
*/


































































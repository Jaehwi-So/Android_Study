package com.sjh.a15_intent;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class IntentSubActivity extends AppCompatActivity {

    Button btn_pre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_sub);

        btn_pre = findViewById(R.id.btn_pre);
        btn_pre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(IntentSubActivity.this, IntentMainActivity.class);

                //중복페이지 실행방지 플래그
                i.setFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP );

                startActivity(i);
                //finish();

            }
        });

    }//onCreate()
}



















































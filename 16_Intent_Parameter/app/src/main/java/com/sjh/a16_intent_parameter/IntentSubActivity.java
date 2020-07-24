package com.sjh.a16_intent_parameter;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class IntentSubActivity extends AppCompatActivity {
    TextView txt_name, txt_age, txt_tel, txt_birth;
    String name, age, tel, birth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_sub);
        txt_name = findViewById(R.id.txt_name);
        txt_age = findViewById(R.id.txt_age);
        txt_tel = findViewById(R.id.txt_tel);
        txt_birth = findViewById(R.id.txt_birth);


        //메인에서 전달받은 Intent
        Intent i = getIntent();
        //intent에서 값 추출하기 1
/*      name = i.getStringExtra("myName");
        age = i.getStringExtra("myAge");
        tel = i.getStringExtra("myTel");
        birth = i.getStringExtra("myBirth);
        */


        //intent에서 값 추출하기 2
        Bundle bundle = i.getExtras();  //intent에서 bundle추출
        name = bundle.getString("name");
        age = bundle.getString("age");
        tel = bundle.getString("tel");
        birth = bundle.getString("birth");
        txt_name.setText(name);
        txt_age.setText(age);
        txt_tel.setText(tel);
        txt_birth.setText(birth);
    }//onCreate
}
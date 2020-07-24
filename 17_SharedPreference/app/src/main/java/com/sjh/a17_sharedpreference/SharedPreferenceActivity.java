package com.sjh.a17_sharedpreference;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

//SharedPreference의 장점 : 액티비티를 가리지 않는다.
public class SharedPreferenceActivity extends AppCompatActivity {
    EditText et;
    CheckBox check;
    TextView value;
    Button btn_value_up, btn_send;
    int n = 0;  //TextView 갱신용 변수

    SharedPreferences pref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preference);
        pref = getSharedPreferences("SHARE", MODE_PRIVATE); //내부적으로 파일을 만드는 개념. 저장하려는 데이터를 넣음.
        //pref에 저장된 데이터 읽어오기
        n = pref.getInt("my_n", 0);

        et = findViewById(R.id.et);
        check = findViewById(R.id.check);
        value = findViewById(R.id.value);
        btn_value_up = findViewById(R.id.btn_value_up);
        btn_send = findViewById(R.id.btn_send);

        value.setText("" + n);
        et.setText(pref.getString("my_edit",""));
        check.setChecked(pref.getBoolean("my_check", false));

        btn_value_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                value.setText("" + ++n);
            }
        });

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SharedPreferenceActivity.this, SharedSubActivity.class);
                startActivity(i);
            }
        });
    }

    //화면전환시 값 저장을 위한 메서드 오버라이딩
    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor edit = pref.edit();    //내부파일 수정
        edit.putInt("my_n", n);
        edit.putString("my_edit", et.getText().toString());
        edit.putBoolean("my_check", check.isChecked()); //체크박스의 체크상태를 확인
        edit.commit();  //commit()메서드가 호출되지 않으면 데이터가 물리적으로 저장되지 않는다.
    }

}
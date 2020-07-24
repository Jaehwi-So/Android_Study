package com.sjh.a17_sharedpreference;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SharedSubActivity extends AppCompatActivity {
    EditText et;
    CheckBox check;
    TextView txt;
    //SharedPreferenceActivity pa = new SharedPreferenceActivity();  액티비티의 new를통한 생성은 거의 금기
    SharedPreferences pref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_sub);
        pref = getSharedPreferences("SHARE", MODE_PRIVATE);
        et = findViewById(R.id.et);
        check = findViewById(R.id.check);
        txt = findViewById(R.id.txt);

        et.setText(pref.getString("my_edit", ""));
        check.setChecked(pref.getBoolean("my_check", false));
        txt.setText("" + pref.getInt("my_n", 0));
    }//onCreate()


}
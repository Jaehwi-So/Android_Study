package com.sjh.a16_intent_parameter;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class IntentMainActivity extends AppCompatActivity {
    Button btn_date, btn_send;
    EditText edit_birth, edit_name, edit_age, edit_tel;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_main);
        btn_date = findViewById(R.id.btn_date);
        btn_send = findViewById(R.id.btn_send);
        edit_birth = findViewById(R.id.edit_birth);
        edit_name = findViewById(R.id.edit_name);
        edit_age = findViewById(R.id.edit_age);
        edit_tel = findViewById(R.id.edit_tel);

        //전송버튼에 이벤트 감지자 등록
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //SubActivity로 전달할 값들
                String name = edit_name.getText().toString();
                String age = edit_age.getText().toString();
                String tel = edit_tel.getText().toString();
                String birth = edit_birth.getText().toString();

                //화면 전환을 위한 intent
                Intent i = new Intent(IntentMainActivity.this, IntentSubActivity.class);
                //1번째방법) 타 액티비티로 값을 전달하기 . Intent에 담아서 전달하기
/*
                i.putExtra("myName", name);    //Map구조
                i.putExtra("myAge", age);
                i.putExtra("myTel", tel);
                i.putExtra("myBirth", birth);
                startActivity(i);
*/

                //2번째방법) 타 액티비티로 값 전달하기. Bundle에 담아서 전달하기
                //Bundle은 여러가지 타입의 값을 저장하는 Map구조의 클래스. 이미 만들어져있는 액티비티에서 데이터가 넘어올 때 Bundle에 포장되어 값이 넘어옴. 보안성UP
                Bundle bundle = new Bundle();
                bundle.putString("name", name);
                bundle.putString("age", age);
                bundle.putString("tel", tel);
                bundle.putString("birth", birth);
                //Intent에 Bundle을 저장
                i.putExtras(bundle);
                startActivity(i);
            }
        });

        //달력 띄워주기
        btn_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //현재 날짜 구하기
                Calendar now = Calendar.getInstance();  //싱글톤
                int y = now.get(Calendar.YEAR); //년도
                int m = now.get(Calendar.MONTH);    //월
                int d = now.get(Calendar.DAY_OF_MONTH); //일

                //날짜선택 다이얼로그 생성
                dialog = new DatePickerDialog(
                        IntentMainActivity.this,
                        dateSetListener,    //달력의 날짜변경 감지자
                        y, m, d);

                dialog.show();
            }
        });
    }//onCreate

    //날짜 변경 이벤트
    DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int y, int m, int d) {
            //날짜 형식(####-##-##)
            //파라미터 값 중 m은 1월 -> 0, 2월 -> 1...로 넘어온다
            String str = String.format("%d-%02d-%02d", y, m + 1, d);
            //선택된 생일을 EditText에 담는다.
            edit_birth.setText(str);
        }
    };
}
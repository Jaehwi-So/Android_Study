package com.sjh.a04_inflater;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

//어댑터를 이용하여 Inflater을 통한 ListView 동적 생성하기
public class AdapterActivity extends AppCompatActivity {
    Button btn_add;
    EditText et;
    ListView myList;
    //TextView list_form_txt;
    ArrayList<String> arr;

    MyAdapter adapter = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adapter);
        arr = new ArrayList<>();

        myList = findViewById(R.id.myList);
        et = findViewById(R.id.et);
        btn_add = findViewById(R.id.btn_add);

        //어댑터 생성. 생성시 getView()가 자동으로 arr의 size만큼 연속적으로 호출된다.
        adapter = new MyAdapter(AdapterActivity.this, R.layout.list_form, arr, myList);
        //Context는 현재클래스.this로 하자
        //resource는 항목 하나를 구현하기 위한 xml문서.

        //리스트 뷰에 어댑터를 추가 -> 어댑터를 통해 arr의 데이터가 담긴 list_form 뷰가 activity_main에 추가됨
        myList.setAdapter(adapter);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arr.add( et.getText().toString() );
                //myList.setAdapter(adapter);(전체 데이터를 다시 갱신)
                adapter.notifyDataSetChanged(); //어뎁터의 getView()를 갱신하는 메서드(추가로 들어오는 데이터만 갱신)
            }
        });

    }//onCreate
}
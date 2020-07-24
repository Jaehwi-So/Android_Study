package com.sjh.a13_menu_event;

import android.app.Dialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

//메뉴창에서 선택 시 이미지 보이기/숨기기
public class MainActivity extends AppCompatActivity {
    ImageView img;
    Dialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img = findViewById(R.id.img);
    }//onCreate()

    //메뉴 생성
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_menu, menu);
        return true;    //return값은 true
    }

    //메뉴 클릭 시
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.menu:
                dialog = new Dialog(this);
                dialog.setContentView(R.layout.dialog_form);    //dialog가 참조할 레이아웃

                Button btn_inv = dialog.findViewById(R.id.btn_inv); //레이아웃을 통해서 검색
                if(img.getVisibility() != View.VISIBLE){    //이미지 보여주기, 숨기기 버튼 텍스트 설정
                    btn_inv.setText("보이기");
                }
                else{
                    btn_inv.setText("숨기기");
                }

                //다이얼로그 버튼에 이벤트 감지자 등록
                btn_inv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(img.getVisibility() != View.VISIBLE){    //이미지 보여주기, 숨기기 버튼 텍스트 설정
                            img.setVisibility(View.VISIBLE);
                        }
                        else{
                            img.setVisibility(View.INVISIBLE);
                        }
                        dialog.dismiss();   //다이얼로그 숨기기
                    }
                });

                dialog.show();
                break;
        }
        return true;
    }
}
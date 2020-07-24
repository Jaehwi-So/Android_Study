package com.sjh.a11_menupopupmenu;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PopupMenuActivity extends AppCompatActivity {

    Button menu_show_btn;
    View anchor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_menu);

        anchor = findViewById(R.id.anchor);
        menu_show_btn = findViewById(R.id.menu_show_btn);
        menu_show_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //팝업메뉴 생성
                PopupMenu popup = new PopupMenu(
                        PopupMenuActivity.this, //화면제어권자
                        anchor); //anchor : 팝업을 띄울 기준 view

                //팝업메뉴에 참조시킬 메뉴 참조파일을 등록
                getMenuInflater().inflate( R.menu.my_menu, popup.getMenu() );
                //메뉴에 이벤트 감지자 등록
                popup.setOnMenuItemClickListener(popupClick);

                popup.show();//팝업메뉴 노출
            }
        });

    }//onCreate()

    PopupMenu.OnMenuItemClickListener popupClick = new PopupMenu.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem item) {

            switch ( item.getItemId() ){
                case R.id.add:
                    Toast.makeText(getApplicationContext(), "ADD", Toast.LENGTH_SHORT).show();
                    break;

                case R.id.edit:
                    Toast.makeText(getApplicationContext(), "EDIT", Toast.LENGTH_SHORT).show();
                    break;

                case R.id.finish:
                    finish();
                    break;
            }//switch

            return true;
        }
    };

}




























































package com.sjh.a24_appdrawer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawer_layout;
    View drawer;
    Button btn_open, btn_close;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawer_layout = findViewById(R.id.drawer_layout);
        drawer = findViewById(R.id.drawer); //서랍 객체
        btn_open = findViewById(R.id.btn_open);
        btn_close = findViewById(R.id.btn_close);

        btn_open.setOnClickListener(drawer_click);
        btn_close.setOnClickListener(drawer_click);

    }//onCreate()

    //버튼 이벤트처리
    View.OnClickListener drawer_click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch(view.getId()){
                case R.id.btn_open:
                    drawer_layout.openDrawer(drawer); //서랍 열기
                    break;
                case R.id.btn_close:
                    //왼쪽이나 오른쪽 서랍중 원하는 서랍을 닫는다.
                    drawer_layout.closeDrawer(drawer);

                    //drawer_layout.closeDrawers(); //열려진 모든 서랍을 닫음(왼쪽, 오른쪽 서랍 전부)
                    break;
            }
        }
    };

}
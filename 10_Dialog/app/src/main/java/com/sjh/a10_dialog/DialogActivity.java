package com.sjh.a10_dialog;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

//다이얼로그(Alert창)
public class DialogActivity extends AppCompatActivity {

    Button show_dialog, btn1, btn2;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);

        show_dialog = findViewById(R.id.show_dialog);
        show_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //다이얼로그 생성
                dialog = new Dialog(DialogActivity.this);

                //다이얼로그 배경 반투명 효과 제거(반드시 setContentView보다 위쪽에서 할 것!)
                dialog.getWindow().clearFlags(
                        WindowManager.LayoutParams.FLAG_DIM_BEHIND );

                //다이얼로그의 자체 배경을 투명하게 설정
                dialog.getWindow().setBackgroundDrawable(
                        new ColorDrawable( Color.TRANSPARENT ));

                //다이얼로그가 참조할 레이아웃 등록
                dialog.setContentView(R.layout.dialog_layout);


                //버튼에 이벤트 감지자 등록
                btn1 = dialog.findViewById(R.id.btn1);
                btn2 = dialog.findViewById(R.id.btn2);

                btn1.setOnClickListener(click);
                btn2.setOnClickListener(click);

                //뒤로가기나 주변터치로 다이얼로그가 종료되는 것을 방지
                dialog.setCancelable(false);

                dialog.show();

            }
        });

    }//onCreate()

    View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch ( v.getId() ){

                case R.id.btn1:
                    finish();
                    break;

                case R.id.btn2:
                    dialog.dismiss();//다이얼로그 종료
                    break;

            }//switch

        }
    };

}



































package com.sjh.a14_alertdialog;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AlertDialogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert_dialog);
    }

    //뒤로가기 버튼 감지
    @Override
    public void onBackPressed() {
        //super.onBackPressed(); super를 사용하면 뒤로가기 클릭시 무조건 강제종료
        AlertDialog.Builder dialog = new AlertDialog.Builder(this); //AlertDialog가 가진 Builder클래스
        dialog.setTitle("어플제목");
        dialog.setMessage("종료하시겠습니까?");
        dialog.setPositiveButton("확인", new DialogInterface.OnClickListener() {  //확인버튼 누를시 이벤트 감지자
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();   //종료
            }
        });

        dialog.setNegativeButton("취소", click);
        dialog.setNeutralButton("생각좀..", click);
        dialog.setNegativeButton("no", null);
        dialog.show();
    }//onBackPressed()

    DialogInterface.OnClickListener click = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {   // i : 버튼상태
            switch(i){
                case DialogInterface.BUTTON_NEGATIVE:
                    break;
                case DialogInterface.BUTTON_NEUTRAL:
                    Toast.makeText(getApplicationContext(),"생각중", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };
}
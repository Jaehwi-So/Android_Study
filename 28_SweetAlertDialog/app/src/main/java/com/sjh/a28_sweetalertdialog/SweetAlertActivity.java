package com.sjh.a28_sweetalertdialog;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;

public class SweetAlertActivity extends AppCompatActivity {

    SweetAlertDialog sweetAlertDialog;
    Button warning, error, success, btn_default;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sweet_alert);

        warning = findViewById(R.id.warning);
        error = findViewById(R.id.error);
        success = findViewById(R.id.success);
        btn_default = findViewById(R.id.btn_default);

        warning.setOnClickListener( click );
        error.setOnClickListener( click );
        success.setOnClickListener( click );
        btn_default.setOnClickListener( click );

    }//onCreate()

    View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            switch ( view.getId() ){
                case R.id.warning:
                    sweetAlertDialog = new SweetAlertDialog(
                            SweetAlertActivity.this, SweetAlertDialog.WARNING_TYPE);
                    sweetAlertDialog.setTitleText("경고 다이얼로그");
                    sweetAlertDialog.setContentText("서버 연결이 원활하지 않습니다");
                    sweetAlertDialog.setConfirmText("확인");
                    sweetAlertDialog.setCancelText("취소");
                    sweetAlertDialog.show();
                    break;

                case R.id.error:
                    sweetAlertDialog = new SweetAlertDialog(
                            SweetAlertActivity.this, SweetAlertDialog.ERROR_TYPE);
                    sweetAlertDialog.setTitleText("오류 다이얼로그");
                    sweetAlertDialog.setContentText("서버 접속이 끊겼습니다");
                    sweetAlertDialog.setConfirmText("확인");
                    sweetAlertDialog.setCancelText("취소");
                    sweetAlertDialog.show();
                    break;

                case R.id.success:
                    sweetAlertDialog = new SweetAlertDialog(
                            SweetAlertActivity.this, SweetAlertDialog.SUCCESS_TYPE);
                    sweetAlertDialog.setTitleText("SUCCESS 다이얼로그");
                    sweetAlertDialog.setContentText("서버 접속 성공");
                    sweetAlertDialog.setConfirmText("확인");
                    sweetAlertDialog.show();
                    break;

                case R.id.btn_default:
                    sweetAlertDialog = new SweetAlertDialog(
                            SweetAlertActivity.this, SweetAlertDialog.CUSTOM_IMAGE_TYPE);
                    sweetAlertDialog.setTitleText("CUSTOM 다이얼로그");
                    sweetAlertDialog.setContentText("커스텀 다이얼로그입니다");
                    sweetAlertDialog.setConfirmText("확인");
                    sweetAlertDialog.setCancelText("취소");
                    sweetAlertDialog.setCustomImage(R.mipmap.ic_launcher);

                    //확인버튼에 이벤트 감지자 등록
                    sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            Toast.makeText(getApplicationContext(), "확인되었습니다", Toast.LENGTH_SHORT).show();
                            sweetAlertDialog.dismiss(); //다이얼로그 종료
                        }
                    });

                    //취소버튼에 이벤트 감지자 등록.
                    sweetAlertDialog.setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            Toast.makeText(getApplicationContext(), "취소하였습니다", Toast.LENGTH_SHORT).show();
                            sweetAlertDialog.dismiss(); //감지자를 따로 등록 시 다이얼로그 종료를 넣어야함
                        }
                    });

                    sweetAlertDialog.show();
                    break;
            }//switch

        }
    };

}




















































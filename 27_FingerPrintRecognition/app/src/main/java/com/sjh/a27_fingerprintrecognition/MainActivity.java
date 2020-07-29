package com.sjh.a27_fingerprintrecognition;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.github.ajalt.reprint.core.AuthenticationFailureReason;
import com.github.ajalt.reprint.core.AuthenticationListener;
import com.github.ajalt.reprint.core.Reprint;

//ajart/reprint라이브러리 사용
public class MainActivity extends AppCompatActivity {
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = findViewById(R.id.text);

        //권한 사용여부 판단위해 자체적 초기화. 지문인식 지원이 되는 기종인지 확인
        Reprint.initialize(this);

        if(checkDeviceSpeck()){
            Reprint.authenticate(new AuthenticationListener() {
                @Override
                public void onSuccess(int moduleTag) {
                    text.setText("인증성공");
                }

                @Override
                public void onFailure(AuthenticationFailureReason failureReason, boolean fatal, CharSequence errorMessage, int moduleTag, int errorCode) {
                    text.setText("인증실패");
                }
            });
        }
    }

    //지문사용 가능 여부를 판단하기 위한 메서드
    private boolean checkDeviceSpeck(){
        //1. 하드웨어적으로 지문인식 센서가 없는 경우
        boolean isHardware = Reprint.isHardwarePresent();   //지문인식을 지원하는 기기인지 확인
        //2. 지문인식은 지원하지만 등록한 지문이 없는 경우
        boolean hasRegister = Reprint.hasFingerprintRegistered();   //지문인식을 지원하나 등록된 지문이 있는지 확인
        if(!isHardware){
            Toast.makeText(getApplicationContext(), "지문인식을 지원하지 않는 기기입니다", Toast.LENGTH_SHORT).show();
        }
        else if(!hasRegister){
            Toast.makeText(getApplicationContext(), "기기에 등록된 지문이 없습니다. \n지문등록을 먼저 해주세요", Toast.LENGTH_SHORT).show();
        }

        Toast.makeText(getApplicationContext(), "인증 성공!!", Toast.LENGTH_SHORT).show();
        return isHardware && hasRegister;
    }//checkDeviceSpeck()
}
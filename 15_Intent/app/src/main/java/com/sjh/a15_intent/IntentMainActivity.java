package com.sjh.a15_intent;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class IntentMainActivity extends AppCompatActivity {

    Button btn_link, btn_call, btn_sms, btn_camera, btn_gallery, btn_next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_main);

        btn_link = findViewById(R.id.btn_link);
        btn_call = findViewById(R.id.btn_call);
        btn_camera = findViewById(R.id.btn_camera);
        btn_gallery = findViewById(R.id.btn_gallery);
        btn_next = findViewById(R.id.btn_next);
        btn_sms = findViewById(R.id.btn_sms);

        btn_link.setOnClickListener( click );
        btn_call.setOnClickListener( click );
        btn_camera.setOnClickListener( click );
        btn_gallery.setOnClickListener( click );
        btn_next.setOnClickListener( click );
        btn_sms.setOnClickListener( click );

    }//onCreate()

    View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            //안드로이드에서 화면전환을 위해 필요한 클래스
            Intent intent = null;

            switch ( view.getId() ){

                case R.id.btn_link://웹페이지로 전환
                    intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData( Uri.parse("https://www.naver.com") );
                    startActivity(intent);//화면전환
                    break;

                case R.id.btn_call://다이얼 화면으로 전환
                    /*intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData( Uri.parse("tel:010-111-2222") );
                    startActivity(intent);*/

                    //전화를 즉시 걸어주는 기능
                    intent = new Intent(Intent.ACTION_CALL);
                    intent.setData(Uri.parse("tel:01093321823"));
                    startActivity(intent);
                    break;

                case R.id.btn_sms://문자전송 화면으로 전환
                    intent = new Intent( Intent.ACTION_SENDTO );
                    intent.setData( Uri.parse("smsto:0101112222") );
                    intent.putExtra("sms_body", "안녕하세요");
                    startActivity(intent);
                    break;

                case R.id.btn_camera:
                    //내장카메라로 연결
                    //intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    //startActivity(intent);

                    //동영상 페이지로 연결
                    intent = new Intent( MediaStore.ACTION_VIDEO_CAPTURE );
                    startActivity(intent);
                    break;

                case R.id.btn_gallery:
                    intent = new Intent(Intent.ACTION_GET_CONTENT);
                    intent.setType("image/*");//모든 타입의 이미지 포함
                    startActivity(intent);
                    break;

                case R.id.btn_next://사용자가 지정한 Activity로 화면 전환

                    //Intent를 통해 사용자 지정 페이지로 전환을 하려면
                    //전환을 위한 페이지가 반드시 Manifest.xml에 등록되어 있어야 한다.
                    intent = new Intent(
                            IntentMainActivity.this, IntentSubActivity.class );

                    //intent.setClass(IntentMainActivity.this, IntentSubActivity.class);
                    startActivity(intent);
                    break;
            }//switch

        }
    };

}


























































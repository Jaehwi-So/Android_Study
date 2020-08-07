package com.sjh.a31_openapiconnection_captcha;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    ImageView img;
    EditText et;
    Button btn_check, btn_re;

    /*해당 부분 초기화하여 사용*/
    final String ID = "ID 입력";
    final String SECRET = "SECRET 입력";

    static String result_key;//이미지 요청을 위한 키값

    ApiExamCaptchaNkey nkey;//캡차 키 발급을 위한 클래스
    ApiExamCaptchaImage nImage;//발급받은 키로 이미지 생성
    ApiExamCaptchaNkeyResult nResult;//이미지와 사용자의 입력값을 비교 클래스

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img = findViewById(R.id.img);
        et = findViewById(R.id.et);
        btn_check = findViewById(R.id.btn_check);
        btn_re = findViewById(R.id.btn_re);

        nkey = new ApiExamCaptchaNkey();
        nImage = new ApiExamCaptchaImage();
        nResult = new ApiExamCaptchaNkeyResult();

        new CaptchaKeyAsync().execute();

        //이미지 새로고침
        btn_re.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et.setText("");
                new CaptchaKeyAsync().execute();
            }
        });

        //확인(캡차 검증)
        btn_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new CaptchaResultAsync().execute( et.getText().toString() );
            }
        });

    }//onCreate()

    //캡차키 발급을 위한 Async
    class CaptchaKeyAsync extends AsyncTask<Void, Void, String>{

        @Override
        protected String doInBackground(Void... voids) {
            return nkey.getNkey(ID, SECRET);
        }//doInBackground()

        @Override
        protected void onPostExecute(String s) {
            result_key = s;
            try {
                JSONObject jsonObject = new JSONObject(s);
                result_key = jsonObject.getString("key");

            } catch (JSONException e) {
                e.printStackTrace();
            }

            Log.i("MY", result_key);

            //키발급이 완료되면 발급받은 키로 이미지 생성하는 Async호출
            new CaptchaImageAsync().execute();

        }//onPostExecute()
    }//CaptchaKeyAsync클래스

    //발급받은 키로 이미지를 생성하는 Async
    class CaptchaImageAsync extends AsyncTask<Void, Void, Bitmap>{

        @Override
        protected Bitmap doInBackground(Void... voids) {
            return nImage.getCaptchaImage(ID, SECRET);
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            Log.i("MY", "" + bitmap);
            img.setImageBitmap(bitmap);
        }
    }//CaptchaImageAsync클래스

    //캡차 입력갑 비교 Async
    class CaptchaResultAsync extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... strings) {
            return nResult.getNkeyResult(ID, SECRET, strings[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            Log.i("TEST", s);
            try {
                JSONObject jsonObject = new JSONObject(s);
                boolean res = jsonObject.getBoolean("result");

                if( res ){
                    Toast.makeText(
                            getApplicationContext(), "인증 성공!", Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(
                            getApplicationContext(), "인증 실패!", Toast.LENGTH_SHORT).show();

                    //인증 실패시 새로운 캡차 이미지를 로드
                    new CaptchaKeyAsync().execute();
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }//CaptchaResultAsync클래스

}
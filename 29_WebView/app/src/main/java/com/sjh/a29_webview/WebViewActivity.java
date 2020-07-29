package com.sjh.a29_webview;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

//WEBview의 경우 약관..이런거 보여줄 때 주로 사용됨. 통신속도가 느려서 많이사용x
public class WebViewActivity extends AppCompatActivity {
    WebView myWebView;
    TextView tv;
    Button btn_send;
    EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        myWebView = findViewById(R.id.myWebView);
        tv = findViewById(R.id.tv);
        btn_send = findViewById(R.id.btn_send);
        et = findViewById(R.id.et);

        //웹페이지에 웹뷰를 연결
        myWebView.loadUrl("http://192.168.1.15:9090/29_Ex1_Webview/page.html ");
        WebSettings ws = myWebView.getSettings();
        ws.setJavaScriptEnabled(true);  //자바스크립트 기능 사용

        //자바스크립트 감지자 ok
        myWebView.addJavascriptInterface( new Object(){

            @JavascriptInterface
            public void performClick(String id, String pwd){
                Toast.makeText(getApplicationContext(), id+'/'+pwd, Toast.LENGTH_SHORT).show();
                et.setText(id + "/" + pwd);
            }
        }, "ok" );

        //자바스크립트 감지자 forJson
        myWebView.addJavascriptInterface(new Object(){

            @JavascriptInterface
            public void performClick( String course ){

                try {
                    //단일 JSON타입 {'name':'웹개발과정'....}
                    JSONObject jsonObject = new JSONObject(course);
                    String name = "과정명 : " + jsonObject.getString("name");
                    String start = "시작일 : " + jsonObject.getString("start");
                    String end = "종료일 : " + jsonObject.getString("end");
                    String result1 = name + "\n" + start + "\n" + end + "\n";

                    //JSON배열 'sub':['java', 'jsp', 'android']
                    String subject = "과목명 : ";
                    JSONArray arr = new JSONObject(course).getJSONArray("sub");
                    for(int i = 0; i < arr.length(); i++){
                        subject += arr.get(i) + " / ";
                    }
                    String result2 = subject + "\n";

                    //JSON 다중배열  'student':[{'name':'kim'}, {'name':'lee'}, {'name':'park'}]
                    String student = "학생명 : ";
                    JSONArray stuArr = new JSONObject(course).getJSONArray("student");
                    for(int i = 0; i < arr.length(); i++){
                        student += stuArr.getJSONObject(i).getString("name") + " / ";
                    }
                    String result3 = student + "\n";

                    tv.setText( result1 + result2 + result3);
                    //스레드문제일 시 이렇게 넘김
                    /*final String result1 = name + "\n" + start + "\n" + end + "\n";

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tv.setText( result1 );
                        }
                    });*/

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }, "forJson");

        //액티비티 최하단 send버튼 클릭 시 editText의 내용을 웹으로 전달
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str = et.getText().toString();

                //str을 html의 send()메서드로 전달
                String result = String.format("javascript:send('%s');", str);
                myWebView.loadUrl(result);
            }
        });

   }//onCreate()
}
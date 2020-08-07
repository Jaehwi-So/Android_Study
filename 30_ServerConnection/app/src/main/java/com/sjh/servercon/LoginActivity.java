package com.sjh.servercon;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.sjh.servercon.util.Util;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class LoginActivity extends AppCompatActivity {

    EditText edit_id, edit_pwd;
    Button btn_login, btn_regi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edit_id = findViewById(R.id.edit_id);
        edit_pwd = findViewById(R.id.edit_pwd);
        btn_login = findViewById(R.id.btn_login);
        btn_regi = findViewById(R.id.btn_regi);

        btn_regi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(i);
                finish();
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String id = edit_id.getText().toString().trim();
                String pwd = edit_pwd.getText().toString().trim();
                String result = "id=" + id + "&pwd=" + pwd;

                new LoginTask().execute(result, Util.TYPE_LOGIN);

            }
        });
    }//onCreate

    //로그인용 Task클래스
    class LoginTask extends AsyncTask<String, Void, String> {

        String ip = Util.IP; //서버의 ip
        String sendMsg, receiveMsg;
        String serverip = Util.SERVER_IP; //연결할 서버의 주소

        @Override
        protected String doInBackground(String... strings) {

            try {

                String str = "";
                URL url = new URL( serverip );

                //서버 연결
                HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                conn.setRequestMethod("POST");
                OutputStreamWriter osw = new OutputStreamWriter( conn.getOutputStream() );

                //List.jsp?id=aa&pwd=1111&type=type_regi
                sendMsg = strings[0] + "&type=" + strings[1];

                //서버로 파라미터 전달
                osw.write(sendMsg);
                osw.flush();

                //전송이 완료되면 서버에서 처리한 결과값을 받는다
                //conn.getResponseCode() : 200이면 문제없음 // HTTP_OK = 200
                //conn.getResponseCode() : 404, 500 등 비정상 전송
                if( conn.getResponseCode() == conn.HTTP_OK ){

                    //서버의 데이터 읽기
                    InputStreamReader tmp = new InputStreamReader(
                            conn.getInputStream(), "utf-8" );

                    BufferedReader reader = new BufferedReader( tmp );
                    StringBuffer buffer = new StringBuffer();

                    while( ( str = reader.readLine() ) != null ){

                        buffer.append(str);

                    }//while
                    //receiveMsg += str;


                    receiveMsg = buffer.toString();

                    JSONArray jarray = new JSONObject( receiveMsg ).getJSONArray("res");
                    JSONObject jObject = jarray.getJSONObject(0);
                    String result = jObject.getString("result");

                    if( result.equals("success") ){
                        receiveMsg = "로그인성공";
                    }else{
                        receiveMsg = "아이디나 비밀번호가 일치하지 않습니다";
                    }
                }

            }catch (Exception e){

            }
            return receiveMsg;
        }

        @Override
        protected void onPostExecute(String s) {
            Toast.makeText(getApplicationContext(), s,Toast.LENGTH_SHORT).show();

            if( s.equals("로그인성공") ){
                //가입성공시 로그인 페이지로 전환
                Intent i = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(i);
                finish();
            }

        }
    }//RegiTask

}
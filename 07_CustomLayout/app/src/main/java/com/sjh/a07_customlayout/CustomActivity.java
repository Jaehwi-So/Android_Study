package com.sjh.a07_customlayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

//Activity 안에서 Layout을 직접 생성하기
public class CustomActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_custom); //레이아웃을 호출

        //추가하고자 하는 레이아웃을 생성
        LinearLayout rootLayout = new LinearLayout(this);   //context를 parameter로넘김

        //rootLayout에 방향설정
        rootLayout.setOrientation(LinearLayout.VERTICAL);   //방향성 설정

        //생성된 레이아웃에 버튼을 추가
        for(int i = 0; i < 3; i++){
            Button btn = new Button(CustomActivity.this);
            btn.setId(i);   //Activity에서 id를 지정할 때 정수형태밖에 안됨
            btn.setText("btn" + i);

            //버튼의 너비와 높이를 wrap_content로 변경
            btn.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT)
            );
            /*btn.setWidth(LinearLayout.LayoutParams.WRAP_CONTENT);
            btn.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);*/

            //버튼에 이벤트 감지자 등록
            btn.setOnClickListener(click);

            //생성된 버튼을 root layout에 추가
            rootLayout.addView(btn);
        }

        setContentView(rootLayout); //레이아웃을 호출
    }//onCreate

    View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case 0 :
                    Toast.makeText(getApplicationContext(), "0!!", Toast.LENGTH_SHORT).show();
                    break;
                case 1 :
                    Toast.makeText(CustomActivity.this, "1!!", Toast.LENGTH_SHORT).show();
                    //context에 그냥 this를 쓰게되면 View객체를 가리키게됨
                    break;
                case 2 :
                    Toast.makeText(getApplicationContext(), "2!!", Toast.LENGTH_SHORT).show();
                    break;
                default :
                    break;
            }
        }
    };

}
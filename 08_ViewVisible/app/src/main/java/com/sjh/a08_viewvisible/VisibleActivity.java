package com.sjh.a08_viewvisible;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class VisibleActivity extends AppCompatActivity {
    Button back1, back2, bottom, btn_hello;
    ImageView back1_img, back2_img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visible);
        back1 = findViewById(R.id.back1);
        back2 = findViewById(R.id.back2);
        bottom = findViewById(R.id.bottom);
        btn_hello = findViewById(R.id.btn_hello);
        back1_img = findViewById(R.id.back1_img);
        back2_img = findViewById(R.id.back2_img);

        back1.setOnClickListener(click);
        back2.setOnClickListener(click);
        bottom.setOnClickListener(click);
    }

    View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.back1:
                    back1_img.setVisibility(View.VISIBLE);
                    back2_img.setVisibility(View.INVISIBLE);
                    break;
                case R.id.back2:
                    back1_img.setVisibility(View.INVISIBLE);
                    back2_img.setVisibility(View.VISIBLE);
                    break;
                case R.id.bottom:
                    if(btn_hello.getVisibility() != View.VISIBLE){
                        btn_hello.setVisibility(View.VISIBLE);
                    }
                    else{
                        btn_hello.setVisibility(View.GONE);
                    }
                    break;
            }
        }
    };

}
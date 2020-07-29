package com.sjh.a25_swiperefreshlayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class MainActivity extends AppCompatActivity {

    SwipeRefreshLayout swipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        swipe = findViewById(R.id.swipe);

        //디스크(동글뱅이 돌아가는거)의 배경 색 변경
        swipe.setProgressBackgroundColorSchemeColor(Color.argb(255,100,125,255));

        //다스크 사이즈 변경(기본:DEFAULT, 큰사이즈:LARGE)
        swipe.setSize(SwipeRefreshLayout.LARGE);

        //디스크를 당길 최대 좌표 지정, scale:true로 지정시 점점 사이즈가커짐
        //swipe.setProgressViewEndTarget(true, 400);

        //디스크를 당길 시작, 최대 좌표 지정
        swipe.setProgressViewOffset(false, 200, 500);

        //디스크 안에 돌아가는 동그란거 색깔 바꿈
        swipe.setColorSchemeResources(R.color.c1, R.color.c2, R.color.c3, R.color.c4);

        //SwipeRefreshLayout에게 로딩 시작 시 이벤트 감지자 등록(당긴 손을 놓았을 때 호출)
        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                h.sendEmptyMessageDelayed(0,1000);
            }
        });
    }


    Handler h = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            //로딩 완료시 디스크 제거
            swipe.setRefreshing(false);
        }
    };

}
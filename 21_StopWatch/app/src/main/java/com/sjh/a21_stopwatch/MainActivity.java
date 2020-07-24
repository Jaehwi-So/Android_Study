package com.sjh.a21_stopwatch;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    TextView time_out, record;
    Button btn_start, btn_rec;
    ScrollView scroll;

    final static int INIT = 0;//처음상태
    final static int RUN = 1;//진행
    final static int PAUSE = 2;//일시정지

    //현재 상태를 저장할 변수
    int cur_Status = INIT;
    int myCount = 1;//기록시 넘버링을 위한 변수

    //타이머의 진행도를 계산하기 위한 변수
    long myBaseTime, myPauseTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        time_out = findViewById(R.id.time_out);
        record = findViewById(R.id.record);
        scroll = findViewById(R.id.scroll);
        btn_start = findViewById(R.id.btn_start);
        btn_rec = findViewById(R.id.btn_rec);

        btn_start.setOnClickListener(myClick);
        btn_rec.setOnClickListener(myClick);

    }//onCreate()

    View.OnClickListener myClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            switch ( view.getId() ){
                case R.id.btn_start://시작버튼 클릭

                    switch ( cur_Status ){

                        case INIT://초기상태

                            //애플리케이션이 실행되고 나서 elapsedRealtime()메서드가
                            //호출될때 까지 경과된 시간
                            myBaseTime = SystemClock.elapsedRealtime();

                            //핸들러 호출
                            myTimer.sendEmptyMessage(0);

                            btn_start.setText("멈춤");
                            btn_rec.setEnabled(true);//기록버튼 활성화

                            cur_Status = RUN;//현재상태 변경
                            break;

                        case RUN://스톱워치 진행중
                            //핸들러 정지
                            myTimer.removeMessages(0);

                            //스톱워치가 정지한 시간을 저장
                            myPauseTime = SystemClock.elapsedRealtime();

                            btn_start.setText("시작");
                            btn_rec.setText("리셋");

                            cur_Status = PAUSE;
                            break;

                        case PAUSE://스톱워치 일시정지
                            long now = SystemClock.elapsedRealtime();

                            //핸들러 호출
                            myTimer.sendEmptyMessage(0);

                            myBaseTime += (now - myPauseTime);

                            btn_start.setText("멈춤");
                            btn_rec.setText("기록");

                            cur_Status = RUN;
                            break;

                    }//inner switch

                    break;

                case R.id.btn_rec://기록버튼 클릭
                    switch(cur_Status){
                        case RUN:   //기록
                            String str = record.getText().toString();
                            str += String.format("%d. %s\n", myCount++, getTimeOut());
                            record.setText(str);
                            //새로운 기록이 추가될 때 마다 스크롤뷰를 마지막 추가된 값으로 갱신
                            scroll.scrollTo(0,record.getHeight());  //
                            break;
                        case PAUSE: //초기화
                            btn_rec.setText("기록");
                            btn_rec.setEnabled(false);  //기록버튼 비활성화

                            myCount = 1;
                            record.setText("");
                            time_out.setText("00:00:00");
                            cur_Status = INIT;
                            break;
                    }
                    break;
            }//switch

        }
    };

    //화면 갱신용 핸들러
    Handler myTimer = new Handler(){

        @Override
        public void handleMessage(Message msg) {

            time_out.setText( getTimeOut() );
            myTimer.sendEmptyMessage(0);
        }
    };

    //경과시간을 텍스트뷰에 갱신하는 메서드
    private String getTimeOut(){
        long now = SystemClock.elapsedRealtime();
        long outTime = now - myBaseTime;

        //00:00:00형시으로 outTime의 포멧을 지정
        String resultTime = String.format("%02d:%02d:%02d",
                                            outTime/1000/60, //분
                                            outTime/1000%60, //초
                                            outTime%1000/10);//밀리세컨드

        return resultTime;
    }

}











































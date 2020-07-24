package com.sjh.a18_openapiconnect;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    static EditText search;//검색어
    ListView myListView;
    Button search_btn;
    Parser parser;
    ArrayList<BookVO> list;
    ViewModelAdapter adapter;

    //스크롤링을 위한 객체들
    int start = 1;
    LayoutInflater mInflater;
    View footerView;
    boolean mLockListView = true;   //true일 때 추가로드, false일 때 로드안함

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        search = findViewById(R.id.search);
        search_btn = findViewById(R.id.search_btn);
        myListView = findViewById(R.id.myListView);

        parser = new Parser();
        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(search.getText().toString().trim().length() > 0){    //한 글자 이상 입력받은 경우에만 검색 시작
                    list = new ArrayList<>();
                    adapter = null;
                    start = 1;
                    //list = parser.connectNaver(list);   //서버통신은 반드시 백그라운드 스레드에서 하는 것이 원칙이 되어 이런식으로는 호출할 수 없다.
                    new NaverAsync().execute(); //execute() : doInBackground()를 호출하는 메서드
                }
            }
        });

        //footer.xml 객체화
        mInflater = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);  //adapter가 아닌 activity이므로 context얻을필요X
        footerView = mInflater.inflate(R.layout.footer, null);
    }//onCreate()

    //백그라운드 통신을 위한 스레드 클래스인 AsyncTask를 생성(서버 통신 전용 클래스) Alt+Enter통해 Override
    /*
    AsyncTask는 생성 시 세 개의 제너릭 타입을 가진다.
    1. doInBackground의 parameter type
    2. onProgressUpdate()(UI의 진행상황을 알려주는 메서드)이 오버라이딩 되어있는 경우 해당 메서드에서 사용할 자료형 타입
    3. doInBackground의 반환형이자, 작업 결과를 반영하는 onPostExecute()의 파라미터 타입
     */
    class NaverAsync extends AsyncTask<String, Void, ArrayList<BookVO>> {

        @Override  //각종 반복이나 제어 등 주된 처리 로직을 담당
        protected ArrayList<BookVO> doInBackground(String... strings) {
            //strings[0], strings[1]...의 파라미터가 넘어온다.
            return parser.connectNaver(start, list);
        }//doInBackground

        @Override   //통신이 완료되면 return값을 가지고 호출되는 메서드
        protected void onPostExecute(ArrayList<BookVO> bookVOS) {
            if(adapter == null){
                adapter = new ViewModelAdapter(MainActivity.this, R.layout.book_item, bookVOS);
                //리스트뷰에 스크롤 감지자를 등록
                myListView.setOnScrollListener(scrollListner);
                //리스트뷰에 footer를 등록(반드시 setAdapter보다 위에서 한다)
                myListView.addFooterView(footerView);
                footerView.setVisibility(View.VISIBLE);
                myListView.setAdapter(adapter);
            }
            adapter.notifyDataSetChanged(); //어댑터에 변경사항이 존재시 갱신
            mLockListView = false;
        }//onPostExecute
    }//NaverAsync

    //리스트뷰의 스크롤 감지자
    AbsListView.OnScrollListener scrollListner = new AbsListView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(AbsListView absListView, int i) {

        }


        @Override
        //스크롤이 발생할 때 마다 지속적으로 호출됨
        //firstVisibleItem : 리스트뷰 최상단 item의 인덱스 번호를 가져온다.
        //visibleItemCount : 현재 리스트뷰가 보여주고 있는 Item의 갯수
        //totalItemCount : 리스트뷰의 총 항목 수
        public void onScroll(AbsListView absListView, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
            int count = totalItemCount - visibleItemCount;  //(10 - 3 = 7)
            //count와 firstVisibleItem의 값이 같아지면 가장 아래로 스크롤 되었다고 판단할 수 있다.
            if(firstVisibleItem >= count && totalItemCount != 0 && !mLockListView){
                mLockListView = true;
                //총 1000개의 데이터 검색
                if(start < 1000 - PageCount.COUNT && list.size() >= PageCount.COUNT){
                    if(start >= list.size()){
                        start = 1000 - PageCount.COUNT;
                    }
                    start += PageCount.COUNT;
                    //스크롤이 끝나면 10개씩 자동 로드
                    //new NaverAsync().execute();

                    //footerView를 직접 클릭하여 로드하기
                    footerView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            new NaverAsync().execute();
                        }
                    });
                }//if

                //검색할 내용이 없는 경우
                else{
                    Toast.makeText(getApplicationContext(), "더 불러올 내용이 없습니다", Toast.LENGTH_SHORT).show();
                    //footer 제거
                    //myListView.removeFooterView(footerView);
                    //LinearLayout linear = footerView.findViewById(R.id.f)
                    footerView.setVisibility(View.GONE);
                }//else
            }
        }
    };
}






































package com.sjh.a04_inflater;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

//리스트 뷰의 항목을 출력하기 위한 클래스
public class MyAdapter extends ArrayAdapter<String> {  //ArrayAdapter 클래스를 상속받는다.
    Context context;    //context를 파라미터로 받아 정보를 넘겨받는다. 이를 통해 inflater를 생성가능
    ArrayList<String> arr;
    int resource;
    ListView myList;

    //Alt+Enter로 슈퍼클래스 생성자 자동완성
    //resource는 항목 하나를 구현하기 위한 xml문서(list_form.xml)가 정수형태로 넘어감. -> inflater가 필요하다.
    public MyAdapter(Context context, int resource, ArrayList<String> arr, ListView myList) {
        super(context, resource, arr);  //arr을 파라미터로 넣는 이유 : list의 사이즈를 알아야 한다.

        this.context = context;
        this.arr = arr;
        this.resource = resource;
        this.myList = myList;

        this.myList.setOnItemClickListener(itemClick);  //리스트 클릭
        this.myList.setOnItemLongClickListener(longClick);  //리스트 롱클릭
    }

    //position은 arr의 size만큼 0~n-1로 바뀐다. getView는 n번 호출된다.
    @Override   //ArrayAdapter를 상속받는 클래스는 getView가 반드시 Override되어있어야 한다.
    public View getView(int position, View convertView, ViewGroup parent) {
        //inflater를 사용하여 resource를 실제 view형식으로 변환
        LayoutInflater linf = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = linf.inflate(resource, null);   //resource를 view로 객체화됨, view그룹은 없으므로 null
        //convertView는 resource(==list_form.xml)가 실체화(객체화)된 형태

        //convertView에서 아이디를 가진 객체를 선택
        TextView txt = convertView.findViewById(R.id.list_form_txt);
        Button btn = convertView.findViewById(R.id.btn);
        btn.setText("btn" + position);  //setText시 반드시 문자열 형태로 집어넣어야한다.

        //버튼에 감지자 등록
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), ((Button)v).getText().toString(), Toast.LENGTH_SHORT ).show();
            }
        });

        String s = arr.get(position);
        txt.setText(s);
        return convertView; //convertView를 ListView에 추가한다는 뜻
    }

    AdapterView.OnItemClickListener itemClick = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int i, long id) { // i == position

            Toast.makeText(getContext(), arr.get(i), Toast.LENGTH_SHORT).show();
            //Toast.makeText(context.getApplicationContext(), arr.get(i), Toast.LENGTH_SHORT).show();
            //Toast.makeText(context, arr.get(i), Toast.LENGTH_SHORT).show();
        }
    };

    AdapterView.OnItemLongClickListener longClick = new AdapterView.OnItemLongClickListener() {
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int i, long id) {
            Toast.makeText(getContext(), "long :"+ arr.get(i), Toast.LENGTH_SHORT).show();
            arr.remove(i);
            notifyDataSetChanged(); //getView갱신. 부모가 Adapter이므로 Adapter.notify로 안해도됨
            return true;
        }
    };
}

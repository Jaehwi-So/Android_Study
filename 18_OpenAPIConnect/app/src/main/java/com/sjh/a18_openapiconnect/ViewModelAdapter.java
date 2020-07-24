package com.sjh.a18_openapiconnect;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.net.URL;
import java.util.ArrayList;

public class ViewModelAdapter extends ArrayAdapter<BookVO> {
    Context context;
    int resource;
    ArrayList<BookVO> list;
    BookVO vo;

    public ViewModelAdapter(Context context, int resource, ArrayList<BookVO> list) {
        super(context, resource, list);
        this.context = context;
        this.resource = resource;
        this.list = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater linf = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = linf.inflate(resource, null);   //객체화
        vo = list.get(position); //position위치의 vo를 하나씩 가져옴
        TextView title = convertView.findViewById(R.id.book_title);
        TextView author = convertView.findViewById(R.id.book_author);
        TextView price = convertView.findViewById(R.id.book_price);
        ImageView img = convertView.findViewById(R.id.book_img);

        title.setText(vo.getB_title());
        author.setText(vo.getB_author());
        price.setText(vo.getB_price());

        //이미지 로드
        new ImgAsync(img, vo).execute();
        return convertView;
    }//getView()

    //URL을 통한 이미지 로드를 위한 백그라운드 스레드 클래스
    class ImgAsync extends AsyncTask<Void, Void, Bitmap>{   //비트 단위의 데이터를 모아 만든 이미지형태의 데이터
        Bitmap bm;
        ImageView mImg;
        BookVO vo;

        //Alt+Insert를 통해 생성자 생성
        public ImgAsync(ImageView mImg, BookVO vo) {
            this.mImg = mImg;
            this.vo = vo;
        }

        @Override
        protected Bitmap doInBackground(Void... voids) {
            try{
                URL img_url = new URL(vo.getB_img());
                BufferedInputStream bis = new BufferedInputStream(img_url.openStream());    //해당 url이 inputStream으로 읽는 것을 bufferedStream이 빠르게 읽어옴
                bm = BitmapFactory.decodeStream(bis);   //읽어온 스트림을 통해 비트맵객체 생성
                bis.close();
            } catch (Exception e){

            }
            return bm;  //postExecute의 파라미터로 넘어감
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            //가져온 비트맵을 ImageView에 탑재
            mImg.setImageBitmap(bitmap);
        }

    }
}

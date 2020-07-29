package com.sjh.a23_pinchtozoom_lib;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import uk.co.senab.photoview.PhotoViewAttacher;

/*라이브러리 등록. lib폴더에 .jar 넣기
*add as library를 통해 라이브러리 등록, build.gradle에서도 등록된 것을 확인
* */
public class MainActivity extends AppCompatActivity {
    ImageView photo;
    PhotoViewAttacher attacher;   //라이브러리의 클래스

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        photo = findViewById(R.id.photo);
        attacher = new PhotoViewAttacher(photo);    //생성자로 확대 축소하고 싶은 이미지
        attacher.update();  //이미지 뷰에 핀치투줌 적용완료. ctrl누른채로 드래그해서 확인
    }
}
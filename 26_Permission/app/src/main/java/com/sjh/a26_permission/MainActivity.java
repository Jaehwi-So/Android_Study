package com.sjh.a26_permission;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import com.gun0912.tedpermission.TedPermissionActivity;

import java.util.List;

//라이브러리를 이용하여 권한설정
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //전화걸기 권한에 대한 수락 여부 확인. 스스로 자가진단을 해 보았을 때 권한이 수락되어있지 않는 경우 GRANTED : 수락 / DENIED : 거부
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
            setPermission();
            return; //수락이 되어있지 않은 경우 return
        }

        //주소록 접근권한에 대한 수락여부 확인
        if(ActivityCompat.checkSelfPermission(this,Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_DENIED){
            setPermission();
            return; //수락이 되어있지 않은 경우 return
        }
    }//onCreate()

    private void setPermission(){
        TedPermission.with(this)
                .setPermissionListener(permissionListener)    //권한 수락 여부에 대한 감지자
                //여러개의 권한 중 수락되지 않은 권한이 있을 경우 출력되는 메시지
                .setDeniedMessage("이 앱에서 요구하는 권한이 있습니다\n [설정]->[권한]에서 활성화 해주세요")
                .setPermissions(Manifest.permission.CALL_PHONE, Manifest.permission.READ_CONTACTS)  //설정하고 싶은 권한들을 다중추가 가능하게함
                .check();

    }//setPermission()

    //앱 권한설정 감지자
    PermissionListener permissionListener = new PermissionListener() {
        @Override
        public void onPermissionGranted() {
            //모든 권한의 수락이 완료된 경우
            Intent intent = new Intent(MainActivity.this, MainActivity.class);
            startActivity(intent);
            finish();

        }

        @Override
        public void onPermissionDenied(List<String> deniedPermissions) {
            //한 가지라도 허용되지 않은 권한이 있는 경우 앱을 끈다.
            finish();
        }
    };

}
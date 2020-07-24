package com.sjh.a11_menupopupmenu;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }//onCreate()

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch ( item.getItemId() ){
            case R.id.add:
                Toast.makeText(getApplicationContext(), "추가합니다", Toast.LENGTH_SHORT).show();
                break;

            case R.id.edit:
                Toast.makeText(getApplicationContext(), "수정합니다", Toast.LENGTH_SHORT).show();
                break;

            case R.id.finish:
                finish(); //현재 액티비티 종료
                break;
        }//switch
        return true;
    }
}
























































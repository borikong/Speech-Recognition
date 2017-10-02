package com.google.cloud.android.speech.MainMenu;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.cloud.android.speech.Common.BaseActivity;
import com.google.cloud.android.speech.DailyTest.StartDailyTestActivity;
import com.google.cloud.android.speech.Information.InfoMenuActivity;
import com.google.cloud.android.speech.Study.StudyEnterActivity;
import com.google.cloud.android.speech.Record.MonthActivity;
import com.google.cloud.android.speech.UserData.MySQLiteHandler;
import com.google.cloud.android.speech.R;
import com.google.cloud.android.speech.UserData.UserDataActivity;

public class StartActivity extends BaseActivity {

    MySQLiteHandler handler;
    Cursor c;

    ImageButton btn1, btn2, btn3, btn4;
    ImageButton userdatabtn;
    TextView usertext; //[사용자이름]님 반갑습니다! 를 출력하기 위한 TextView

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        btn1=(ImageButton) findViewById(R.id.btn1); //데일리테스트
        btn2=(ImageButton) findViewById(R.id.btn2); //영역별 설명
        btn3=(ImageButton) findViewById(R.id.btn3); //기록장
        btn4=(ImageButton) findViewById(R.id.btn4); //스터디

        userdatabtn=(ImageButton)findViewById(R.id.userdatabtn);
        usertext=(TextView)findViewById(R.id.usertext);
        user_id=getUserId();
        usertext.setTextColor(Color.rgb(255,255,255));

        //디비 불러오는 부분
        handler=MySQLiteHandler.open(getApplicationContext());
        handler.insert(user_id,"","","");
        c=handler.selectById(user_id);
        startManagingCursor(c);
        if(c!=null&&c.moveToFirst()){
            usertext.setText(c.getString(c.getColumnIndex("name"))+"님 반갑습니다!");
        }

        btn1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this,StartDailyTestActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(StartActivity.this, InfoMenuActivity.class);
                startActivity(intent);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(StartActivity.this, MonthActivity.class);
                startActivity(intent);
            }
        });

        btn4.setOnClickListener(new View.OnClickListener(){ //스터디

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), StudyEnterActivity.class);
                //Toast.makeText(getApplication(), "스터디", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });

        userdatabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(StartActivity.this, UserDataActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.rightin_activity,R.anim.rightin_activity);
            }
        });
    }

    private String getUserId(){
        SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);

        return pref.getString("user_id", "");
    }
}

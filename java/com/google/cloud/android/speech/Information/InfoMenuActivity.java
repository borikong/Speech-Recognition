package com.google.cloud.android.speech.Information;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageButton;

import com.google.cloud.android.speech.Common.BaseActivity;
import com.google.cloud.android.speech.MainMenu.StartActivity;
import com.google.cloud.android.speech.R;
import com.google.cloud.android.speech.UserData.UserDataActivity;

/**
 * Created by 김다영 on 2017-08-24.
 */

public class InfoMenuActivity extends BaseActivity {

    ImageButton vocabularybtn,continuitybtn,pronunciationbtn,speedbtn;
    ImageButton userdatabtn,mainmenubtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informationmenu);
        vocabularybtn=(ImageButton) findViewById(R.id.vocabularybtn);
        continuitybtn=(ImageButton) findViewById(R.id.continuitybtn);
        pronunciationbtn=(ImageButton) findViewById(R.id.pronunciationbtn);
        speedbtn=(ImageButton) findViewById(R.id.speedbtn);

        mainmenubtn=(ImageButton)findViewById(R.id.menubtn);
        userdatabtn=(ImageButton)findViewById(R.id.userdatabtn);

        vocabularybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent=new Intent(InfoMenuActivity.this,VocaInfoActivity.class);
               startActivity(intent);
            }
        });

        continuitybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(InfoMenuActivity.this,ContinuityInfoActivity.class);
                startActivity(intent);
            }
        });

        pronunciationbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(InfoMenuActivity.this, PronunciationInfoActivity.class);
                startActivity(intent);
            }
        });

        speedbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(InfoMenuActivity.this,SpeedInfoActivity.class);
                startActivity(intent);
            }
        });


        userdatabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(InfoMenuActivity.this, UserDataActivity.class);
                startActivity(intent);
            }
        });

        mainmenubtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(InfoMenuActivity.this,StartActivity.class);
                startActivity(intent);
            }
        });
    }

    private String getUserId(){
        SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);

        return pref.getString("user_id", "");
    }
}

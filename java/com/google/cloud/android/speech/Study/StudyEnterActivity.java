package com.google.cloud.android.speech.Study;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.cloud.android.speech.R;

public class StudyEnterActivity extends AppCompatActivity {


    ImageButton btnV, btnC, btnP, btnS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_enter);

        btnV=(ImageButton) findViewById(R.id.vocabularybtn);
        btnC=(ImageButton) findViewById(R.id.continuitybtn);
        btnP=(ImageButton) findViewById(R.id.pronunciationbtn);
        btnS=(ImageButton) findViewById(R.id.speedbtn);

        btnV.setOnClickListener(new View.OnClickListener(){ //어휘력 영역 스터디 들어가기
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(getApplicationContext(), StudyVocabularyScoreActivity.class);
                startActivity(intent);
            }
        });

        btnC.setOnClickListener(new View.OnClickListener(){ //계속성 영역 스터디 들어가기

            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), StudyContinuityScoreActivity.class);
                startActivity(intent);
            }
        });

        btnP.setOnClickListener(new View.OnClickListener(){ //발음 영역 스터디 들어가기

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), StudyPronounciationScoreActivity.class);
                startActivity(intent);

            }
        });

        btnS.setOnClickListener(new View.OnClickListener(){ //속도 영역 스터디 들어가기
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(getApplication(), StudySpeedScoreActivity.class); //스터디-속도 부분 설명해주기
                startActivity(intent);
            }
        });

    }


}

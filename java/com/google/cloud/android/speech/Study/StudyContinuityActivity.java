package com.google.cloud.android.speech.Study;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.cloud.android.speech.DailyTest.DailyTestQuestionDB;
import com.google.cloud.android.speech.R;

import static com.google.cloud.android.speech.DailyTest.MainActivity.conQ;
import static com.google.cloud.android.speech.DailyTest.MainActivity.speedQ;

public class StudyContinuityActivity extends AppCompatActivity {
    TextView tv;
    int Score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_continuity);
        tv=(TextView)findViewById(R.id.tv);
        Intent intent=getIntent();
        Score=intent.getIntExtra("ContinuityScore",Score); //인트형받기

       searchRecord(""+Score);
      //  Toast.makeText(getApplicationContext(), "계속성", Toast.LENGTH_LONG).show();
    }

    public void searchRecord(String c_score){
    tv.setText("Q.문제");
        String[] where={c_score};
        DailyTestQuestionDB.db=openOrCreateDatabase(DailyTestQuestionDB.db_name, Activity.MODE_PRIVATE, null);
        Cursor c2= DailyTestQuestionDB.db.rawQuery("select question from "+ conQ +" where score<?", where);
        for(int i=0; i<c2.getCount(); i++){
            c2.moveToNext(); //다음번 레코드가 오게 되고
            String question = c2.getString(0); //그 레코드 안에서 0번쨰 해당하는애 =(name)
            tv.append(question);
        }
    }
}

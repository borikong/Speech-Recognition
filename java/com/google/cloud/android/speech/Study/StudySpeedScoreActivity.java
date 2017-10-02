package com.google.cloud.android.speech.Study;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.cloud.android.speech.R;

public class StudySpeedScoreActivity extends AppCompatActivity {

    TextView tv;
    EditText edt;
    Button okBtn;

    String score1;
    int score2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_speed_score);

        okBtn=(Button) findViewById(R.id.okButton);

        final String[] items = {"10","9","8","7","6","5","4","3","2","1"};
        final Spinner spinner = (Spinner)findViewById(R.id.spinner1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setSelection(5);

        okBtn.setOnClickListener(new View.OnClickListener(){ //확인버튼 누르고 스터디 속도 영역 가기

            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplication(), StudySpeedActivity.class);
                score1=spinner.getSelectedItem().toString();
                score2=Integer.parseInt(score1);

                intent.putExtra("SpeedScore",score2); //사용자 입력 점수넘겨주기
               // Toast.makeText(getApplicationContext(), edt.getText().toString()+"점수라능1", Toast.LENGTH_LONG).show();
                startActivity(intent);
            }
        });
    }

}

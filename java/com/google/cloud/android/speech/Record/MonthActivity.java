package com.google.cloud.android.speech.Record;

import android.support.v7.app.AppCompatActivity;

        import android.content.Intent;
        import android.os.Bundle;
import android.view.View;
        import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.cloud.android.speech.Common.BaseActivity;
import com.google.cloud.android.speech.DailyTest.ResultActivity;
import com.google.cloud.android.speech.R;

public class MonthActivity extends BaseActivity {
    public static final int REQUEST_CODE_MENU=101;
    ImageButton prevM, nextM,resultBtn;
    TextView currM;
    GridView gridView;
    MonthAdapter adapter;
    private int[] myScore = {0,0,0,0};
    private int daySelect = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_month);

        prevM = (ImageButton)findViewById(R.id.prevM);
        nextM = (ImageButton)findViewById(R.id.nextM);
        resultBtn = (ImageButton)findViewById(R.id.resultBtn);
        currM = (TextView)findViewById(R.id.currM);
        gridView = (GridView)findViewById(R.id.monthView);
        adapter = new MonthAdapter(this);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                adapter.setSelectedPosition(position);
                adapter.notifyDataSetChanged();
            }
        });



        int curYear = adapter.getCurYear();
        int curMonth = adapter.getCurMonth()+1;
        currM.setText(curYear+"년 " + curMonth + "월");

        prevM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.setPreviousMonth();
                adapter.notifyDataSetChanged();
                int curYear = adapter.getCurYear();
                int curMonth = adapter.getCurMonth()+1;
                currM.setText(curYear+"년 " + curMonth + "월");
            }
        });

        nextM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.setNextMonth();
                adapter.notifyDataSetChanged();
                int curYear = adapter.getCurYear();
                int curMonth = adapter.getCurMonth()+1;
                currM.setText(curYear+"년 " + curMonth + "월");
            }
        });

        resultBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                daySelect = adapter.getDaySelect();

                Intent intent = new Intent(getApplicationContext(),ResultActivity.class);
                intent.putExtra("DAY",daySelect);
                intent.putExtra("SCORE1",6);
                intent.putExtra("SCORE2",7);
                intent.putExtra("SCORE3",8);
                intent.putExtra("SCORE4",9);

                startActivityForResult(intent, REQUEST_CODE_MENU);
            }
        });

        //그래프에 들어갈 점 배열  - 평균값
        int[] points = {5, 3, 7, 8, 4, 3, 3, 6, 4, 1,
                5, 3, 8, 6, 7 ,5 ,9, 5, 7, 6,
                7, 6, 3, 1, 5, 6, 8, 6, 10, 5, 6};

        GraphView graphview = (GraphView) findViewById(R.id.GraphView);

        //단위는 1씩, 원점은 0, 총 10줄로 나누어진 그래프를 그린다
        graphview.setPoints(points, 1, 0, 10);
        graphview.drawForBeforeDrawView();
    }
}
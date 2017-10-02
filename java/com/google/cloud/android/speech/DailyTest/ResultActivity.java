package com.google.cloud.android.speech.DailyTest;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.cloud.android.speech.Common.BaseActivity;
import com.google.cloud.android.speech.Information.InfoMenuActivity;
import com.google.cloud.android.speech.UserData.MySQLiteHandler;
import com.google.cloud.android.speech.R;
import com.google.cloud.android.speech.MainMenu.StartActivity;

public class ResultActivity extends BaseActivity {
   // static SQLiteDatabase db;
    MySQLiteHandler handler; //handler 선언
    Cursor c; //커서 선언
    LinearLayout grapeLayout;
    Resources res;
    Animation anim;
    Button menuBtn,informationbtn;
    TextView vocabulary,continuity,pronunciation,speed,nameTv; //각각 점수를 출력하기 위한 TextView
    String username;
    java.util.Calendar cal = java.util.Calendar.getInstance();
    int year = cal.get ( cal.YEAR );
    int month = cal.get ( cal.MONTH ) + 1 ;
    int date = cal.get ( cal.DATE ) ;
    String nowDate = year+"-"+month+"-"+date;
    public int score1,score2,score3,score4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        res = getResources();
        anim = AnimationUtils.loadAnimation(this, R.anim.grow);
        grapeLayout = (LinearLayout)findViewById(R.id.grapeLayout);
        vocabulary=(TextView)findViewById(R.id.vocabulary);
        continuity=(TextView)findViewById(R.id.continuity);
        pronunciation=(TextView)findViewById(R.id.pronunciation);
        speed=(TextView)findViewById(R.id.speed);
        nameTv=(TextView)findViewById(R.id.name);//디비에 저장된 username을 출력하기 위한 TextView

        final Intent intent = getIntent();
        score1 = intent.getIntExtra("SCORE1",score1);
        score2 = intent.getIntExtra("SCORE2",score2);
        score3 = intent.getIntExtra("SCORE3",score3);
        score4 = intent.getIntExtra("SCORE4",score4);
        user_id=getUserId();

        //Toast.makeText(getApplication(),score1+score2+score3+score4,Toast.LENGTH_LONG).show();
        DailyTestQuestionDB.db=openOrCreateDatabase(DailyTestQuestionDB.db_name, Activity.MODE_PRIVATE, null);
        DailyTestQuestionDB.db.execSQL("insert into scoreTable (voca, pronunciation, continuity, speed, date) values ( " + score1 + ", " + score2 + ", " + score3  + ", " + score4 + ", '"+ nowDate +"' );");

        //디비에 저장된 Username불러오는 부분
        handler=MySQLiteHandler.open(getApplicationContext());
        c=handler.selectById(user_id);

        if(c!=null&&c.moveToFirst()){
            username=c.getString(c.getColumnIndex("name"));
            nameTv.setText(username+"");
        }

        //각각 점수를 해당 TextView에 출력
        vocabulary.setText(score1+"");
        continuity.setText(score2+"");
        pronunciation.setText(score3+"");
        speed.setText(score4+"");

        addItem("어휘력", score1);
        addItem("계속성", score2);
        addItem("발음", score3);
        addItem("속도", score4);

        //메뉴 버튼 누르면 StartActivity로 이동

        menuBtn=(Button)findViewById(R.id.menubtn);
        menuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(ResultActivity.this,StartActivity.class);
                startActivity(i);
            }
        });

        informationbtn=(Button)findViewById(R.id.informationbtn);
        informationbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(ResultActivity.this, StartActivity.class);
                startActivity(intent);
            }
        });
    }

    private void addItem(String name, int value) {

        LinearLayout itemLayout = new LinearLayout(this);
        itemLayout.setOrientation(LinearLayout.HORIZONTAL);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);

        LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);

        LinearLayout.LayoutParams params3 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);


        // 텍스트뷰 추가
        TextView textView = new TextView(this);
        textView.setText(name);
        params.width = 240;
        params.setMargins(0, 4, 0, 4);
        itemLayout.addView(textView, params);

        // 프로그레스바 추가
        ProgressBar proBar = new ProgressBar(this, null, android.R.attr.progressBarStyleHorizontal);
        proBar.setIndeterminate(false);
        proBar.setMax(100);
        proBar.setProgress(100);
        proBar.setAnimation(anim);
        params2.height = 100;
        params2.width = value * 50;
        params2.gravity = Gravity.LEFT;
        itemLayout.addView(proBar, params2);

        grapeLayout.addView(itemLayout, params3);

    }

    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        //Toast.makeText(this, "onWindowFocusChanged : " + hasFocus, Toast.LENGTH_SHORT).show();
        if (hasFocus) {
            anim.start();
        } else {
            anim.reset();
        }
    }
    private String getUserId(){
        SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);

        return pref.getString("user_id", "");
    }
}
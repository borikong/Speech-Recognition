/*
 * Copyright 2016 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.cloud.android.speech.DailyTest;

import android.Manifest;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.database.Cursor;
import android.os.Bundle;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;

import com.google.cloud.android.speech.Common.BaseActivity;
import com.google.cloud.android.speech.R;

import java.util.ArrayList;

import static com.google.cloud.android.speech.DailyTest.DailyTestQuestionDB.res_Record;


public class MainActivity extends BaseActivity implements MessageDialogFragment.Listener {
    public static final int REfQUEST_CODE_MENU=101;
    private static final String FRAGMENT_MESSAGE_DIALOG = "message_dialog";
    private static final String STATE_RESULTS = "results";
    private static final int REQUEST_RECORD_AUDIO_PERMISSION = 1;
    private SpeechService mSpeechService;
    private VoiceRecorder mVoiceRecorder;
    private int number=0;
    private int[] myScore = {0,0,0,0};
    int tableNum=1;
    public static final String vocaQ="vocaQ1", conQ="conQ1", pronounciationQ="proQ1", speedQ="speedQ1";
    String questionVocabulary;


    private final VoiceRecorder.Callback mVoiceCallback = new VoiceRecorder.Callback() {
        @Override
        public void onVoiceStart() {
            if (mSpeechService != null) {
                mSpeechService.startRecognizing(mVoiceRecorder.getSampleRate());
            }
        }

        @Override
        public void onVoice(byte[] data, int size) {
            if (mSpeechService != null) {
                mSpeechService.recognize(data, size);
            }
        }

        @Override
        public void onVoiceEnd() {
            if (mSpeechService != null) {
                mSpeechService.finishRecognizing();
            }
        }
    };

    // Resource caches
    private String temp = "";
    private String temp2 = "";
    private Button startBtn,nextBtn;
    private TextView textTv,qusTv,questionNum;
    private String testText="";
    private Chronometer timer;
    int qstnum; //문제번호

    private final ServiceConnection mServiceConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName componentName, IBinder binder) {
            mSpeechService = SpeechService.from(binder);
            mSpeechService.addListener(mSpeechServiceListener);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            mSpeechService = null;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createScoreTable("scoreTable");


    final Resources resources = getResources();
        final Resources.Theme theme = getTheme();

        startBtn = (Button) findViewById(R.id.startBtn);
        nextBtn = (Button) findViewById(R.id.nextBtn);
        textTv = (TextView) findViewById(R.id.textTv);
        qusTv = (TextView)findViewById(R.id.qusTv);
        questionNum=(TextView)findViewById(R.id.questionNum);
        qstnum=1;

        qusTv.setMovementMethod(new ScrollingMovementMethod());
        textTv.setMovementMethod(new ScrollingMovementMethod());

        timer = (Chronometer) findViewById(R.id.timer);
       // nextBtn.setEnabled(false); //처음에는 start버튼만 활성화 되고 타이머가 31 초가 되면 next버튼이 활성화 되는데 편의를 위해 일단 주석처리 해 놨음

        createDB(DailyTestQuestionDB.db_name);
        createTable(DailyTestQuestionDB.tb_name);
        createScoreTable("scoreTable");

        questionNum.setText("Q.문제"+qstnum);//EnterHangeolActivity에서MainActivity로 넘어오면 바로 문제1.이 뜨고 시작을 누르면 1번 문제가 나오도록

        qstnum++; //2번문제로 넘어갈 준비비

       final ArrayList<String> results = savedInstanceState == null ? null : savedInstanceState.getStringArrayList(STATE_RESULTS);

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startBtn.setEnabled(false); //start 버튼을 누르면 타이머가 31초가 될 때 까지 비활성화됨
                number++;
                DailyTestQuestionDB myDB= new DailyTestQuestionDB(); //start 버튼을 누르면 문제가 보임

                //myDB.searchRecord(""+number);
                myDB.searchRecord(""+tableNum);
                tableNum++;
                //Toast.makeText(getApplicationContext(), tableNum+"개", Toast.LENGTH_LONG).show();

                qusTv.setText(res_Record);

                stopVoiceRecorder();
                Toast.makeText(getApplicationContext(),"녹음이 시작됩니다. 말을 해주세요.",Toast.LENGTH_SHORT).show();
                timer.setBase(SystemClock.elapsedRealtime()); //0으로 초기화
                startVoiceRecorder();
                timer.start();
                testText = "";
            }
        });
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startBtn.setEnabled(true); //next버튼을 누르면 start버튼 활성화
                //nextBtn.setEnabled(false);
                textTv.setText("");//next버튼 누르면 녹음된 텍스트가 지워짐
                questionNum.setText("Q.문제"+qstnum);

                if(qstnum==2){
                    questionVocabulary="\n"+res_Record+"\n"; //어휘력 1번 문제
                }

                if(qstnum==3){ //어휘력 2번 문제
                  //  questionVocabulary.concat(res_Record);
                    DailyTestQuestionDB.db.execSQL("insert into " + vocaQ + "(turn, question, score) values ( " + tableNum + " , '" +  questionVocabulary.concat(res_Record) + "', " + myScore[0] + " );");
                }

                if(qstnum==4){ //ㄱ계속성
                    res_Record="\n"+res_Record;
                   DailyTestQuestionDB.db.execSQL("insert into " + conQ + "(turn, question, score) values ( " + tableNum + " , '" + res_Record + "', " + myScore[1] + " );"); //
                  //  Toast.makeText(getApplicationContext(), res_Record, Toast.LENGTH_LONG).show();
                }

                if(qstnum<=5){
                    qusTv.setText("다음문제입니다. 시작하려면 START 버튼을 눌러 주세요.");//5번문제까지는 시작전에 start버튼을 누르라고 알림
                }

                if(qstnum==5){//발음
                    res_Record="\n"+res_Record;
                    DailyTestQuestionDB.db.execSQL("insert into " + pronounciationQ + "(turn, question, score) values ( " + tableNum + " , '" + res_Record + "', " + myScore[2] + " );"); //발음->발음
                    nextBtn.setText("결과보기"); //5번문제로 넘어가면 next버튼이 결과보기 버튼으로 바뀜


                }
                if(qstnum>=6){ //6번이상의 문제가 되면 액티비티 전환
                    res_Record="\n"+res_Record;
                    DailyTestQuestionDB.db.execSQL("insert into " + speedQ + "(turn, question, score) values ( " + tableNum + " , '" + res_Record + "', " + myScore[3] + " );");
                    //속도문제넣기

                    Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                    intent.putExtra("SCORE1",myScore[0]);
                    intent.putExtra("SCORE2",myScore[1]);
                    intent.putExtra("SCORE3",myScore[2]);
                    intent.putExtra("SCORE4",myScore[3]);
                    startActivityForResult(intent,REfQUEST_CODE_MENU);
                    questionNum.setText("");
                }
                qstnum++; //문제번호++
            }
        });


        timer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                long time = SystemClock.elapsedRealtime()- chronometer.getBase();
                int realTime = Integer.parseInt(time/1000+"");
                if(realTime>=31) {
                    stopVoiceRecorder();
                    chronometer.stop();
                    nextBtn.setEnabled(true);//31초가 되면 next버튼이 활성화됨
                }
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(getApplicationContext(),"onStart",Toast.LENGTH_SHORT).show();

        // Prepare Cloud Speech API
        bindService(new Intent(this, SpeechService.class), mServiceConnection, BIND_AUTO_CREATE);

        // Start listening to voices
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED) {
        //    startVoiceRecorder();
        } else if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.RECORD_AUDIO)) {
            showPermissionMessageDialog();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, REQUEST_RECORD_AUDIO_PERMISSION);
        }
    }

    @Override
    protected void onStop() {
        // Stop listening to voice
        stopVoiceRecorder();
        // Stop Cloud Speech API
        mSpeechService.removeListener(mSpeechServiceListener);
        unbindService(mServiceConnection);
        mSpeechService = null;

        super.onStop();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
            @NonNull int[] grantResults) {
        if (requestCode == REQUEST_RECORD_AUDIO_PERMISSION) {
            if (permissions.length == 1 && grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            } else {
                showPermissionMessageDialog();
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    private void startVoiceRecorder() {
        if (mVoiceRecorder != null) {
            mVoiceRecorder.stop();
        }
        mVoiceRecorder = new VoiceRecorder(mVoiceCallback);
        mVoiceRecorder.start();
    }

    private void stopVoiceRecorder() {
        if (mVoiceRecorder != null) {
            mVoiceRecorder.stop();
            mVoiceRecorder = null;
            if(!temp.equals(temp2)) {
                testText = testText+" " + temp;
            }
            textTv.setText(testText);
            allScore(testText,number);

        }
    }


    private void showPermissionMessageDialog() {
        MessageDialogFragment
                .newInstance(getString(R.string.permission_message))
                .show(getSupportFragmentManager(), FRAGMENT_MESSAGE_DIALOG);
    }


    @Override
    public void onMessageDialogDismissed() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, REQUEST_RECORD_AUDIO_PERMISSION);
    }

    private final SpeechService.Listener mSpeechServiceListener =
            new SpeechService.Listener() {
                @Override
                public void onSpeechRecognized(final String text, final boolean isFinal) {
                    temp = text;
                    if (isFinal) {
                        mVoiceRecorder.dismiss();
                    }
                    if (!TextUtils.isEmpty(text)) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (isFinal) {
                                    temp2=text;
                                    testText = testText+" " + text;
                                }
                            }
                        });
                    }
                }
            };

    private void allScore(String testText, int number) {
        Vocabulary voca;
        Speed speed;
        Pronunciation pro;
        Continuity continuity;
        int score =0;

        switch (number) {
            //어휘력
            case 1:  voca=new Vocabulary(testText,1);   score = voca.getScore(); myScore[0]+=score;
                break;
            //어휘력
            case 2: voca= new Vocabulary(testText,2); score = voca.getScore(); myScore[0]+=score;
                break;
            //계속성
            case 3: continuity= new Continuity(testText); score = continuity.getScore(); myScore[1]+=score; break;
            //발음
            case 4: pro= new Pronunciation(testText); score = pro.getScore(); myScore[2]+=score; break;
            //속도
            case 5: speed= new Speed(testText); score = speed.getScore(); myScore[3]+=score; break;
        }
    }

    public void createDB(String db_name){
        try {
            DailyTestQuestionDB.db=openOrCreateDatabase(db_name, Activity.MODE_PRIVATE, null);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void createTable(String tb_name){
        DailyTestQuestionDB.db.execSQL("create table if not exists "+tb_name + "(_id integer PRIMARY KEY autoincrement," +
                "  turns integer, speechRecord text);");
    }

    public void createScoreTable(String tb_name){
        DailyTestQuestionDB.db=openOrCreateDatabase(DailyTestQuestionDB.db_name, Activity.MODE_PRIVATE, null);
        DailyTestQuestionDB.db.execSQL("create table if not exists "+tb_name + "(_id integer PRIMARY KEY autoincrement," +
                " voca integer, pronunciation integer, continuity integer, speed integer, date text);");

        Cursor c2 = DailyTestQuestionDB.db.rawQuery("select voca, pronunciation, continuity, speed, date from "+tb_name,null);
        tableNum=c2.getCount()*10+1; //1,11,21,31,41,51,61


        DailyTestQuestionDB.db.execSQL("create table if not exists "+ vocaQ + "(_id integer PRIMARY KEY autoincrement," +
                "  turn integer, question text, score integer);");

        DailyTestQuestionDB.db.execSQL("create table if not exists "+ conQ + "(_id integer PRIMARY KEY autoincrement," +
                "  turn integer, question text, score integer);");

        DailyTestQuestionDB.db.execSQL("create table if not exists "+ pronounciationQ + "(_id integer PRIMARY KEY autoincrement," +
                "  turn integer, question text, score integer);");

        DailyTestQuestionDB.db.execSQL("create table if not exists "+ speedQ + "(_id integer PRIMARY KEY autoincrement," +
                "  turn integer, question text, score integer);");

    }

}

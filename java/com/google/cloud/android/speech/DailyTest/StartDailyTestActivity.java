package com.google.cloud.android.speech.DailyTest;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.cloud.android.speech.Common.BaseActivity;
import com.google.cloud.android.speech.R;

/**
 * Created by 김다영 on 2017-07-20.
 */

public class StartDailyTestActivity extends BaseActivity {

    Button startbtn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startdailytest);

        startbtn=(Button)findViewById(R.id.start);

        startbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(StartDailyTestActivity.this,MainActivity.class);
                startActivity(i);
            }
        });
    }
}

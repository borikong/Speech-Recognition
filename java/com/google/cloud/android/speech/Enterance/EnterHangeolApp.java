package com.google.cloud.android.speech.Enterance;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.google.cloud.android.speech.Login.LoginActivity;
import com.google.cloud.android.speech.R;
import com.google.cloud.android.speech.MainMenu.StartActivity;

/**
 * Created by 김다영 on 2017-07-11.
 */

public class EnterHangeolApp extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter);

        Handler hd = new Handler();
        hd.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i=new Intent(EnterHangeolApp.this,LoginActivity.class);
                startActivity(i);
            }
        }, 2500);

    }
}

package com.google.cloud.android.speech.Common;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by 김다영 on 2017-08-24.
 */

public class BaseActivity extends AppCompatActivity{
    protected String user_id;
    protected String user_password;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}

package com.google.cloud.android.speech.Information;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.google.cloud.android.speech.Common.BaseActivity;
import com.google.cloud.android.speech.R;

/**
 * Created by 김다영 on 2017-08-30.
 */

public class VocaInfoActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vocabularyinfo);
    }
}

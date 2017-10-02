package com.google.cloud.android.speech.Record;

import android.support.v7.widget.AppCompatTextView;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;

/**
 * Created by DS on 2017-05-18.
 */

public class MonthItemView extends AppCompatTextView {

    private MonthItem item;
    public int result;

    //생성자
    public MonthItemView(Context context) {
        super(context);

        init();
    }

    public MonthItemView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    //초기화
    private void init() {
        setBackgroundColor(Color.WHITE);    //textview에서 백그라운드를 설정해주는 메소드.
    }

    public MonthItem getItem() {
        return item;
    }
    public int getResult(){
        return result;
    }

    public void setItem(MonthItem item) {
        this.item = item;

        int day = item.getDay();
        if (day != 0) {
            setText(String.valueOf(day));
        } else {
            setText("");
        }
    }
    public void setResult(int result){
        this.result = result;
    }
}

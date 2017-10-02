package com.google.cloud.android.speech.Record;

/**
 * Created by 김다영 on 2017-07-26.
 */
public class MonthItem {
    private int dayValue;
    public MonthItem() {

    }

    //생성자
    public MonthItem(int day) {
        dayValue = day;
    }

    public int getDay() {
        return dayValue;
    }

    public void setDay(int day) {
        this.dayValue = day;
    }
}
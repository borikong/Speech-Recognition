package com.google.cloud.android.speech.Record;
import android.content.Context;
import android.graphics.Color;
import android.text.format.Time;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;

import java.util.Calendar;

/**
 * Created by DS on 2017-05-18.
 */
public class MonthAdapter extends BaseAdapter  {

    public static final String TAG = "MonthAdapter";

    Context mContext;

    public static int oddColor = Color.rgb(225, 225, 225);
    public static int headColor = Color.rgb(12, 32, 158);

    public int daySelect = 0;
    private int selectedPosition = -1;  //아무것도 선택되지 않았다는 것 : -1

    public int result=5;

    private MonthItem[] items;

    private int countColumn = 7;

    int mStartDay;
    int startDay;
    int curYear;
    int curMonth;

    int firstDay;
    int lastDay;

    Calendar mCalendar;
    boolean recreateItems = false;

    public MonthAdapter(Context context) {
        super();

        mContext = context;

        init();
    }

    public MonthAdapter(Context context, AttributeSet attrs) {
        super();

        mContext = context;

        init();
    }

    private void init() {
        items = new MonthItem[7 * 6];   //최대 6주까지 표시해주고 있다.

        mCalendar = Calendar.getInstance(); //오늘에 해당하는 (현재 시간에 해당하는)시간을 가져옴.
        recalculate();  //현재 있는 월에 맞겠금 쓰는 것. (1일부터 마지막날까지)
        resetDayNumbers();
    }

    public void recalculate() {

        // set to the first day of the month
        mCalendar.set(Calendar.DAY_OF_MONTH, 1);    //그 달의 첫째 요일을 1로 설정한다.

        // get week day
        int dayOfWeek = mCalendar.get(Calendar.DAY_OF_WEEK);    //day_of_week : 오늘이 무슨 요일인지 값을 설정.
        firstDay = getFirstDay(dayOfWeek);
        Log.d(TAG, "firstDay : " + firstDay);

        mStartDay = mCalendar.getFirstDayOfWeek();
        //해당 년, 월, 일의 마지막 날 설정하는 것.
        curYear = mCalendar.get(Calendar.YEAR);
        curMonth = mCalendar.get(Calendar.MONTH);
        lastDay = getMonthLastDay(curYear, curMonth);   //2월의 윤년을 계산해 주는 것.

        Log.d(TAG, "curYear : " + curYear + ", curMonth : " + curMonth + ", lastDay : " + lastDay);

        int diff = mStartDay - Calendar.SUNDAY - 1;
        startDay = getFirstDayOfWeek();
        Log.d(TAG, "mStartDay : " + mStartDay + ", startDay : " + startDay);

    }

    //이전달 버튼을 누른 경우 사용하는 함수
    public void setPreviousMonth() {
        //캘린더의 날을 -1을 해주어 5월이면 4월을 설정해주는 것.
        mCalendar.add(Calendar.MONTH, -1);
        //설정 달에 대해 계속 해서 다시 계산하여 보여주는 것.
        recalculate();  //다시 계산
        resetDayNumbers();  //다시 설정해주는 것.
        selectedPosition = -1;
    }

    public void setNextMonth() {
        //달에 +1을 해주어 다시 설정해준다.
        mCalendar.add(Calendar.MONTH, 1);
        recalculate();
        resetDayNumbers();
        selectedPosition = -1;
    }

    public void resetDayNumbers() {
        //그리드에 해당하는 칸(7*2)
        for (int i = 0; i < 42; i++) {
            // calculate day number
            int dayNumber = (i+1) - firstDay;   //첫번째 날짜에 해당하는 값을 계속해서 빼면 마지막날에서 1을 빼면 -가 나오기 때문에 설정
            if (dayNumber < 1 || dayNumber > lastDay) { //음수와 마지막날보다 크거나 작을 때 0으로 설정해 주어 1일을 설정할 수 있도록 해준다.
                dayNumber = 0;
            }

            // save as a data item
            items[i] = new MonthItem(dayNumber);
        }
    }

    private int getFirstDay(int dayOfWeek) {
        //무슨 요일로 시작하는지 보여주는 함수.
        int result = 0;
        if (dayOfWeek == Calendar.SUNDAY) {
            result = 0;
        } else if (dayOfWeek == Calendar.MONDAY) {
            result = 1;
        } else if (dayOfWeek == Calendar.TUESDAY) {
            result = 2;
        } else if (dayOfWeek == Calendar.WEDNESDAY) {
            result = 3;
        } else if (dayOfWeek == Calendar.THURSDAY) {
            result = 4;
        } else if (dayOfWeek == Calendar.FRIDAY) {
            result = 5;
        } else if (dayOfWeek == Calendar.SATURDAY) {
            result = 6;
        }

        return result;
    }

    public int getCurYear() {
        return curYear;
    }

    public int getCurMonth() {
        return curMonth;
    }

    public int getNumColumns() {
        return 7;
    }

    public int getCount() {
        return 7 * 6;
    }

    public Object getItem(int position) {
        return items[position];
    }

    public long getItemId(int position) {
        return position;
    }

    //해당하는 view를 설정해주는 것.
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.d(TAG, "getView(" + position + ") called.");

        MonthItemView itemView;
        if (convertView == null) {
            itemView = new MonthItemView(mContext);
        } else {
            itemView = (MonthItemView) convertView;
        }

        // create a params
        GridView.LayoutParams params = new GridView.LayoutParams(
                GridView.LayoutParams.MATCH_PARENT,
                120);

        // calculate row and column
        int rowIndex = position / countColumn;
        int columnIndex = position % countColumn;

        Log.d(TAG, "Index : " + rowIndex + ", " + columnIndex);

        // set item data and properties
        //monthItem마다 view가 붙는다.
        itemView.setItem(items[position]);
        itemView.setResult(result);
        itemView.setLayoutParams(params);
        itemView.setPadding(2, 2, 2, 2);

        // set properties (왼쪽에 배치)
        itemView.setGravity(Gravity.LEFT);

        if (columnIndex == 0) { //일요일에 해당하는 경우
            itemView.setTextColor(Color.RED);
        }
        else if(columnIndex == 6){ //토요일에 해당하는 경우
            itemView.setTextColor(Color.BLUE);
        }
        else {    //그외에는 black
            itemView.setTextColor(Color.BLACK);
        }

        // set background color
        //사용자가 클릭, 선택 했을 때
        if (position == getSelectedPosition()) {    //selectedposition : 선택했다는 것을 알려주기 위해 배경을 yellow를 설정
            if(getDaySelect()>0&&getDaySelect()<32){ // 날짜이외의 블럭은 배경선택x
                itemView.setBackgroundColor(Color.YELLOW);
                itemView.setTextColor(Color.BLUE);
                itemView.setTextSize(24);
            }
        } else {
            itemView.setBackgroundColor(Color.WHITE);
            itemView.setTextSize(16);
        }
        return itemView;
    }

    public int getDaySelect(){
        daySelect = getSelectedPosition()-firstDay+1; //daySelect를 날짜로 변경
        return daySelect;
    }


    /**
     * Get first day of week as android.text.format.Time constant.
     * @return the first day of week in android.text.format.Time
     */
    public static int getFirstDayOfWeek() {
        int startDay = Calendar.getInstance().getFirstDayOfWeek();
        if (startDay == Calendar.SATURDAY) {
            return Time.SATURDAY;
        } else if (startDay == Calendar.MONDAY) {
            return Time.MONDAY;
        } else {
            return Time.SUNDAY;
        }
    }

    /**
     * get day count for each month
     *
     * @param year
     * @param month
     * @return
     */
    private int getMonthLastDay(int year, int month){
        switch (month) {
            case 0:
            case 2:
            case 4:
            case 6:
            case 7:
            case 9:
            case 11:
                return (31);

            case 3:
            case 5:
            case 8:
            case 10:
                return (30);

            default:
                if(((year%4==0)&&(year%100!=0)) || (year%400==0) ) {
                    return (29);   // 2월 윤년계산
                } else {
                    return (28);
                }
        }
    }
    /**
     * set selected row
     */
    public void setSelectedPosition(int selectedPosition) {
        this.selectedPosition = selectedPosition;
    }

    /**
     * get selected row
     *
     * @return
     */
    public int getSelectedPosition() {
        return selectedPosition;
    }
}

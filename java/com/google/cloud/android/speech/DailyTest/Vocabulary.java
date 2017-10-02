package com.google.cloud.android.speech.DailyTest;

import android.database.Cursor;

/**
 * Created by DS on 2017-07-05.
 */

public class Vocabulary {

    private String str = "";
    int num;
    private String[] rightAns;
    private int score=0;
    private String[] ra1  = null;
    private String[] ra2  = null;
    String[] day1_1 = {"지양","개발","한창","담합"};
    String[] day1_2 = {"하늘", "상승", "발생", "혼동", "책"};
    String[] day2_1 = {"가르치는", "가리는", "있다가", "맞추었다"};
    String[] day2_2 = {"홍보", "할인", "평가", "인정", "수상"};
    String[] day3_1 = {"연기", "다른", "곤혹", "경신"};
    String[] day3_2 = {"항소", "언론", "상해", "사태", "참석"};
    String[] day4_1 = {"결딴", "셀렘", "부치도록", "목이 멘다"};
    String[] day4_2 = {"비난", "통제", "유지", "파악", "대화"};
    String[] day5_1 = {"자못", "대인", "불리는", "철"};
    String[] day5_2 = {"표현", "감동", "의식", "의미", "눈동자"};
    String[] day6_1 = {"예스러운", "우려", "거꾸로", "조장"};
    String[] day6_2 = {"도전", "기록", "제품", "종목", "성적"};
    String[] day7_1 = {"깨치는", "불고하고", "맛 들였다", "쓸모없게"};
    String[] day7_2 = {"공감", "시대", "하루", "속도", "반복"};
    public Vocabulary(String str, int num) {
        this.str = str;
        this.num= num;
        Cursor c2 = DailyTestQuestionDB.db.rawQuery("select voca, pronunciation, continuity, speed, date from scoreTable",null);
        int tableNum=c2.getCount()+1;
        switch (tableNum) {
            case 1: ra1 = day1_1; ra2 = day1_2; break;
            case 2: ra1 = day2_1; ra2 = day2_2; break;
            case 3: ra1 = day3_1; ra2 = day3_2; break;
            case 4: ra1 = day4_1; ra2 = day4_2; break;
            case 5: ra1 = day5_1; ra2 = day5_2; break;
            case 6: ra1 = day6_1; ra2 = day6_2; break;
            case 7: ra1 = day7_1; ra2 = day7_2; break;
            default: break;
        }
    }

    public void vocaTest() {
        if(num==1)
            rightAns = ra1;
        else if(num==2)
            rightAns = ra2;

        for(int i = 0;i<rightAns.length;i++) {
            if ( str.indexOf( rightAns[i] ) > -1 ) {
                score++;
            }
        }
    }

    public int  getScore() {
        vocaTest();
        return score;
    }
}
package com.google.cloud.android.speech.DailyTest;

/**
 * Created by DS on 2017-07-05.
 */

public class Speed {

     int n;
     int num;
     int level;

    String record;

    public Speed(String record){
        this.record=record;
    }


   public int getScore() {
                /*1. 음절 갯수를 구한다*/

        String[] tmp = record.split(" "); //공백을 기준으로 단어를 나누는 메소드

        for (String s : tmp) {
            n++; //단어 수 만큼 n의 값 증가
            num = record.length() - (n - 1);  /*단어가 n개 일 때, 공백 부분은 n-1개*/
        }
                /*2. 구한 음절 갯수를 녹음 시간으로 나눈다*/

        int result = CalculateResult(num);
        n = 0;
        num = record.length(); //원래대로 초기화.

        return result;
    }


    public int CalculateResult(int nums){

    /*평가 그래프*/
        if(nums>=125 && nums<=135){ //1분당 260~270여자 기준
            level=10;
        }else if((nums>=120 && nums<125) || (nums>135 && nums<=140)){ //5개
            level=9;
        }else if((nums>=115 && nums<120) || (nums>140 && nums<145)){ //5개
            level=8;
        }else if((nums>=112 && nums<115) || (nums>145 && nums<148)){ //3개
            level=7;
        }else if((nums>=109 && nums<112) || (nums>148 && nums<151)){ //3개
            level=6;
        }else if((nums>=106 && nums<109) || (nums>151 && nums<153)){ //3개
            level=5;
        }else if((nums>=101 && nums<106) || (nums>153 && nums<158)){ //5개
            level=4;
        }else if((nums>=96 && nums<101) || (nums>158 && nums<163)){ //5개
            level=3;
        }else if((nums>=91 && nums<96) || (nums>163 && nums<168)){ //5개
            level=2;
        }else{
            level=1;
        }
        return level;
    }

}
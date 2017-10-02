package com.google.cloud.android.speech.DailyTest;

/**
 * Created by DS on 2017-07-05.
 */

public class Continuity {
    private String str;

    public Continuity(String str){
        this.str=str;
    }

    public int getScore(){
        int blank=0;
        int continuityScore=0;
        int wordnum=0;

        for(int i=0;i<str.length();i++){
            if(str.charAt(i)==' '){
                blank++;
            }
        }

        wordnum=blank+1;
        System.out.println("wordnum: "+wordnum);

        if(wordnum<=0||str.length()==0){
            continuityScore=0;
        }else if(wordnum<=3||wordnum>122){
            continuityScore=1;
        }else if(wordnum<=10||wordnum>=115){
            continuityScore=2;
        }else if(wordnum<=17||wordnum>=108){
            continuityScore=3;
        }else if(wordnum<=24||wordnum>=101){
            continuityScore=4;
        }else if(wordnum<=31||wordnum>=94){
            continuityScore=5;
        }else if(wordnum<=38||wordnum>=87){
            continuityScore=6;
        }else if(wordnum<=45||wordnum>=80){
            continuityScore=7;
        }else if(wordnum<=52||wordnum>=73){
            continuityScore=8;
        }else if(wordnum<=59||wordnum>=66){
            continuityScore=9;
        }else if(wordnum<=65&&wordnum>=60){
            continuityScore=10;
        }
        return continuityScore;
    }

}

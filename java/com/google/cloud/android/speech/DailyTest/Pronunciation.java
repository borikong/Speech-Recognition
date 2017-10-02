package com.google.cloud.android.speech.DailyTest;

/**
 * Created by DS on 2017-07-05.
 */

public class Pronunciation {
    public String question1="합성착향료";
    public String question2="국세청 연말정산";
    public String question3="내가 그린 기린 그림은 긴 기린 그림이고";
    public String question4="네가 그린 기린 그림은 안 긴 기린 그림이다";
    public String question5="간장공장 공장장은 강 공장장이고";
    public String question6="된장공장 공장장은 공 공장장이다";

    public int score =0;
    public String string;

    public Pronunciation(String string) {
        this.string = string;
    }

    public int getScore(){
        if(string.contains(question1)){
            score=score+1;
        }
        if(string.contains(question2)){
            score=score+1;
        }
        if(string.contains(question3)){
            score=score+2;
        }
        if(string.contains(question4)){
            score=score+2;
        }
        if(string.contains(question5)){
            score=score+2;
        }
        if(string.contains(question6)){
            score=score+2;
        }
        return score;
    }

}

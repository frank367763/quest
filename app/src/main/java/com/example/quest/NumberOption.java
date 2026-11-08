package com.example.quest;

public class NumberOption {
    public int mQuestion;
    private int mHint;
    public boolean mTrueQuestion1;
    public boolean mTrueQuestion2;
    public boolean mTrueQuestion3;
    public boolean mTrueQuestion4;
    public boolean mTrueQuestion5;
    public boolean mTrueQuestion6;

    public NumberOption(int question, int hint, boolean trueQuestion1, boolean trueQuestion2, boolean trueQuestion3, boolean trueQuestion4, boolean trueQuestion5, boolean trueQuestion6){
        mQuestion = question;
        mHint = hint;
        mTrueQuestion1 = trueQuestion1;
        mTrueQuestion2 = trueQuestion2;
        mTrueQuestion3 = trueQuestion3;
        mTrueQuestion4 = trueQuestion4;
        mTrueQuestion5 = trueQuestion5;
        mTrueQuestion6 = trueQuestion6;
    }

    public int getQuestion(){
        return mQuestion;
    }
    public int getHint(){ return mHint; };

    public boolean isTrueQuestion1(){
        return mTrueQuestion1;
    }

    public boolean isTrueQuestion2(){
        return mTrueQuestion2;
    }

    public boolean isTrueQuestion3(){
        return mTrueQuestion3;
    }

    public boolean isTrueQuestion4(){
        return mTrueQuestion4;
    }

    public boolean isTrueQuestion5(){
        return mTrueQuestion5;
    }

    public boolean isTrueQuestion6(){
        return mTrueQuestion6;
    }
}

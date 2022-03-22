package com.example.judgmentdoc.vo;

import java.util.List;

public class CheckResultVO {
    private int accuracy;
    private int factLess;
    private int lawLess;
    private int lawError;
    List<Text> texts;

    public CheckResultVO() {
    }

    public int getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(int accuracy) {
        this.accuracy = accuracy;
    }

    public int getFactLess() {
        return factLess;
    }

    public void setFactLess(int factLess) {
        this.factLess = factLess;
    }

    public int getLawLess() {
        return lawLess;
    }

    public void setLawLess(int lawLess) {
        this.lawLess = lawLess;
    }

    public int getLawError() {
        return lawError;
    }

    public void setLawError(int lawError) {
        this.lawError = lawError;
    }

    public List<Text> getTexts() {
        return texts;
    }

    public void setTexts(List<Text> texts) {
        this.texts = texts;
    }
}

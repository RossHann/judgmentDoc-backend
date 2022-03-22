package com.example.judgmentdoc.vo;

public class Fact extends Text {
    private int count;

    public Fact() {
        this.setType(1);
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}

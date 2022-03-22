package com.example.judgmentdoc.vo;

public class Conclusion extends Text {
    private int count;

    public Conclusion() {
        this.setType(3);
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}

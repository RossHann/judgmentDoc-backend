package com.example.judgmentdoc.vo;

import com.example.judgmentdoc.po.Article;

import java.util.ArrayList;
import java.util.List;

public class Fact extends Text {
    private int count;
    private List<Article> needs;

    public Fact() {
        this.setType(1);
        this.needs = new ArrayList<>();
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Article> getNeeds() {
        return needs;
    }

    public void setNeeds(List<Article> needs) {
        this.needs = needs;
    }

    public void addNeed(Article article) {
        this.needs.add(article);
    }
}

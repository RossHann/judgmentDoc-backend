package com.example.judgmentdoc.vo;

import com.example.judgmentdoc.po.Article;

public class Law extends Text {

    private Article article;

    public Law() {
        this.setType(2);
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }
}

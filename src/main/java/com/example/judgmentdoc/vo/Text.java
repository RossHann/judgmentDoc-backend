package com.example.judgmentdoc.vo;

import java.util.ArrayList;
import java.util.List;

public class Text {
    private String id;
    private String content;
    private int type;
    private List<String> relations;

    public Text() {
        this.setType(0);
        this.relations = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<String> getRelations() {
        return relations;
    }

    public void setRelations(List<String> relations) {
        this.relations = relations;
    }

    public void addRelation(String r) {
        this.relations.add(r);
    }
}

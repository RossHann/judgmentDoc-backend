package com.example.judgmentdoc.vo;

import java.util.List;

public class DocInfoVO {
    private String content;
    private String courtName;
    private String name;
    private String number;
    private String date;
    private List<MemberVO> members;

    public DocInfoVO() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCourtName() {
        return courtName;
    }

    public void setCourtName(String courtName) {
        this.courtName = courtName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<MemberVO> getMembers() {
        return members;
    }

    public void setMembers(List<MemberVO> members) {
        this.members = members;
    }
}

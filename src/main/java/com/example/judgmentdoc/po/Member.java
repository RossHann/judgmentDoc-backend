package com.example.judgmentdoc.po;

public class Member implements Comparable<Member> {
    private String status;
    private String name;

    public Member() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private final static String STATUS_TBL = "审判长审判员代理审判员人民陪审员书记员";

    @Override
    public int compareTo(Member m) {
        if (STATUS_TBL.indexOf(this.status) == STATUS_TBL.indexOf(m.getStatus())) {
            return m.getName().compareTo(this.name);
        } else {
            return STATUS_TBL.indexOf(this.status) < STATUS_TBL.indexOf(m.getStatus()) ? -1 : 1;
        }
    }

    @Override
    public String toString() {
        return "MemberVO{" +
                "status='" + status + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
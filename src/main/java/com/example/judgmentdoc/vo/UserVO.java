package com.example.judgmentdoc.vo;

import com.example.judgmentdoc.po.User;

public class UserVO {
    private Long userId;
    private String username;
    private int role;

    public UserVO() {
    }

    public UserVO(User user) {
        this.userId = user.getId();
        this.username = user.getUsername();
        this.role = user.getRole();
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
}

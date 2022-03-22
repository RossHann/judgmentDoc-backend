package com.example.judgmentdoc.bl.user;

import com.example.judgmentdoc.po.User;
import com.example.judgmentdoc.vo.ResponseVO;

public interface UserService {
    ResponseVO login(String username, String password);

    ResponseVO register(User user);

    ResponseVO getUserInfoById(Long userId);
}

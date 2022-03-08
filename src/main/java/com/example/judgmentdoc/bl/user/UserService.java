package com.example.judgmentdoc.bl.user;

import com.example.judgmentdoc.po.User;
import com.example.judgmentdoc.vo.ResponseVO;
import com.example.judgmentdoc.vo.UserVO;

public interface UserService {
    ResponseVO login(UserVO userVO);
}

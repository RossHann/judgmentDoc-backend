package com.example.judgmentdoc.blImpl.user;

import com.example.judgmentdoc.bl.user.UserService;
import com.example.judgmentdoc.data.user.UserMapper;
import com.example.judgmentdoc.po.User;
import com.example.judgmentdoc.vo.ResponseVO;
import com.example.judgmentdoc.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private static final String USER_NOT_EXIST = "用户不存在";
    private static final String USERNAME_EXIST = "用户名已存在";
    private static final String WRONG_PWD = "密码错误";

    @Autowired
    UserMapper userMapper;

    @Override
    public ResponseVO login(String username, String password) {
        User user = userMapper.getUserByUsername(username);
        if (user != null) {
            if (user.getPassword().equals(password)) {
                return ResponseVO.buildSuccess(new UserVO(user));
            }
            return ResponseVO.buildFailure(WRONG_PWD);
        }
        return ResponseVO.buildFailure(USER_NOT_EXIST);
    }

    @Override
    public ResponseVO register(User user) {
        try {
            return ResponseVO.buildSuccess(userMapper.insertUser(user));
        } catch (DuplicateKeyException e) {
            System.out.println(e);
            return ResponseVO.buildFailure(USERNAME_EXIST);
        }
    }
}

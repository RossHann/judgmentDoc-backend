package com.example.judgmentdoc.blImpl.user;

import com.example.judgmentdoc.bl.user.UserService;
import com.example.judgmentdoc.data.user.UserMapper;
import com.example.judgmentdoc.po.User;
import com.example.judgmentdoc.vo.ResponseVO;
import com.example.judgmentdoc.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private static final String USER_NOT_EXIST = "用户不存在";
    private static final String WRONG_PWD = "密码错误";

    @Autowired
    UserMapper userMapper;

    @Override
    public ResponseVO login(UserVO userVO) {
        User user = userMapper.getUserByUsername(userVO.getUsername());
        if (user != null) {
            if (user.getPassword().equals(userVO.getPassword())) {
                return ResponseVO.buildSuccess(user);
            }
            return ResponseVO.buildFailure(WRONG_PWD);
        }
        return ResponseVO.buildFailure(USER_NOT_EXIST);
    }
}

package com.example.judgmentdoc.blImpl.user;

import com.example.judgmentdoc.bl.user.UserService;
import com.example.judgmentdoc.data.user.UserMapper;
import com.example.judgmentdoc.po.User;
import com.example.judgmentdoc.util.aliOss.AliOssUtil;
import com.example.judgmentdoc.vo.ResponseVO;
import com.example.judgmentdoc.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private static final String USER_NOT_EXIST = "用户不存在";
    private static final String USERNAME_EXIST = "用户名已存在";
    private static final String WRONG_PWD = "密码错误";
    private static final String OSS_PREFIX_AVATAR = "http://pyyybf.oss-cn-shanghai.aliyuncs.com/judgmentDoc/avatars/";

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
            e.printStackTrace();
            return ResponseVO.buildFailure(USERNAME_EXIST);
        }
    }

    @Override
    public ResponseVO getUserInfoById(Long userId) {
        return ResponseVO.buildSuccess(userMapper.getUserInfoById(userId));
    }

    @Override
    public ResponseVO updateAvatarById(Long userId, MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseVO.buildFailure("文件为空");
        }
        String originalFileName = file.getOriginalFilename();
        String suffix = originalFileName.substring(originalFileName.lastIndexOf("."));
        String fileName = UUID.randomUUID().toString().replace("-", "") + suffix;
        AliOssUtil.upload(file, "avatars/" + fileName);
        String avatar = OSS_PREFIX_AVATAR + fileName;
        return ResponseVO.buildSuccess(userMapper.updateAvatarById(userId, avatar));
    }

    @Override
    public ResponseVO updateUserInfoById(Long userId, User user) {
        user.setId(userId);
        return ResponseVO.buildSuccess(userMapper.updateUserInfoById(user));
    }

    @Override
    public ResponseVO updatePasswordById(Long userId, String password) {
        return ResponseVO.buildSuccess(userMapper.updatePasswordById(userId, password));
    }
}

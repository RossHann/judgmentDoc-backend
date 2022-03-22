package com.example.judgmentdoc.bl.user;

import com.example.judgmentdoc.po.User;
import com.example.judgmentdoc.vo.ResponseVO;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {
    ResponseVO login(String username, String password);

    ResponseVO register(User user);

    ResponseVO getUserInfoById(Long userId);

    ResponseVO updateAvatarById(Long userId, MultipartFile file);

    ResponseVO updateUserInfoById(Long userId, User user);

    ResponseVO updatePasswordById(Long userId, String password);
}

package com.example.judgmentdoc.controller.user;

import com.example.judgmentdoc.bl.user.UserService;
import com.example.judgmentdoc.po.User;
import com.example.judgmentdoc.vo.ResponseVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController()
@RequestMapping("/api/user")
@Api(tags = "UserController", description = "用户信息管理")
public class UserController {

    private static final String LOGIN_ERROR = "登录出错";
    private static final String REGISTER_ERROR = "注册失败";
    private static final String INFO_FETCH_ERROR = "个人信息获取失败";
    private static final String UPDATE_ERROR = "更改失败";

    @Autowired
    UserService userService;

    @ApiOperation(value = "登录")
    @GetMapping("/login")
    public ResponseVO login(@RequestParam String username,
                            @RequestParam String password) {
        try {
            return userService.login(username, password);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure(LOGIN_ERROR);
        }
    }

    @ApiOperation(value = "注册")
    @PostMapping("/register")
    public ResponseVO register(@RequestBody User user) {
        try {
            return userService.register(user);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure(REGISTER_ERROR);
        }
    }

    @ApiOperation(value = "获取用户个人信息")
    @GetMapping("/getUserInfoById/{userId}")
    public ResponseVO getUserInfoById(@PathVariable Long userId) {
        try {
            return userService.getUserInfoById(userId);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure(INFO_FETCH_ERROR);
        }
    }

    @ApiOperation(value = "更改用户头像")
    @PutMapping("/updateAvatarById")
    public ResponseVO updateAvatarById(@RequestParam(value = "userId") Long userId,
                                       @RequestParam(value = "file") MultipartFile file) {
        try {
            return userService.updateAvatarById(userId, file);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure(UPDATE_ERROR);
        }
    }

    @ApiOperation(value = "更改用户个人信息")
    @PutMapping("/updateUserInfoById/{userId}")
    public ResponseVO updateUserInfoById(@PathVariable(value = "userId") Long userId, @RequestBody User user) {
        try {
            return userService.updateUserInfoById(userId, user);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure(UPDATE_ERROR);
        }
    }

    @ApiOperation(value = "更改用户密码")
    @PutMapping("/updatePasswordById")
    public ResponseVO updatePasswordById(@RequestParam(value = "userId") Long userId,
                                         @RequestParam(value = "password") String password) {
        try {
            return userService.updatePasswordById(userId, password);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure(UPDATE_ERROR);
        }
    }
}

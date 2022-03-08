package com.example.judgmentdoc.controller.user;

import com.example.judgmentdoc.bl.user.UserService;
import com.example.judgmentdoc.vo.ResponseVO;
import com.example.judgmentdoc.vo.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/api/user")
@Api(tags = "UserController", description = "用户信息管理")
public class UserController {

    private static final String LOGIN_ERROR = "登录出错";

    @Autowired
    UserService userService;

    @ApiOperation(value = "登录")
    @GetMapping("/login")
    public ResponseVO login(@RequestParam String username,
                            @RequestParam String password) {
        try {
            return userService.login(new UserVO(username, password));
        } catch (Exception e) {
            System.out.println(e);
            return ResponseVO.buildFailure(LOGIN_ERROR);
        }
    }
}

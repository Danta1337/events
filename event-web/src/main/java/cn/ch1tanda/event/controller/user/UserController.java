package cn.ch1tanda.event.controller.user;

import cn.ch1tanda.event.convention.response.DefaultResult;
import cn.ch1tanda.event.convention.response.Result;
import cn.ch1tanda.event.manager.framework.RedisManager;
import cn.ch1tanda.event.manager.user.UserManager;
import cn.ch1tanda.event.manager.user.req.RegisterReq;
import cn.ch1tanda.event.manager.user.resp.RegisterResp;
import cn.ch1tanda.event.model.User;
import cn.ch1tanda.event.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.Random;

@Controller
public class UserController {

    UserService userService;
    UserManager userManager;
    PasswordEncoder passwordEncoder;

    @Autowired
    void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }

    @Autowired
    void setEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/register/sendVerifyCode")
    @ResponseBody
    public void sendVerifyCode(String email) {
        userService.sendEmailVerificationCode(email);
    }

    @PostMapping("/register")
    @ResponseBody
    public Result<Void> register(String email, String username, String password, String verifyCode) {
        if (!userService.verifyEmailVerificationCode(email, verifyCode)) {
            return new DefaultResult<>("1", "verify code is not correct");
        }

        String encodedPassword = passwordEncoder.encode(password);
        RegisterReq registerReq = new RegisterReq(
                User.builder()
                        .email(email)
                        .username(username)
                        .password(encodedPassword)
                        .build());

        return userManager.register(registerReq);
    };
}

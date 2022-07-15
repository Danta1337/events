package cn.ch1tanda.event.controller.user;

import cn.ch1tanda.event.convention.response.DefaultResult;
import cn.ch1tanda.event.convention.response.Result;
import cn.ch1tanda.event.convention.response.Results;
import cn.ch1tanda.event.manager.user.UserManager;
import cn.ch1tanda.event.manager.user.req.RegisterReq;
import cn.ch1tanda.event.manager.user.resp.RegisterResp;
import cn.ch1tanda.event.model.User;
import cn.ch1tanda.event.service.user.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

@Controller
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private UserManager userManager;

    @Resource
    private PasswordEncoder passwordEncoder;

    @GetMapping("/register/sendVerifyCode")
    @ResponseBody
    public Result<Boolean> sendVerifyCode(String email) {
        userService.sendEmailVerificationCode(email);
        return Results.success(true);
    }

    @PostMapping("/register")
    @ResponseBody
    public Result<Boolean> register(String email, String username, String password, String verifyCode) {
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
        RegisterResp registerResult = userManager.register(registerReq);
        return registerResult.isSuccess() ? Results.success(true) : Results.success(false);
    };
}

package cn.ch1tanda.event.controller.user;

import cn.ch1tanda.event.convention.response.Result;
import cn.ch1tanda.event.convention.response.Results;
import cn.ch1tanda.event.model.User;
import cn.ch1tanda.event.service.user.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;

@Controller
@Validated
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private PasswordEncoder passwordEncoder;

    @GetMapping("/register/sendVerifyCode")
    @ResponseBody
    public Result<Boolean> sendVerifyCode(
            @NotBlank String email) {
        userService.sendEmailVerificationCode(email);
        return Results.success(true);
    }

    @PostMapping("/register/submit")
    @ResponseBody
    public Result<Boolean> register(
            @NotBlank String email,
            @NotBlank String username,
            @NotBlank String password,
            @NotBlank String verifyCode) {
        if (!userService.verifyEmailVerificationCode(email, verifyCode)) {
            return Results.failure("1", "verify code is not correct");
        }

        String encodedPassword = passwordEncoder.encode(password);
        User user = new User();
        user.setEmail(email);
        user.setUsername(username);
        user.setPassword(encodedPassword);

        boolean registerResult = userService.register(user);

        return registerResult ? Results.success(true) : Results.failure("C_1", "注册失败！");
    }

    @RequestMapping("/login")
    public String toLogin() {
        return "user/login";
    }

    @RequestMapping("/register")
    public String toRegister() {
        return "user/register";
    }
}

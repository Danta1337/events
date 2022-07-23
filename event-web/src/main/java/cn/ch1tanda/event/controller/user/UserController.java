package cn.ch1tanda.event.controller.user;

import cn.ch1tanda.event.convention.response.Result;
import cn.ch1tanda.event.convention.response.Results;
import cn.ch1tanda.event.manager.user.UserManager;
import cn.ch1tanda.event.manager.user.req.RegisterReq;
import cn.ch1tanda.event.manager.user.resp.RegisterResp;
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
    private UserManager userManager;

    @Resource
    private PasswordEncoder passwordEncoder;

    @GetMapping("/register/sendVerifyCode")
    @ResponseBody
    public Result<Boolean> sendVerifyCode(
            @NotBlank String email) {
        userService.sendEmailVerificationCode(email);
        return Results.success(true);
    }

    @PostMapping("/register")
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

        RegisterReq registerReq = new RegisterReq(user);
        RegisterResp registerResult = userManager.register(registerReq);

        return registerResult.isSuccess() ? Results.success(true) : Results.failure("1", registerResult.getMessage());
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

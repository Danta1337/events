package cn.ch1tanda.event.controller.user;

import cn.ch1tanda.event.manager.framework.RedisManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.Random;

@Controller
public class UserController {
    Random random = new Random(new Date().getTime());

    RedisManager redisManager;

    @Autowired
    void setRedisManager(RedisManager redisManager) {
        this.redisManager = redisManager;
    }

    @GetMapping("/register/sendVerifyCode")
    @ResponseBody
    public void sendVerifyCode(String email) {
        StringBuilder verifyCodeBuilder = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            verifyCodeBuilder.append(random.nextInt(10));
        }

        redisManager.set(email, verifyCodeBuilder.toString(), 1000 * 60 * 5L);
    }
}

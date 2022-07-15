package cn.ch1tanda.event.manager.user.req;


import cn.ch1tanda.event.model.User;
import lombok.Getter;

public class AuthReq {
    @Getter
    private final String username;

    public AuthReq(String username) {
        this.username = username;
    }
}

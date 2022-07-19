package cn.ch1tanda.event.manager.user.req;


import lombok.Getter;

public class AuthReq {
    @Getter
    private final String username;

    public AuthReq(String username) {
        this.username = username;
    }
}

package cn.ch1tanda.event.manager.user.req;

import cn.ch1tanda.event.model.User;
import lombok.Getter;

public class RegisterReq {
    @Getter
    private final User user;

    public RegisterReq(User user) {
        this.user = user;
    }
}

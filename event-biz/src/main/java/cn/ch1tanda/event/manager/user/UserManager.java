package cn.ch1tanda.event.manager.user;

import cn.ch1tanda.event.model.User;

import java.util.List;

public interface UserManager {

    User auth(String username);

    boolean register(User req);

    List<String> getAuthorities(String username);
}

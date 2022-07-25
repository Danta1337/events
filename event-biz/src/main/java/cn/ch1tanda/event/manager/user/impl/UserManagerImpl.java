package cn.ch1tanda.event.manager.user.impl;

import cn.ch1tanda.event.exception.ServiceInvalidException;
import cn.ch1tanda.event.manager.user.UserManager;
import cn.ch1tanda.event.mapper.AuthorityMapper;
import cn.ch1tanda.event.mapper.UserMapper;
import cn.ch1tanda.event.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

@Slf4j
@Component
public class UserManagerImpl implements UserManager {

    @Resource
    private UserMapper userMapper;

    @Resource
    private AuthorityMapper authorityMapper;

    @Override
    @Transactional
    public User auth(String username) {
        User res = userMapper.selectUsernameAndPasswordByUsername(username);
        return Objects.isNull(res) ? null : res;
    }

    @Override
    @Transactional
    public boolean register(User user) {
        User res = userMapper.selectUsernameAndPasswordByUsername(user.getUsername());
        if (Objects.nonNull(res)) {
            throw new ServiceInvalidException("Username already exists");
        }

        Date now = new Date();

        user.setGmtCreated(now);
        user.setGmtModified(now);
        user.setEnabled(false);
        user.setRemark("");

        try {
            int insert = userMapper.insert(user);
            return insert == 1;
        } catch (Exception e) {
            log.info("An exception occurred when registering", e);
            return false;
        }
    }

    @Override
    @Transactional
    public List<String> getAuthorities(String username) {
        List<String> authorities = authorityMapper.selectAuthorityByUsername(username);
        return Objects.isNull(authorities) ? new ArrayList<>() : authorities;
    }
}

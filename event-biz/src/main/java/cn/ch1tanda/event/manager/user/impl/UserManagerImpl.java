package cn.ch1tanda.event.manager.user.impl;

import cn.ch1tanda.event.manager.user.UserManager;
import cn.ch1tanda.event.manager.user.req.AuthReq;
import cn.ch1tanda.event.manager.user.req.GetAuthoritiesReq;
import cn.ch1tanda.event.manager.user.req.RegisterReq;
import cn.ch1tanda.event.manager.user.resp.AuthResp;
import cn.ch1tanda.event.manager.user.resp.GetAuthoritiesResp;
import cn.ch1tanda.event.manager.user.resp.RegisterResp;
import cn.ch1tanda.event.mapper.AuthorityMapper;
import cn.ch1tanda.event.mapper.UserMapper;
import cn.ch1tanda.event.model.User;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
public class UserManagerImpl implements UserManager {

    @Resource
    private UserMapper userMapper;

    @Resource
    private AuthorityMapper authorityMapper;

    @Override
    @Transactional
    public AuthResp auth(AuthReq req) {
        Optional<User> res = userMapper.selectUsernameAndPasswordByUsername(req.getUsername());

        if (!res.isPresent()) {
            return new AuthResp("1", "user does not exist", null);
        }

        return new AuthResp("0", "success", res.get());
    }

    @Override
    @Transactional
    public RegisterResp register(RegisterReq req) {
        Optional<User> res = userMapper.selectUsernameAndPasswordByUsername(req.getUser().getUsername());
        if (res.isPresent()) {
            return new RegisterResp("1", "username already exists");
        }

        Date now = new Date();
        User user = req.getUser();

        user.setGmtCreated(now);
        user.setGmtModified(now);
        user.setEnabled(false);
        user.setRemark("");

        try {
            userMapper.insert(user);
        } catch (RuntimeException e) {
            return new RegisterResp("1", "database exception");
        }

        return new RegisterResp("0", "success");
    }

    @Override
    @Transactional
    public GetAuthoritiesResp getAuthorities(GetAuthoritiesReq req) {
        List<String> authorities = authorityMapper.selectAuthorityByUsername(req.getUsername());
        return new GetAuthoritiesResp("0", "success", authorities);
    }
}

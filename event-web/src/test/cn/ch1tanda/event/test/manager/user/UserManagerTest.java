package cn.ch1tanda.event.test.manager.user;


import cn.ch1tanda.event.manager.user.UserManager;
import cn.ch1tanda.event.mapper.AuthorityMapper;
import cn.ch1tanda.event.mapper.UserMapper;
import cn.ch1tanda.event.test.BaseTest;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class UserManagerTest extends BaseTest {
    UserManager userManager;
    @MockBean
    UserMapper userMapper;

    @Autowired
    void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }

    @MockBean
    AuthorityMapper authorityMapper;


    @Test
    void authTest() {
        Date date1 = new Date();
        date1.setTime(1000);

        Date date2 = new Date();
        date2.setTime(1000);

        assertEquals(date1, date2);

    }

}
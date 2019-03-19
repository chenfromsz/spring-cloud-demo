package com.demo.data.test;


import com.demo.common.UserQo;
import com.demo.data.DataApplication;
import com.demo.data.domain.User;
import com.demo.data.service.UserService;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import java.text.SimpleDateFormat;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {DataApplication.class})
@SpringBootTest
@Slf4j
public class UserTest {

    @Autowired
    private UserService userService;

    @Test
    public void insertData(){

        User user = new User();
        user.setOpenId("abcd123456");
        user.setNickName("昵称");

        userService.insert(user);

        Assert.notNull(user.getId(), "create user error");

    }

    //@Test
    public void getById(){
        User user = userService.getById("767a50682054469eba50682054f69ebf");
        Assert.notNull(user, "not find");
        log.info("=====user:{}", new Gson().toJson(user));
    }

    //@Test
    public void delById(){
        String ret = userService.delete("767a50682054469eba50682054f69ebf");
        Assert.isTrue(ret.equals("1"), ret);
    }


    //@Test
    public void findUserPage() throws Exception{
        UserQo userQo = new UserQo();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        userQo.setCreated(sdf.parse("2019-01-01 00:00:00"));

        PageInfo<User> page = userService.getPage(userQo);

        log.info("====page:{}", new Gson().toJson(page));
    }



}

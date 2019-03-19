package com.demo.data.controller;

import com.demo.common.UserQo;
import com.demo.data.domain.User;
import com.demo.data.service.UserService;
import com.demo.data.util.BaseIDUtils;
import com.demo.data.util.CopyUtil;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserApiController {

    @Autowired
    private UserService userService;


    @GetMapping("/get")
    public User findById(@RequestParam String id) throws Exception{
        return userService.getById(id);
    }


    @PostMapping("/page")
    public String findPage(@RequestBody UserQo userQo) throws Exception{
        PageInfo<User> pageInfo = userService.getPage(userQo);
        return new Gson().toJson(pageInfo);
    }


    @PostMapping("/add")
    public String save(@RequestBody UserQo userQo) throws Exception{
        User user = CopyUtil.copy(userQo, User.class);
        user.setId(BaseIDUtils.randomUUID());
        String response = userService.insert(user);
        return response;
    }

    @PostMapping("/update")
    public String update(@RequestBody UserQo userQo) throws Exception{
        User user = CopyUtil.copy(userQo, User.class);
        String response = userService.update(user);
        return response;
    }

    @GetMapping("/delete")
    public String delete(@RequestParam String id) throws Exception {
        return userService.delete(id);
    }
}

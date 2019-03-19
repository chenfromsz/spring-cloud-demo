package com.demo.data.service;


import com.demo.common.UserQo;
import com.demo.data.domain.User;
import com.demo.data.mapper.UserMapper;
import com.demo.data.util.CommonUtils;
import com.demo.data.util.MapToBeanUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public String insert(User user){
        try {
            userMapper.insert(user);
            return "1";
        }catch (Exception e){
            e.printStackTrace();
            return e.getMessage();
        }
    }

    public String update(User user){
        try {
            userMapper.updateByPrimaryKey(user);
            return "1";
        }catch (Exception e){
            e.printStackTrace();
            return e.getMessage();
        }
    }

    public String delete(String id){
        try {
            userMapper.deleteByPrimaryKey(id);
            return "1";
        }catch (Exception e){
            e.printStackTrace();
            return e.getMessage();
        }
    }

    public User getById(String id) {
        try {
            return userMapper.selectByPrimaryKey(id);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    public PageInfo<User> getPage(UserQo userQo) throws Exception{
        try {
            Map<String, Object> map = MapToBeanUtil.transBean2Map(userQo);

            if(!StringUtils.isEmpty(userQo.getNickName())){
                map.put("nickName", userQo.getNickName());
            }

            //日期转字符串
            if (!StringUtils.isEmpty(userQo.getCreated())) {
                map.put("created", CommonUtils.formatDateTime(userQo.getCreated()));
            }

            PageHelper.startPage(userQo.getPage(), userQo.getSize());
            List<User> list = userMapper.getPage(map);

            return new PageInfo(list);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}

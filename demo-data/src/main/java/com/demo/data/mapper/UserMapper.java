package com.demo.data.mapper;


import com.demo.data.domain.User;
import com.demo.data.util.MyMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface UserMapper extends MyMapper<User> {
    /**
     * findOne
     * @param id
     * @return
     */
    User getOne(String id);

    List<User> getPage(@Param("map") Map<String, Object> map);

}
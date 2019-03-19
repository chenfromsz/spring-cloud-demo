package com.demo.web.service;

import com.demo.common.UserQo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("restapi")
public interface UserClient {
    @RequestMapping(method = RequestMethod.GET, value = "/user/get")
    String findById(@RequestParam("id") String id);

    @RequestMapping(method = RequestMethod.POST, value = "/user/page",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    String findPage(@RequestBody UserQo userQo);

    @RequestMapping(method = RequestMethod.POST, value = "/user/add",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    String create(@RequestBody UserQo userQo);

    @RequestMapping(method = RequestMethod.POST, value = "/user/update",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    String update(@RequestBody UserQo userQo);

    @RequestMapping(method = RequestMethod.GET, value = "/user/delete")
    String delete(@RequestParam("id") String id);
}

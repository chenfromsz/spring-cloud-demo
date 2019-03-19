package com.demo.web.controller;

import com.demo.common.UserQo;
import com.demo.common.util.TreeMapConvert;
import com.demo.web.service.UserRestService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Controller
@RequestMapping("/user")
public class UserController {
    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserRestService userRestService;

    @RequestMapping("/index")
    public String index(ModelMap model, HttpServletRequest request) throws Exception{
        return "user/index";
    }

    @RequestMapping(value="/{id}")
    public String show(ModelMap model, @PathVariable String id) {
        String json = userRestService.findById(id);
        UserQo user = new Gson().fromJson(json, UserQo.class);
        model.addAttribute("user", user);
        return "user/show";
    }

    @RequestMapping(value="/list")
    @ResponseBody
    public Page<Map<String, Object>> findAll(UserQo userQo) {
        String json = userRestService.findPage(userQo);

        Gson gson = TreeMapConvert.getGson();
        TreeMap<String,Object> page = gson.fromJson(json, new TypeToken< TreeMap<String,Object>>(){}.getType());

        Pageable pageable = PageRequest.of(userQo.getPage(), userQo.getSize(), null);
        List<UserQo> list = new ArrayList<>();

        if(page != null && page.get("list") != null) {
            list = gson.fromJson(page.get("list").toString(), new TypeToken<List<UserQo>>() {
            }.getType());
        }
        String count = page.get("total").toString();

        return new PageImpl(list, pageable, new Long(count));
    }

    @RequestMapping("/new")
    public String create(ModelMap model, HttpServletRequest request){
        return "user/new";
    }

    @RequestMapping(value="/save", method = RequestMethod.POST)
    @ResponseBody
    public String save(UserQo userQo) throws Exception{
        String ret = userRestService.create(userQo);
        logger.info("返回值=" + ret);
        return ret;
    }

    @RequestMapping(value="/edit/{id}")
    public String edit(ModelMap model, @PathVariable String id, HttpServletRequest request){
        String json = userRestService.findById(id);
        UserQo userQo = new Gson().fromJson(json, UserQo.class);

        model.addAttribute("user", userQo);

        return "user/edit";
    }

    @RequestMapping(method = RequestMethod.POST, value="/update")
    @ResponseBody
    public String update(UserQo userQo, HttpServletRequest request) throws Exception{
        String ret = userRestService.update(userQo);
        logger.info("返回值=" + ret);
        return ret;
    }

    @RequestMapping(value="/delete/{id}",method = RequestMethod.GET)
    @ResponseBody
    public String delete(@PathVariable String id) throws Exception{
        String ret = userRestService.delete(id);
        logger.info("返回值=" + ret);
        return ret;
    }

}

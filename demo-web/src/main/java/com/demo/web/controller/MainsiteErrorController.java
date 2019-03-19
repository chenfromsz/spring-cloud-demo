package com.demo.web.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MainsiteErrorController implements ErrorController {
    @Override
    public String getErrorPath() {
        return "/error";
    }


    @RequestMapping(value="/error")
    public String handleError(HttpServletRequest request, ModelMap modelMap){
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        modelMap.addAttribute("message", statusCode);
        return "403";
    }

}

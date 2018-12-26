package com.pxkj.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {

    @RequestMapping({"/","/index"})
    public String index(){
        return "index";
    }

    @RequestMapping("/loginPage")
    public String login(){
        return "login";
    }

    @PostMapping("/login")
    @ResponseBody
    public String login(String username, String password){
        try {
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            subject.login(token);
        } catch (AuthenticationException e) {
           return "{\"code\": 102, \"msg\": \"用户名或密码错误\"}";
        }
        return "{\"code\": 100, \"msg\": \"success\"}";
    }

}

package com.atguigu.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**

 */

@Controller
public class HelloController {

    @ResponseBody
    @RequestMapping(value = "/hello")
    public String hello(){
        return "hello world...";
    }
//    @RequestMapping({"/","/index.html"})
//    public String login(){
//        return "login";
//    }
//    @RequestMapping(value = "/main.html")
//    public String log(){
//        return "dashboard";
//    }

}

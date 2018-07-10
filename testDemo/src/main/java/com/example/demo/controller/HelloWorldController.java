package com.example.demo.controller;

import com.example.demo.domain.User;
import com.example.demo.service.HelloWorldService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by huanglijun on 2017/9/14.
 */
@RestController
@ResponseBody
public class HelloWorldController {

    @Autowired
    private HelloWorldService helloWorldService;

    @Autowired
    private UserService userService;

    @PostMapping("hello")
    public List<User> postIndex(@RequestBody String data){
        System.out.println("请求进来参数为:" + data);
        List<User> list = userService.findAll();
        return list;
    }

    @GetMapping("hello")
    public String getIndex(@RequestParam(value = "username",required = false) String username){
        String result = helloWorldService.hello();
        return "Hello World哈哈哈  username=" + username + "  result=" + result;
    }

    @RequestMapping("get")
    public String get(){
        return "拍0000贷sdfsdgf";
    }
}

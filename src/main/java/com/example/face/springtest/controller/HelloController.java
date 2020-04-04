package com.example.face.springtest.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description:
 * User: zhangll
 * Date: 2020-04-04
 * Time: 12:00
 */
@RestController
public class HelloController {


    @RequestMapping(value = "/hello")
    public String hello(){
        return "hello";
    }
}

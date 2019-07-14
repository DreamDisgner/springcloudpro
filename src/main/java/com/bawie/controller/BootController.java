package com.bawie.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/aa")
public class BootController {

    @RequestMapping("/list")
    public  String hello(){

        System.out.println("11111");
        return "hello";
    }

}

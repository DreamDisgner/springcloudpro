package com.bawie.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/aa")
public class BootController {

    @RequestMapping("/list")
    public  String hello(){

        System.out.println("11111");
        System.out.println("222");
        System.out.println("333");
        System.out.println("444");
        System.out.println("555");
        System.out.println("I am deal some problems finished!");
        System.out.println("i am bug bugfix I come agin");
        return "hello";
    }

}

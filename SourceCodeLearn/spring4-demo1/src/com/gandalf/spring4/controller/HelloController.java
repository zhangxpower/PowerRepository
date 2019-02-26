package com.gandalf.spring4.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {

    @RequestMapping(value = "/hello")
    public ModelAndView hello(){
        System.out.println("HELLO CONTROLLER RESPONSE....");
        return new ModelAndView("helloTest");
    }
}

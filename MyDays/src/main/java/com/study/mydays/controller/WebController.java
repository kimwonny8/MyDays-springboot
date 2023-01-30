package com.study.mydays.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping("/hello")
    public String hello(Model model){
        model.addAttribute("name","내 이름");
        model.addAttribute("img","image/img.jpg");
        return "hello";
    }

    @GetMapping("/")
    public String index(){
        return "home";
    }
}

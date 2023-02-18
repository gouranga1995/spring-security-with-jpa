package com.example.gouranga.springsessionjdbc.entity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

     @GetMapping("/home")
     public String getHome(){
         return "this is home page";
     }

    @GetMapping("/admin")
    public String getAdmin(){
        return "welcome admin";
    }

    @GetMapping("/user")
    public String getUser(){
        return "user page";
    }

}

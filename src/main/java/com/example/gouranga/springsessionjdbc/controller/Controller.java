package com.example.gouranga.springsessionjdbc.controller;

import com.example.gouranga.springsessionjdbc.entity.LoginRequestModel;
import com.example.gouranga.springsessionjdbc.repo.UserRepo;
import com.example.gouranga.springsessionjdbc.service.CustomAuthenticationProvider;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@Slf4j
public class Controller {

    //Log log= LogFactory.getLog(Controller.class);

    private static final String SPRING_SECURITY_CONTEXT_KEY=HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY;


    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepo userRepo;

    @Autowired
    CustomAuthenticationProvider authenticationProvider;

    private SecurityContextRepository securityContextRepository=new HttpSessionSecurityContextRepository();

     @GetMapping("/home")
     public String getHome(){
         log.info("this is info message in controller class:");
         log.warn("this is warn message in controller class");
         return "this is home page";
     }

    @PostMapping("/signIn")
    public String login(@RequestBody LoginRequestModel requestModel, HttpSession session){
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken(requestModel.getUserName(),requestModel.getPassword());
        Authentication auth= authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        System.out.println(auth);
        SecurityContext sc= SecurityContextHolder.getContext();
        sc.setAuthentication(auth);
        session.setAttribute(SPRING_SECURITY_CONTEXT_KEY,sc);
        return "logged in successfully";
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

package com.example.gouranga.springsessionjdbc.service;

import com.example.gouranga.springsessionjdbc.entity.MyUserDetails;
import com.example.gouranga.springsessionjdbc.entity.Users;
import com.example.gouranga.springsessionjdbc.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<Users> user=userRepo.findByUserName(s);
        user.orElseThrow(()->new BadCredentialsException("user/password incorrect"));
        return new MyUserDetails(s,user.get().getPassword());
    }


}

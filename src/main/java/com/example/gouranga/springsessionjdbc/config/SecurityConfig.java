package com.example.gouranga.springsessionjdbc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsService userDetailsService;

    //if we uncomment this below overridden configure method, it will always take anonymous authentication provider in package org.springframework.security.authentication;
    // and our custom authentication provider will not take in effect
    //so there are two way to get authentication manager bean 1. to get hold on the authentication manager builder and build the authentication manager with one or multiple
    //authentication provider by method chaning let say in memory auth,jdbc auth etc.in this case user detail service implementation.
    //2. add a bean for authentication manager. in this case we need to provide custom auth provider.
    //in both cases out custom user details service will be used to fetch the user details from db.
     @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //set our own configuration
        auth.userDetailsService(userDetailsService);
    }

    //all request needs to be authenticated.
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /*http.csrf().disable().authorizeRequests()
                //.antMatchers("/signIn").permitAll()
                .antMatchers("/user").hasAnyRole("USER","ADMIN")
                .antMatchers("/admin").hasRole("ADMIN")
                .anyRequest().authenticated().and().formLogin();*/

        http.csrf().disable().authorizeRequests()
                .antMatchers("/admin").hasRole("ADMIN")
                .antMatchers("/user").hasAnyRole("USER","ADMIN")
                .antMatchers("/").permitAll().and().httpBasic();
        //this httpBasic() method is used to enable http basic authentication.it is simple authentication build
        //into the http protocol.when enabled the server sends a 401 unauthrozed response for unauthenticated user.

        // ABOVE AND BELOW represents same
        /*http.csrf().disable().authorizeRequests()
                .antMatchers("/admin").hasAuthority("ROLE_ADMIN")
                .antMatchers("/user").hasAnyRole("ROLE_USER","ROLE_ADMIN")
                .antMatchers("/").and().httpBasic();*/

        //http.csrf().disable().authorizeRequests().anyRequest().authenticated().and().httpBasic();
    }

    //there is no encoding with this but we have add this so that spring security assume one dummy encoding.
    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }


    //this bean is added to use custome authentication provider and set the authenticated
    //user manually to spring security context.
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }

}

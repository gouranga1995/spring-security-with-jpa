package com.example.gouranga.springsessionjdbc.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "user_table")
public class Users {
    @Id
    public String userName;
    public String password;
    public boolean enabled;
}

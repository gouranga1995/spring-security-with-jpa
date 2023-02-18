package com.example.gouranga.springsessionjdbc.repo;

import com.example.gouranga.springsessionjdbc.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<Users,String> {
    Optional<Users> findByUserName(String userName);
}

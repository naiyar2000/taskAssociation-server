package com.example.demo.repository;

import com.example.demo.entity.UserDao;
import org.springframework.data.repository.CrudRepository;
public interface UserRepository extends CrudRepository<UserDao, Integer> {
    UserDao findByUsername(String username);
    UserDao findByEmail(String email);
}
package com.qf.dao;

import com.qf.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.jpa.repository.JpaRepository;

@Mapper
public interface UserRepository extends JpaRepository<User,Integer> {
    //自定义sql
    User findByEmail(String email);

    User findByUsername(String username);

    User findByUsernameAndPassword(String username, String password);
}
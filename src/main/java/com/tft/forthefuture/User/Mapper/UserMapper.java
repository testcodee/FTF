package com.tft.forthefuture.User.Mapper;

import com.tft.forthefuture.User.Vo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    List<User> findAllUsers();

    User findById(Long id);

    void insert(User user);

    User findByUsername(String username);

    void updateUser(User user);

}

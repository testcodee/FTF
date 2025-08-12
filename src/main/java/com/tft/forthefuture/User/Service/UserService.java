package com.tft.forthefuture.User.Service;

import com.tft.forthefuture.Common.config.UserConfig;
import com.tft.forthefuture.User.Mapper.UserMapper;
import com.tft.forthefuture.User.Vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserService {

    private final UserMapper userMapper;

    @Autowired
    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public List<User> getAllUsers() {
        return userMapper.findAllUsers();
    }

}

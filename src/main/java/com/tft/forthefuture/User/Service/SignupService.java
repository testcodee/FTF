package com.tft.forthefuture.User.Service;

import com.tft.forthefuture.Common.Config.UserConfig;
import com.tft.forthefuture.User.Mapper.UserMapper;
import com.tft.forthefuture.User.Vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;

@Service
public class SignupService {

    private final UserMapper userMapper;
    private final UserConfig userConfig;

    @Autowired
    public SignupService(UserMapper userMapper) {
        this.userMapper = userMapper;
        this.userConfig = new UserConfig();
    }

    public void registerUser(@RequestBody User user){
        //패스워드 암호화
        user.setPassword(userConfig.passwordEncoder().encode(user.getPassword()));
        //현재시간
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        userMapper.insert(user);
    }

}

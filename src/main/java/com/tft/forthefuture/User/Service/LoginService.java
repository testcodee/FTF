package com.tft.forthefuture.User.Service;

import com.tft.forthefuture.Common.Config.UserConfig;
import com.tft.forthefuture.User.Mapper.UserMapper;
import com.tft.forthefuture.User.Vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private final UserMapper userMapper;
    private final UserConfig userConfig;

    @Autowired
    public LoginService(UserMapper userMapper) {
        this.userMapper = userMapper;
        this.userConfig = new UserConfig();
    }

    /*
    * @param username 로그인 아이디
    * @param password 입력된 평문 비밀번호
    * @return 로그인 사용자 객체, 실패시 null
    * */
    public User login(String username, String password){
        User user = userMapper.findByUsername(username);
        if(user != null && userConfig.passwordEncoder().matches(password, user.getPassword())){
            return user;
        }
        return null;
    }

}

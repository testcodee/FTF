package com.tft.forthefuture.User.Service;

import com.tft.forthefuture.User.Mapper.UserMapper;
import com.tft.forthefuture.User.Vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserService {

    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> getAllUsers() {
        return userMapper.findAllUsers();
    }

    // 유저 프로필 업데이트
    public User userUpdateProfile(User user) {
        User findUser = userMapper.findById(user.getId());
        if(user == null){
            throw new IllegalArgumentException("User not found");
        }

        findUser.setName(user.getName());
        findUser.setEmail(user.getEmail());
        findUser.setNickname(user.getNickname());
        findUser.setUpdatedAt(LocalDateTime.now());

        userMapper.updateUser(findUser);
        return findUser;
    }

    public User getUserById(Long id) {
        return userMapper.findById(id);
    }

    public void changePassword(Long id, String currentPassword, String newPassword) {
        User user = userMapper.findById(id);
        if(user == null){
            throw new IllegalArgumentException("User not found");
        }
        if(!passwordEncoder.matches(currentPassword, user.getPassword())){
            throw new IllegalArgumentException("Current password does not match");
        }
        user.setPassword(passwordEncoder.encode(newPassword));
        user.setUpdatedAt(LocalDateTime.now());
        userMapper.updateUser(user);
    }
}

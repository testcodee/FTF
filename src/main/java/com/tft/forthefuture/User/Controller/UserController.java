package com.tft.forthefuture.User.Controller;

import org.springframework.ui.Model;
import com.tft.forthefuture.User.Service.UserService;
import com.tft.forthefuture.User.Vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

}

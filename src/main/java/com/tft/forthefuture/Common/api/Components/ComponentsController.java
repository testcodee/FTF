package com.tft.forthefuture.Common.api.Components;

import com.tft.forthefuture.User.Vo.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ComponentsController {
    @GetMapping("/components/bottom-nav")
    public String getBottomNav(){
        return "components/bottom-nav";
    }

    @GetMapping("/components/header")
    public String getHeader(HttpSession session, Model model){

        // 세션에서 User정보 받아서 Header에 user정보 표시
        User user = (User) session.getAttribute("loggedInUser");
        model.addAttribute("user", user);

        return "components/header";
    }
}

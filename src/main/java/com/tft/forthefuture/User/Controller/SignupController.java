package com.tft.forthefuture.User.Controller;

import com.tft.forthefuture.User.Service.SignupService;
import com.tft.forthefuture.User.Vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
public class SignupController {

    SignupService signupService;

    @Autowired
    public SignupController(SignupService signupService) {
        this.signupService = signupService;
    }

    // 회원가입 페이지 표시
    @GetMapping("/signup")
    public String signupForm(Model model) {
        model.addAttribute("user", new User());
        return "user/signup"; // templates/user/signup.html 반환
    }

    // 회원가입 처리
    @PostMapping("/signup")
    public String processSignup(@RequestBody User user, Model model) {
        try {
            signupService.registerUser(user);
            return "redirect:/user/login"; // 회원가입 성공 시 로그인 페이지로 리다이렉트
        } catch (Exception e) {
            model.addAttribute("error", "회원가입 중 오류가 발생했습니다: " + e.getMessage());
            return "/user/signup"; // 오류 발생 시 회원가입 페이지로 돌아감
        }
    }

}

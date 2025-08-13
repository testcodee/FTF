package com.tft.forthefuture.User.Controller;

import com.tft.forthefuture.User.Service.LoginService;
import com.tft.forthefuture.User.Vo.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping("/login")
    public String login(){
        return "/user/login";
    }

    @PostMapping("/login")
    public String processLogin(@RequestParam String username,
                               @RequestParam String password,
                               HttpSession session,
                               Model model){
        User user = loginService.login(username, password);
        if (user != null) {
            // 로그인 성공 시 세션에 사용자 정보 저장
            // 세션에 저장될 이름은 다른 곳에서 "loggedInUser"로 사용할 예정 (User 객체와 이름 충돌 방지)
            session.setAttribute("loggedInUser", user);
            return "redirect:/main"; // 메인 페이지로 리다이렉트 (예: 대시보드)
        } else {
            // 로그인 실패 시 에러 메시지를 모델에 추가하고 로그인 페이지로 다시 이동
            model.addAttribute("error", "아이디 또는 비밀번호가 올바르지 않습니다.");
            return "/user/login";
        }
    }
    
    // 로그아웃 세션 제거 후 로그인 화면으로
    @GetMapping("logout")
    public String logout(HttpSession session){
        session.removeAttribute("loggedInUser");
        return "/user/login";
    }
}

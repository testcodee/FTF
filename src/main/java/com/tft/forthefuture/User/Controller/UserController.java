package com.tft.forthefuture.User.Controller;

import jakarta.servlet.http.HttpSession;
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

    @GetMapping("/settings")
    public String showSetting(Model model, HttpSession session){
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        
        if(loggedInUser==null){ //로그인 세션이 없을경우 로그인 페이지로 되돌아감
            return "redirect:/user/login";
        }
        
        model.addAttribute("user", loggedInUser);
        
        return "/user/settings";
    }

    @PostMapping("/settings/updateProfile")
    public String updateProfile(Model model, HttpSession session, @ModelAttribute User user){
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if(loggedInUser==null || !loggedInUser.getId().equals(user.getId())){
            return "redirect:/user/login";
        }

        try{

            User updateUser = userService.userUpdateProfile(user);
            session.setAttribute("loggedInUser", updateUser);

            model.addAttribute("message", "프로필이 성공적으로 업데이트되었습니다.");
        }catch(Exception e){
            model.addAttribute("error", "업데이트 중 오류 발생 : "+e.getMessage());
        }

        model.addAttribute("user", userService.getUserById(loggedInUser.getId()));

        return "/user/settings";
    }

    @PostMapping("/settings/changePassword")
    public String changePassword(Model model, HttpSession session,
                                 @RequestParam String currentPassword,
                                 @RequestParam String newPassword,
                                 @RequestParam String confirmPassword){
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if(loggedInUser==null){return "redirect:/user/login";}

        try{
            if(!newPassword.equals(confirmPassword)){
                throw new IllegalArgumentException("새 비밀번호와 비밀번호 확인이 일치하지 않습니다.");
            }
            userService.changePassword(loggedInUser.getId(), currentPassword, newPassword);
            model.addAttribute("message", "비밀번호가 성공적으로 변경되었습니다.");
        }catch(IllegalArgumentException e){
            model.addAttribute("error", e.getMessage());
        }catch(Exception e){
            model.addAttribute("error", "비밀번호 변경 중 오류 : " + e.getMessage());
        }

        model.addAttribute("user", loggedInUser);

        return "/user/settings";
    }

}

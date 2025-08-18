package com.tft.forthefuture.Common.config;

import com.tft.forthefuture.User.Vo.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;

public class loginCheckInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle (HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("loggedInUser");
        if (user == null) {
            response.sendRedirect("/user/login");
            return false;
        }
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
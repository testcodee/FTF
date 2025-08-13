package com.tft.forthefuture.Accountbook.Controller;

import com.tft.forthefuture.Accountbook.Service.AccountService;
import com.tft.forthefuture.Accountbook.Service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccountController {
    private AccountService accountService;
    private DashboardService dashboardService;

    @Autowired
    public AccountController(AccountService accountService, DashboardService dashboardService) {
        this.accountService = accountService;
        this.dashboardService = dashboardService;
    }

    @GetMapping("/assets")
    public String assets(Model model) {
        model.addAttribute("totalAssets","100000");
        model.addAttribute("totalDebt","100000");
        model.addAttribute("netWorth","-100000");

        return "/account/assets";
    }
}

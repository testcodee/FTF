package com.tft.forthefuture.Accountbook.Controller;

import com.tft.forthefuture.Accountbook.Service.AccountService;
import com.tft.forthefuture.Accountbook.Service.DashboardService;
import com.tft.forthefuture.Accountbook.Vo.Account;
import com.tft.forthefuture.User.Vo.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.math.BigDecimal;
import java.util.List;

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
    public String assets(Model model, HttpSession session) {
        User user = (User) session.getAttribute("loggedInUser");

        List<Account> accounts = accountService.getAccountByUserId(user.getId());
        BigDecimal totalAssets = accountService.findTotalBalanceByUserId(user.getId());
        BigDecimal totalDebt = accountService.findTotalDebtByUserId(user.getId());
        BigDecimal netWorth = totalAssets.add(totalDebt);

        model.addAttribute("accounts", accounts);
        model.addAttribute("totalAssets",totalAssets);
        model.addAttribute("totalDebt",totalDebt);
        model.addAttribute("netWorth",netWorth);

        return "/account/assets";
    }
}

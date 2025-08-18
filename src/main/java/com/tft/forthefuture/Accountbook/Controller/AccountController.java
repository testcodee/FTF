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
        if (user == null) {
            return "redirect:/login";
        }
        /*TODO List
        * 1. 총 자산
        * 2. 총 부채
        * 3. 순 자산
        * 4. 계좌 별 잔액 관리
        *  - accounts 테이블
        *   balance : 현재 잔액
        *   account_name : 계좌명 (예: 현금, 우리은행 통장, 국민카드)
        *   account_type : 계좌 유형 (예: 현금, 은행, 신용카드, 투자)
        * */
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

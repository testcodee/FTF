package com.tft.forthefuture.Accountbook.Controller;

import com.tft.forthefuture.Accountbook.Service.AccountService;
import com.tft.forthefuture.Accountbook.Service.DashboardService;
import com.tft.forthefuture.Accountbook.Vo.Account;
import com.tft.forthefuture.User.Vo.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

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
        BigDecimal totalAssets = accountService.findTotalBalanceByUserId(user.getId()); // 자산
        BigDecimal totalDebt = accountService.findTotalDebtByUserId(user.getId()); //부채
        BigDecimal netWorth = totalAssets.subtract(totalDebt); // 순자산 = 자산 - 부채

        model.addAttribute("accounts", accounts);
        model.addAttribute("totalAssets",totalAssets);
        model.addAttribute("totalDebt",totalDebt);
        model.addAttribute("netWorth",netWorth);

        return "/account/assets";
    }

    @PostMapping("/accounts/update")
    @ResponseBody
    public ResponseEntity<?> updateAccount(Model model, HttpSession session, @RequestBody List<Account> accounts) {
        User user = (User) session.getAttribute("loggedInUser");
        try {
            accountService.updateAccountBalances(accounts);
            return ResponseEntity.ok().body(Map.of("message", "잔액이 성공적으로 업데이트되었습니다."));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "잔액 업데이트 중 오류가 발생했습니다: " + e.getMessage()));
        }
    }

    @PostMapping("/accounts/statistics")
    @ResponseBody
    public ResponseEntity<?> statistics(Model model, HttpSession session) {
        User user = (User) session.getAttribute("loggedInUser");
        BigDecimal totalDebt = accountService.findTotalDebtByUserId(user.getId());
        model.addAttribute("totalDebt",totalDebt);
        model.addAttribute("userId",user.getId());
        return new ResponseEntity<>(model, HttpStatus.OK);
    }
}

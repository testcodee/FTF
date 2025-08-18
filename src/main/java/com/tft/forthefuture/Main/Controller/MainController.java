package com.tft.forthefuture.Main.Controller;

import com.tft.forthefuture.Accountbook.Service.DashboardService;
import com.tft.forthefuture.User.Vo.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Controller
public class MainController {

    private final DashboardService dashboardService;

    @Autowired
    public MainController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/")
    public String Main(){
        return "/user/login";
    }

    @GetMapping("/main")
    public String main(HttpSession session, Model model){
        User user = (User) session.getAttribute("loggedInUser");

        model.addAttribute("user", user);

        // -- 실제 데이터 가져오기 --
        Long userId = user.getId(); // 로그인한 사용자의 ID
        LocalDate today = LocalDate.now();
        LocalDate startOfMonth = today.withDayOfMonth(1); // 이 달의 시작일
        LocalDate endOfMonth = today.withDayOfMonth(today.lengthOfMonth()); // 이 달의 마지막일

        BigDecimal totalAssets = dashboardService.calculateTotalAssets(userId);
        BigDecimal totalDebt = dashboardService.calculateTotalDebt(userId);
        BigDecimal netWorth = totalAssets.subtract(totalDebt); // 순자산 = 총 자산 - 총 부채

        BigDecimal monthlyIncome = dashboardService.calculateMonthlyIncome(userId, startOfMonth, endOfMonth);
        BigDecimal monthlyExpense = dashboardService.calculateMonthlyExpense(userId, startOfMonth, endOfMonth);
        BigDecimal remainingBudget = monthlyIncome.subtract(monthlyExpense);

        // 숫자를 포맷팅하여 모델에 추가 (예: 120,000,000)
        DecimalFormat decimalFormat = new DecimalFormat("#,##0"); // 금액을 콤마 형식으로 포맷
        model.addAttribute("totalAssets", decimalFormat.format(totalAssets));
        model.addAttribute("totalDebt", decimalFormat.format(totalDebt));
        model.addAttribute("netWorth", decimalFormat.format(netWorth));
        model.addAttribute("monthlyIncome", decimalFormat.format(monthlyIncome));
        model.addAttribute("monthlyExpense", decimalFormat.format(monthlyExpense));
        model.addAttribute("remainingBudget", decimalFormat.format(remainingBudget));

        return "main"; // templates/main.html 렌더링
    }

}

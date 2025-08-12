package com.tft.forthefuture.Accountbook.Service;

import com.tft.forthefuture.Accountbook.Mapper.AccountMapper;
import com.tft.forthefuture.Accountbook.Mapper.TransactionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class DashboardService {
    private final AccountMapper accountMapper;
    private final TransactionMapper transactionMapper;

    @Autowired
    public DashboardService(AccountMapper accountMapper, TransactionMapper transactionMapper) {
        this.accountMapper = accountMapper;
        this.transactionMapper = transactionMapper;
    }

    public BigDecimal calculateTotalAssets(Long userId) {
        // 모든 계좌의 잔액 합계를 계산하여 총 자산을 구함
        // 계좌 잔액이 실시간으로 업데이트된다는 가정이 필요 (또는 모든 거래 내역 합산)
        return accountMapper.findTotalBalanceByUserId(userId);
    }

    public BigDecimal calculateTotalDebt(Long userId) {
        // 부채 계좌(신용카드, 대출 등)의 합계를 계산하거나, transaction_type을 이용
        // 예시: account_type이 CREDIT_CARD이거나 LOAN인 계좌의 잔액 합
        return accountMapper.findTotalDebtByUserId(userId);
    }

    public BigDecimal calculateMonthlyIncome(Long userId, LocalDate startDate, LocalDate endDate) {
        return transactionMapper.getTotalIncomeByPeriod(userId, startDate, endDate);
    }

    public BigDecimal calculateMonthlyExpense(Long userId, LocalDate startDate, LocalDate endDate) {
        return transactionMapper.getTotalExpenseByPeriod(userId, startDate, endDate);
    }
}

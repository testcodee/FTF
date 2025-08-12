package com.tft.forthefuture.Accountbook.Mapper;

import com.tft.forthefuture.Accountbook.Vo.Transaction;
import org.apache.ibatis.annotations.Mapper;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import com.tft.forthefuture.User.Vo.User;

@Mapper
public interface TransactionMapper {
    // 거래 내역 추가
    void insert(Transaction transaction);

    // 거래 내역 조회
    Transaction findById(Long id);
    List<Transaction> findByUserId(Long userId);

    // 기간별 거래 내역 조회
    List<Transaction> findByUserIdAndPeriod(Long userId, LocalDate startDate, LocalDate endDate);

    // 특정 기간 내 수입/지출 합계
    BigDecimal getTotalIncomeByPeriod(Long userId, LocalDate startDate, LocalDate endDate);
    BigDecimal getTotalExpenseByPeriod(Long userId, LocalDate startDate, LocalDate endDate);

    // 카테고리별 지출 합계
    List<Map<String, Object>> getExpenseSummaryByCategory(Long userId, LocalDate startDate, LocalDate endDate);

    // 월별 수입/지출 추이
    List<Map<String, Object>> getMonthlyIncomeTrend(Long userId, int year);
    List<Map<String, Object>> getMonthlyExpenseTrend(Long userId, int year);

    // 거래 내역 수정/삭제
    void update(Transaction transaction);
    void delete(Long id);
}

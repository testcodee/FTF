package com.tft.forthefuture.Accountbook.Vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction implements Serializable {
    private Long id; //거래 고유 식별자
    private Long userId; // 사용자 ID
    private Long accountId; // 관련 계좌 ID
    private String transactionType; // 거래 유형 (예: INCOME, EXPENSE, TRANSFER)
    private BigDecimal amount; // 거래 금액
    private String category; // 거래 카테고리 (예: 식비, 교통비, 급여)
    private String description; // 거래 상세 설명 (사용처/내역 파싱 결과)
    private LocalDate transactionDate; // 거래 발생 일자 java.time.LocalDate 사용
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

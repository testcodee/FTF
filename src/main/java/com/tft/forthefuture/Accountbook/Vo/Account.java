package com.tft.forthefuture.Accountbook.Vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account implements Serializable {
    private Long id; // 계좌 고유식별자
    private Long userId; //사용자 ID
    private String accountName; // 계좌명 (예: 현금, 우리은행 통장, 국민카드)
    private String accountType; // "CASH", "BANK", "CREDIT_CARD", "INVESTMENT"
    private BigDecimal balance; // 현재 잔액 BigDecimal 사용 권장
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

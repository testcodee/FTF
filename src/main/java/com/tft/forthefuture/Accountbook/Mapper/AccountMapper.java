package com.tft.forthefuture.Accountbook.Mapper;

import com.tft.forthefuture.Accountbook.Vo.Account;
import org.apache.ibatis.annotations.Mapper;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface AccountMapper {
    // 계좌 조회
    List<Account> findAllByUserId(Long userId);
    Account findById(Long id);

    // 계좌 총액 조회 (자산)
    BigDecimal findTotalBalanceByUserId(Long userId);

    // 부채 계좌(신용카드, 대출 등) 총액 조회
    BigDecimal findTotalDebtByUserId(Long userId);

    // 계좌 추가/수정/삭제
    void insert(Account account);
    void update(Account account);
    void updateBalance(Long id, BigDecimal newBalance);
    void delete(Long id);

    // 계좌 유형별 조회
    List<Account> findByUserIdAndType(Long userId, String accountType);
}

package com.tft.forthefuture.Accountbook.Service;

import com.tft.forthefuture.Accountbook.Mapper.AccountMapper;
import com.tft.forthefuture.Accountbook.Mapper.TransactionMapper;
import com.tft.forthefuture.Accountbook.Vo.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class AccountService {
    AccountMapper accountMapper;
    TransactionMapper transactionMapper;

    @Autowired
    public AccountService(AccountMapper accountMapper, TransactionMapper transactionMapper) {
        this.accountMapper = accountMapper;
        this.transactionMapper = transactionMapper;
    }

    public List<Account> getAccountByUserId(Long userId) {
       return accountMapper.findAllByUserId(userId);
    }

    // 계좌 총액 총 자산 계산
    public BigDecimal findTotalBalanceByUserId(Long userId) {
        return accountMapper.findTotalBalanceByUserId(userId);
    }

    public BigDecimal findTotalDebtByUserId(Long userId) {
        return accountMapper.findTotalDebtByUserId(userId);
    }


}

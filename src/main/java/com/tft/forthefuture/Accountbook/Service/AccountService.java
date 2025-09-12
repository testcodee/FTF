package com.tft.forthefuture.Accountbook.Service;

import com.tft.forthefuture.Accountbook.Mapper.AccountMapper;
import com.tft.forthefuture.Accountbook.Mapper.TransactionMapper;
import com.tft.forthefuture.Accountbook.Vo.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    // 계좌 (카드, 은행 등) 아이디로 조회
    public List<Account> getAccountByUserId(Long userId) {
       return accountMapper.findAllByUserId(userId);
    }

    // 계좌 총액 총 자산 계산
    public BigDecimal findTotalBalanceByUserId(Long userId) {
        return accountMapper.findTotalBalanceByUserId(userId);
    }

    // 부채 총액
    public BigDecimal findTotalDebtByUserId(Long userId) { return accountMapper.findTotalDebtByUserId(userId); }

    @Transactional // 예외 발생 시 트랜젝션 롤백
    public void updateAccountBalances(List<Account> accounts) {
        accounts.forEach(account -> {accountMapper.updateBalance(account);});
    }

}

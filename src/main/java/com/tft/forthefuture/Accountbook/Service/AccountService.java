package com.tft.forthefuture.Accountbook.Service;

import com.tft.forthefuture.Accountbook.Mapper.AccountMapper;
import com.tft.forthefuture.Accountbook.Mapper.TransactionMapper;
import com.tft.forthefuture.Accountbook.Vo.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    AccountMapper accountMapper;
    TransactionMapper transactionMapper;

    @Autowired
    public AccountService(AccountMapper accountMapper, TransactionMapper transactionMapper) {
        this.accountMapper = accountMapper;
        this.transactionMapper = transactionMapper;
    }

    public Account getAccountByUserId(Long userId) {
       return accountMapper.findById(userId);
    }


}

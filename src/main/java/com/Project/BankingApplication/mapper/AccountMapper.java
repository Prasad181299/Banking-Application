package com.Project.BankingApplication.mapper;

import com.Project.BankingApplication.dto.AccountDto;
import com.Project.BankingApplication.entity.Account;

public class AccountMapper {

    public static Account mapToAccount(AccountDto accountDto) {
        if (accountDto == null) {
            return null;
        }

        return new Account(
            accountDto.getId(),
            accountDto.getAccountHolderName(),
            accountDto.getBalance()
        );
    }

    public static AccountDto mapToAccountDto(Account account) {
        if (account == null) {
            return null;
        }

        return new AccountDto(
            account.getId(),
            account.getAccountHolderName(),
            account.getBalance()
        );
    }
}

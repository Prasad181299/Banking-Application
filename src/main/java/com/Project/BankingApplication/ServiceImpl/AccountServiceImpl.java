package com.Project.BankingApplication.ServiceImpl;

import com.Project.BankingApplication.dto.AccountDto;
import com.Project.BankingApplication.entity.Account;
import com.Project.BankingApplication.mapper.AccountMapper;
import com.Project.BankingApplication.repository.AccountRepository;
import com.Project.BankingApplication.service.AccountService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account = AccountMapper.mapToAccount(accountDto);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

	@Override
	public AccountDto getAccountById(Long id) {
	Account account = accountRepository.findById(id).orElseThrow(()-> new RuntimeException("Account does not exists"));
	return AccountMapper.mapToAccountDto(account);
	
	}

	@Override
	public AccountDto deposit(Long id, double amount) {
	Account account = accountRepository.findById(id).orElseThrow(()-> new RuntimeException("Account does not exists"));
	double totalBalance = account.getBalance() +amount;
	account.setBalance(totalBalance);
	Account savedAccount = accountRepository.save(account);
	return AccountMapper.mapToAccountDto(savedAccount);
	}

	@Override
	public AccountDto withdraw(Long id, double amount) {
	Account account = accountRepository.findById(id).orElseThrow(()-> new RuntimeException("Account does not exists"));
	if(account.getBalance()<amount) {	
		throw new RuntimeException("Insufficient Balance");	
	}
	
	double totalBalance = account.getBalance()-amount;
	account.setBalance(totalBalance);
	Account SavedAccount = accountRepository.save(account);
		return AccountMapper.mapToAccountDto(SavedAccount);
	}

	@Override
	public List<AccountDto> getAllAccounts() {
		return accountRepository.findAll().stream().map((account)->AccountMapper.mapToAccountDto(account)).collect(Collectors.toList());
		
	}

	@Override
	public void deleteAccount(Long id) {
		Account account = accountRepository.findById(id).orElseThrow(()-> new RuntimeException("Account does not exists"));
		accountRepository.delete(account);
	}

	
	
	
	}

   
        
   
    


	
	        
	


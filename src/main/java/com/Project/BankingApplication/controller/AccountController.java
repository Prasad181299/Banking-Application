package com.Project.BankingApplication.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Project.BankingApplication.dto.AccountDto;
import com.Project.BankingApplication.service.AccountService;

@RestController
@RequestMapping("/api/accounts")

public class AccountController {
	
    private AccountService accountService;
    	
    	public AccountController(AccountService accountService) {
    	
    		this.accountService = accountService;
    }

    //add Account Rest api
    	
   @PostMapping
    public ResponseEntity<AccountDto>addAccount(@RequestBody AccountDto accountDto){	
    	return new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);
    }

   
   //get single account rest api
   
   @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable Long id){
    	
     AccountDto accountDto = accountService.getAccountById(id);	
    	
     return ResponseEntity.ok(accountDto);
    }
   
  
   // Deposit account rest api
   @PutMapping("/{id}/deposit")
   public ResponseEntity<AccountDto> deposit(@PathVariable Long id, @RequestBody Map<String, Double>request){
	   
    AccountDto accountDto = accountService.deposit(id, request.get("amount"));
    
    return ResponseEntity.ok(accountDto);
   
   }
  
   // Withdraw account rest api
   @PutMapping("/{id}/withdraw")
   public ResponseEntity<AccountDto> withdraw(@PathVariable Long id, @RequestBody Map<String, Double>request){
    
	 Double amount = request.get("amount");  
     AccountDto accountDto = accountService.withdraw(id, amount);
     return ResponseEntity.ok(accountDto);
   }
   // get all accounts api
   @GetMapping
   public ResponseEntity<List<AccountDto>>getAllAccounts(){
	  
	   List<AccountDto> accountDto = accountService.getAllAccounts();
	   
	   return ResponseEntity.ok(accountDto);
	   
   }
   
   // Delete account rest api
   @DeleteMapping("/{id}")
    public ResponseEntity<String>deleteAccount(@PathVariable Long id){
    	accountService.deleteAccount(id);
    	return ResponseEntity.ok("Account Deleted Successfully");
    }
}
   







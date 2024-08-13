package com.Project.BankingApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Project.BankingApplication.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

}

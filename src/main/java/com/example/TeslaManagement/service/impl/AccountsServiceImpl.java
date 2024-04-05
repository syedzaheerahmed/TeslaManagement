package com.example.TeslaManagement.service.impl;

import com.example.TeslaManagement.model.Accounts;
import com.example.TeslaManagement.repository.AccountsRepo;
import com.example.TeslaManagement.service.AccountsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountsServiceImpl implements AccountsService {
    AccountsRepo accountRepository;

    @Autowired
    public AccountsServiceImpl(AccountsRepo accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public List<Accounts> getAllAccounts() {
        return accountRepository.findAll();
    }

    @Override
    public String createAccount(Accounts account) {
        try {
             accountRepository.save(account);
        }
        catch (Exception e) {
            System.out.println("Exception Occurred : "+e.getMessage());
            return "Error in adding branch details";
        }
        return "Accounts added successfully";
    }
}

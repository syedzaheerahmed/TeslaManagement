package com.example.TeslaManagement.service.impl;

import com.example.TeslaManagement.model.Accounts;
import com.example.TeslaManagement.model.Branches;
import com.example.TeslaManagement.repository.AccountsRepo;
import com.example.TeslaManagement.service.AccountsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountsServiceImpl implements AccountsService {

    private final AccountsRepo accountsRepo;
    @Autowired
    public AccountsServiceImpl(AccountsRepo accountsRepo) {
        this.accountsRepo = accountsRepo;
    }
    @Override
    public String createAccount(Accounts account) {
        try {
            accountsRepo.save(account);
            return "Account created successfully";
        } catch (Exception e) {
            System.out.println("Exception Occurred : " + e.getMessage());
            return "Error in adding account details";
        }
    }

    @Override
    public String updateAccount(Accounts account) {
        try {
            accountsRepo.save(account);
            return "Account details updated successfully";
        } catch (Exception e) {
            System.out.println("Exception Occurred : " + e.getMessage());
            return "Error in updating account details";
        }
    }

    @Override
    public String deleteAccount(Long accountId) {
        try {
            accountsRepo.deleteById(accountId);
            return "Account details deleted successfully";
        } catch (Exception e) {
            System.out.println("Exception Occurred : " + e.getMessage());
            return "Error in deleting account details";
        }
    }

    @Override
    public Accounts getAccountDetails(Long accountId) {
        try {
            return accountsRepo.findById(accountId).orElse(null);
        } catch (Exception e) {
            System.out.println("Exception Occurred : " + e.getMessage());
            return null;
        }
    }

    @Override
    public List<Accounts> getAllAccounts() {
        return accountsRepo.findAll();
    }
}

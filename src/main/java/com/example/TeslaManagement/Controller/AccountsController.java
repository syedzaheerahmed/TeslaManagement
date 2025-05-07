package com.example.TeslaManagement.Controller;

import com.example.TeslaManagement.service.AccountsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountsController {
    AccountsService accountsService;
    @Autowired
    public AccountsController(AccountsService accountsService) {
        this.accountsService = accountsService;
    }

    // Get the details of a specific account
    @GetMapping("/getAccountDetails/{accountId}")
    public Accounts getAccountDetails(@PathVariable(value = "accountId") Long accountId) {
        return accountsService.getAccountDetails(accountId);
    }

    // Get all accounts
    @GetMapping("/getAllAccounts")
    public List<Accounts> getAllAccounts() {
        return accountsService.getAllAccounts();
    }

    // Add an account
    @PostMapping("/addAccount")
    public String addAccount(@RequestBody Accounts account) {
        return accountsService.createAccount(account);
    }

    // Update an account
    @PutMapping("/updateAccount")
    public String updateAccount(@RequestBody Accounts account) {
        return accountsService.updateAccount(account);
    }

    // Delete an account
    @DeleteMapping("/deleteAccount/{accountId}")
    public String deleteAccount(@PathVariable(value = "accountId") Long accountId) {
        return accountsService.deleteAccount(accountId);
    }
}

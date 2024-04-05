package com.example.TeslaManagement.Controller;
import com.example.TeslaManagement.model.Accounts;
import com.example.TeslaManagement.repository.AccountsRepo;
import com.example.TeslaManagement.service.AccountsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/accounts")
public class AccountsController {
    private final AccountsService accountsService;

    @Autowired
    public AccountsController(AccountsService accountsService) {
        this.accountsService = accountsService;
    }

    @GetMapping
    public List<Accounts> getAllAccounts() {
        return accountsService.getAllAccounts();
    }

    @PostMapping
    public String createAccount(@RequestBody Accounts accounts) {
        return accountsService.createAccount(accounts);
    }
}

package com.example.TeslaManagement.service;

import java.util.List;

public interface AccountsService {
    String createAccount(Accounts account);
    String updateAccount(Accounts account);
    String deleteAccount(Long accountId);
    Accounts getAccountDetails(Long accountId);
    List<Accounts> getAllAccounts();
}

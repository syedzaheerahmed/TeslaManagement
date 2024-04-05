package com.example.TeslaManagement.service;

import com.example.TeslaManagement.model.Accounts;

import java.util.List;

public interface  AccountsService {
    List<Accounts> getAllAccounts();
    String createAccount(Accounts account);
}

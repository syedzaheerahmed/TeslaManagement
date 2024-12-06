package com.example.TeslaManagement.service;

import com.example.TeslaManagement.DTO.TransactionListingDTO;

import java.util.Date;
import java.util.List;

public interface TransactionListingService {
    List<TransactionListingDTO> getTransactionsList(Date fromDate);
}
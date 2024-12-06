package com.example.TeslaManagement.service;

import com.example.TeslaManagement.DTO.TransactionReportDTO;

import java.util.Date;

public interface TransactionReportService {
    TransactionReportDTO generateTransactionReport(Date fromDate);
}

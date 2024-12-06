package com.example.TeslaManagement.Controller;

import com.example.TeslaManagement.CustomException.ResourceNotFoundException;
import com.example.TeslaManagement.DTO.TransactionAccountRequestDTO;
import com.example.TeslaManagement.DTO.TransactionListingDTO;
import com.example.TeslaManagement.DTO.TransactionReportDTO;
import com.example.TeslaManagement.model.Transactions;
import com.example.TeslaManagement.service.TransactionListingService;
import com.example.TeslaManagement.service.TransactionReportService;
import com.example.TeslaManagement.service.TransactionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/transactions")
public class TransactionsController {

    @Autowired
    private final TransactionsService transactionsService;

    @Autowired
    private TransactionReportService transactionReportService;

    @Autowired
    private TransactionListingService transactionListingService;

    @Autowired
    public TransactionsController(TransactionsService transactionsService) {
        this.transactionsService = transactionsService;
    }


    @PostMapping
    public ResponseEntity<?> createTransaction(@RequestBody Transactions transaction) {
        try {
            Transactions savedTransaction = transactionsService.createTransaction(transaction);
            return new ResponseEntity<>(savedTransaction, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error creating transaction: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTransactionById(@PathVariable Long id) {
        try {
            Transactions transaction = transactionsService.getTransactionById(id);
            return ResponseEntity.ok(transaction);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Transaction not found with ID: " + id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error fetching transaction: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllTransactions() {
        try {
            List<Transactions> transactions = transactionsService.getAllTransactions();
            return ResponseEntity.ok(transactions);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error fetching transactions: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTransaction(
            @PathVariable Long id, @RequestBody Transactions transaction) {
        try {
            Transactions updatedTransaction = transactionsService.updateTransaction(id, transaction);
            return ResponseEntity.ok(updatedTransaction);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Transaction not found with ID: " + id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error updating transaction: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTransaction(@PathVariable Long id) {
        try {
            transactionsService.deleteTransaction(id);
            return ResponseEntity.noContent().build();
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Transaction not found with ID: " + id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error deleting transaction: " + e.getMessage());
        }
    }

    @PostMapping("/create-transaction-account")
    public ResponseEntity<?> createTransaction(
            @RequestBody TransactionAccountRequestDTO requestDTO) {
        try {
            Transactions savedTransaction = transactionsService.createTransactionWithAccount(requestDTO);
            return new ResponseEntity<>(savedTransaction, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error creating transaction: " + e.getMessage());
        }
    }

    @GetMapping("/generate-report")
    public ResponseEntity<?> generateReport(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fromDate) {
        try {
            TransactionReportDTO report = transactionReportService.generateTransactionReport(fromDate);
            return ResponseEntity.ok(report);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error creating transaction: " + e.getMessage());
        }
    }

    @GetMapping("/view-transaction")
    public ResponseEntity<List<TransactionListingDTO>> getTransactionsList(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fromDate) {
        List<TransactionListingDTO> transactions = transactionListingService.getTransactionsList(fromDate);
        return ResponseEntity.ok(transactions);
    }
}


package com.example.TeslaManagement.service;

import com.example.TeslaManagement.DTO.StudentDTO;
import com.example.TeslaManagement.DTO.StudentRequestDTO;
import com.example.TeslaManagement.model.TransactionCategory;

import java.util.List;

public interface TransactionCategoryService {
    TransactionCategory createCategory(TransactionCategory category);
    List<TransactionCategory> getAllCategory();
    TransactionCategory getCategoryById(Long categoryId);
    TransactionCategory updateCategory(Long categoryId, TransactionCategory categoryRequest);
    void deleteCategory(Long studentId);
}

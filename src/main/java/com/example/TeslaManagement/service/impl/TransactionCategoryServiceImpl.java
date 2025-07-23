package com.example.TeslaManagement.service.impl;

import com.example.TeslaManagement.CustomException.ResourceNotFoundException;
import com.example.TeslaManagement.model.TransactionCategory;
import com.example.TeslaManagement.repository.TransactionCategoryRepo;
import com.example.TeslaManagement.service.TransactionCategoryService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionCategoryServiceImpl implements TransactionCategoryService {

    @Autowired
    private TransactionCategoryRepo transactionCategoryRepo;

    @Override
    public TransactionCategory createCategory(TransactionCategory category ){
        return transactionCategoryRepo.save(category);
    }

    @Override
    public List<TransactionCategory> getAllCategory(){
        return transactionCategoryRepo.findAll();
    }

    @Override
    public TransactionCategory getCategoryById(Long categoryId){
        return transactionCategoryRepo.findById(categoryId).orElseThrow(() -> new EntityNotFoundException("Category not found with id: " + categoryId));
    }

    @Override
    public TransactionCategory updateCategory(Long categoryId, TransactionCategory categoryRequest){
        TransactionCategory category = getCategoryById(categoryId);
        category.setCategoryName( categoryRequest.getCategoryName() );
        return transactionCategoryRepo.save( category );
    }

    @Override
    @PreAuthorize("hasAnyRole('Super Admin', 'Admin')")
    public void deleteCategory(Long categoryId){
        TransactionCategory transactionCategory = transactionCategoryRepo.findById(categoryId)
                        .orElseThrow(() -> new ResourceNotFoundException(" Requested Category Not Found"));
        transactionCategoryRepo.deleteById(categoryId);
    }

}

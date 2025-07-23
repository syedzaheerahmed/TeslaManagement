package com.example.TeslaManagement.Controller;

import com.example.TeslaManagement.model.TransactionCategory;
import com.example.TeslaManagement.service.TransactionCategoryService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/category")
public class TransactionCategoryController {

    @Autowired
    private TransactionCategoryService transactionCategoryService;

    @PostMapping
    public ResponseEntity<TransactionCategory> createCategory(@Valid @RequestBody TransactionCategory transactionCategoryDTO){
        TransactionCategory createdCategory = transactionCategoryService.createCategory( transactionCategoryDTO );
        return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TransactionCategory>> getAllCategory(){
        return new ResponseEntity<>(transactionCategoryService.getAllCategory(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionCategory> getCategoryId(@PathVariable("id") @NotNull @Positive(message = "Category ID must be positive") Long categoryId){
        return new ResponseEntity<>(transactionCategoryService.getCategoryById(categoryId), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TransactionCategory> updateCategory(@PathVariable("id") @NotNull @Positive(message = "Category ID must be positive") Long categoryId, @RequestBody TransactionCategory categoryRequest){
        return new ResponseEntity<>(transactionCategoryService.updateCategory(categoryId, categoryRequest), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TransactionCategory> deleteCategory(@PathVariable("id") @Positive(message = "Category ID must be positive") @NotNull Long categoryId){
        transactionCategoryService.deleteCategory(categoryId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

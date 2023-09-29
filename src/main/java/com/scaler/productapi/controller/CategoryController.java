package com.scaler.productapi.controller;

import com.scaler.productapi.models.Category;
import com.scaler.productapi.services.CategoryService;
import com.scaler.productapi.services.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import com.scaler.productapi.models.Product;

@RestController
@RequestMapping("/products/categories")
public class CategoryController {
    private  CategoryService CategoryService;
    private ProductService productService;
    public CategoryController(CategoryService CategoryService,ProductService productService){
        this.productService=productService;
        this.CategoryService=CategoryService;
    }

}

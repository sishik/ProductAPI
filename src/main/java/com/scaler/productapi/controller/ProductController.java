package com.scaler.productapi.controller;

import com.scaler.productapi.dtos.ProductDto;
import com.scaler.productapi.exceptions.NotFoundException;
import com.scaler.productapi.models.Category;
import com.scaler.productapi.models.Product;
import com.scaler.productapi.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;
    public ProductController(ProductService productService){
        this.productService=productService;
    }

    @GetMapping()
    public List<Product>getAllProducts(){
        return productService.getAllProducts();
    }
    @GetMapping("/categories/{category}")
    public List<Product>getAllProductsByCategory(@PathVariable("category") String category){
        return productService.getAllProductsByCategory(category);
    }
    @GetMapping("/{productId}")
    public ResponseEntity<Product> getSingleProduct(@PathVariable Long productId)throws NotFoundException{
        Optional<Product> productOptional = productService.getSingleProduct(productId);
        if(productOptional.isEmpty()){
            throw new NotFoundException("No Product with product id: " + productId);
        }
        ResponseEntity<Product> response = new ResponseEntity<>(productOptional.get(), HttpStatus.OK);
        return response;
    }
    @PatchMapping("/{productId}")
    public Product updateProduct(@PathVariable Long productId, @RequestBody ProductDto productDto){
        Product product = new Product();
        product.setId(productDto.getId());
        product.setTitle(productDto.getTitle());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setCategory(new Category());
        product.getCategory().setName(productDto.getCategory());
        product.setImageUrl(productDto.getImage());
        return productService.updateProduct(productId,product);
    }
    @PostMapping()
    public ResponseEntity<Product>addNewProduct(@RequestBody ProductDto productDto){

        Product newProduct = productService.addNewProduct(productDto);
        ResponseEntity<Product> response = new ResponseEntity<>(newProduct,HttpStatus.CREATED);
        return response;
    }
    @DeleteMapping("/{productId}")
    public String deleteProduct(@PathVariable("productId") Long productId) {
        return "Deleting a product with id: " + productId;
    }



}

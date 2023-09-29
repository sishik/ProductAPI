package com.scaler.productapi.services;

import com.scaler.productapi.dtos.ProductDto;
import com.scaler.productapi.exceptions.NotFoundException;
import com.scaler.productapi.models.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> getAllProducts();

    Optional<Product> getSingleProduct(Long productId) throws NotFoundException;

    Product addNewProduct(ProductDto product);

    /*
    Product object has only those fields filled which need to be updated.
    Everything else is null
     */
    Product updateProduct(Long productId, Product product);
    // if (product.getImageUrl() != null) {
    //
    // }

    boolean deleteProduct(Long productId);

    List<Product> getAllProductsByCategory(String category);
}

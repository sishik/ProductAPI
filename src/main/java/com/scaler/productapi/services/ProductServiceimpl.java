package com.scaler.productapi.services;

import com.scaler.productapi.clients.fakestoreApi.FakeStoreClient;
import com.scaler.productapi.clients.fakestoreApi.FakeStoreProductDto;
import com.scaler.productapi.dtos.ProductDto;
import com.scaler.productapi.exceptions.NotFoundException;
import com.scaler.productapi.models.Category;
import com.scaler.productapi.models.Product;
import jakarta.annotation.Nullable;
import lombok.Setter;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class ProductServiceimpl implements ProductService{
    private RestTemplateBuilder restTemplateBuilder;
    private FakeStoreClient fakeStoreClient;
    public ProductServiceimpl(RestTemplateBuilder restTemplateBuilder,FakeStoreClient fakeStoreClient){
        this.fakeStoreClient=fakeStoreClient;
        this.restTemplateBuilder=restTemplateBuilder;
    }
//    private <T>ResponseEntity<T>requestForEntity(HttpMethod httpMethod, String url, @Nullable Object request,Class<T>responseType,Object... uriVariables) {
//        RestTemplate restTemplate = restTemplateBuilder.requestFactory(HttpComponentsClientHttpRequestFactory.class).build();
//        RequestCallback requestCallback = restTemplate.httpEntityCallback(request, responseType);
//        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
//        return restTemplate.execute(url, httpMethod, requestCallback, responseExtractor, uriVariables);
//    }
    private Product convertFakeStoreProductDtotoProduct(FakeStoreProductDto fakeStoreProductDto){
        Product product = new Product();
        product.setId(fakeStoreProductDto.getId());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setPrice(fakeStoreProductDto.getPrice());
        Category category = new Category();
        category.setName(fakeStoreProductDto.getCategory());
        product.setCategory(category);
        product.setImageUrl(fakeStoreProductDto.getImage());
        return product;
    }
    @Override
    public List<Product> getAllProducts() {
        List<FakeStoreProductDto> lists = fakeStoreClient.getAllProducts();
        List<Product> list_of_products = new ArrayList<>();
        for(FakeStoreProductDto fakeStoreProductDto: lists){
            list_of_products.add(convertFakeStoreProductDtotoProduct(fakeStoreProductDto));
        }
        return list_of_products;
    }
    @Override
    public List<Product>getAllProductsByCategory(String categoryName){
        List<FakeStoreProductDto> lists = fakeStoreClient.getAllProducts();
        List<Product> list_of_products = new ArrayList<>();
        for(FakeStoreProductDto fakeStoreProductDto: lists){
            if(fakeStoreProductDto.getCategory().equals(categoryName)){
                list_of_products.add(convertFakeStoreProductDtotoProduct(fakeStoreProductDto));
            }
        }
        return list_of_products;
    }
    @Override
    public Optional<Product> getSingleProduct(Long productId) throws NotFoundException {
        FakeStoreProductDto fakeStoreProductDto = fakeStoreClient.getSingleProduct(productId);

        if (fakeStoreProductDto == null) {
            throw new NotFoundException("There is no Product with Id" + productId);
        }

        // Convert FakeStoreProductDto to Product
        Product product = convertFakeStoreProductDtotoProduct(fakeStoreProductDto);

        return Optional.of(product);
    }

    @Override
    public Product addNewProduct(ProductDto product) {
        FakeStoreProductDto fakeStoreProductDto = fakeStoreClient.addNewProduct(product);
        return convertFakeStoreProductDtotoProduct(fakeStoreProductDto);
    }

    @Override
    public Product updateProduct(Long productId, Product product) {
        FakeStoreProductDto fakeStoreProductDto = fakeStoreClient.updateProduct(productId, product);
        return convertFakeStoreProductDtotoProduct(fakeStoreProductDto);

    }

    @Override
    public Product replaceProduct(Long productId, Product product) {
        return null;
    }

    @Override
    public boolean deleteProduct(Long productId) {
        return false;
    }
}

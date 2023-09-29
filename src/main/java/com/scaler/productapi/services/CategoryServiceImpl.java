package com.scaler.productapi.services;

import com.scaler.productapi.clients.fakestoreApi.FakeStoreClient;
import com.scaler.productapi.clients.fakestoreApi.FakeStoreProductDto;
import com.scaler.productapi.models.Category;
import lombok.Setter;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.ArrayList;
@Service
public class CategoryServiceImpl implements CategoryService{
    private RestTemplateBuilder restTemplateBuilder;
    private FakeStoreClient fakeStoreClient;
    public CategoryServiceImpl(RestTemplateBuilder restTemplateBuilder,FakeStoreClient fakeStoreClient) {
        this.restTemplateBuilder = restTemplateBuilder;
        this.fakeStoreClient = fakeStoreClient;
    }
    @Override
    public List<Category> getAllCategories() {
        List<FakeStoreProductDto> fakeStoreProductDtos = fakeStoreClient.getAllProducts();
        List<Category> categories = new ArrayList<>();
        for(FakeStoreProductDto fakeStoreProductDto: fakeStoreProductDtos){
            Category category = new Category();
            category.setName(fakeStoreProductDto.getCategory());
            categories.add(category);
        }
        return categories;
    }
}

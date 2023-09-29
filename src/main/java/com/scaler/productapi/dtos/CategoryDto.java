package com.scaler.productapi.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CategoryDto {
    private Long id;
    private String title;
    private String description;
    private double price;
    private String image;
    private String category;
}

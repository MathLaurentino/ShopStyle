package com.shopstyle.bff_shop.service;

import com.shopstyle.bff_shop.web.dto.ms_catalog.CategoryTreeDto;
import com.shopstyle.bff_shop.web.dto.ms_catalog.ProductGetDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "ms-catalog", path = "/v1")
public interface CatalogService {

    @GetMapping("/products")
    ResponseEntity<List<ProductGetDto>> getAllProduct();

    @GetMapping("/products/{id}")
    ResponseEntity<ProductGetDto> getProduct(@PathVariable Long id);


    @GetMapping("/category")
    ResponseEntity<List<CategoryTreeDto>> getAllCategoriesAsTree();

}

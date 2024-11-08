package com.shopstyle.bff_shop.web.controller;

import com.shopstyle.bff_shop.service.CatalogService;
import com.shopstyle.bff_shop.web.dto.ms_catalog.ProductGetDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1")
public class CatalogController {

    private final CatalogService service;

    @GetMapping("/products")
    ResponseEntity<List<ProductGetDto>> getAllProduct(){
        return service.getAllProduct();
    }

    @GetMapping("/products/{id}")
    ResponseEntity<ProductGetDto> getProduct(@PathVariable Long id){
        return service.getProduct(id);
    }

}

package com.shopstyle.ms_catalog.web.controller;

import com.shopstyle.ms_catalog.web.dto.ProductGetDto;
import com.shopstyle.ms_catalog.web.dto.ProductReqDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductController {

    ResponseEntity<ProductGetDto> createProduct(ProductReqDto dto);

    ResponseEntity<ProductGetDto> getProduct(Long id);

    ResponseEntity<List<ProductGetDto>> getAllProduct();

    ResponseEntity<ProductGetDto> updateProduct(ProductReqDto dto, Long id);

    ResponseEntity<Void> deleteProduct(Long id);

}

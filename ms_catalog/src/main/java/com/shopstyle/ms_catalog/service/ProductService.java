package com.shopstyle.ms_catalog.service;

import com.shopstyle.ms_catalog.web.dto.ProductGetDto;
import com.shopstyle.ms_catalog.web.dto.ProductReqDto;

import java.util.List;

public interface ProductService {

    ProductGetDto createProduct(ProductReqDto dto);

    ProductGetDto getProduct(Long id);

    List<ProductGetDto> getAllProduct();

    ProductGetDto updateProduct(ProductReqDto dto, Long id);

    void deleteProduct(Long id);

}

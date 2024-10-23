package com.shopstyle.ms_catalog.web.dto.mapper;

import com.shopstyle.ms_catalog.entity.Product;
import com.shopstyle.ms_catalog.web.dto.ProductGetDto;
import com.shopstyle.ms_catalog.web.dto.ProductReqDto;
import org.modelmapper.ModelMapper;

public class ProductMapper {

    public static Product toProduct(ProductReqDto dto) {
        return new ModelMapper().map(dto, Product.class);
    }

    public static ProductGetDto toDto(Product product) {
        return new ModelMapper().map(product, ProductGetDto.class);
    }

}

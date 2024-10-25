package com.shopstyle.ms_catalog.web.dto.mapper;

import com.shopstyle.ms_catalog.entity.Product;
import com.shopstyle.ms_catalog.web.dto.ProductGetDto;
import com.shopstyle.ms_catalog.web.dto.ProductReqDto;
import com.shopstyle.ms_catalog.web.dto.SkuGetDto;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class ProductMapper {

    public static Product toProduct(ProductReqDto dto) {
        return new ModelMapper().map(dto, Product.class);
    }

    public static ProductGetDto toDto(Product product) {
        ProductGetDto getDto = new ProductGetDto();
        getDto.setId(product.getId());
        getDto.setName(product.getName());
        getDto.setDescription(product.getDescription());
        getDto.setBrand(product.getBrand());
        getDto.setMaterial(product.getMaterial());
        getDto.setActive(product.getActive());

        List<SkuGetDto> skuGetDtoList =  product.getSkus().stream()
                .map(SkuMapper::toDto)
                .collect(Collectors.toList());

        getDto.setSkus(skuGetDtoList);
        return getDto;
    }

}

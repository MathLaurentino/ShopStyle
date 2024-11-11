package com.shopstyle.bff_shop.web.dto.ms_catalog;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductGetDto {

    private Long id;
    private String name;
    private String description;
    private String brand;
    private String material;
    private Boolean active;
    private List<SkuGetDto> skus;
}
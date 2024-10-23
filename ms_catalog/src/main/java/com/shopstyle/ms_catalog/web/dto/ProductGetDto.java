package com.shopstyle.ms_catalog.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

}

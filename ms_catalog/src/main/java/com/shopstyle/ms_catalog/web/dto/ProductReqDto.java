package com.shopstyle.ms_catalog.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class ProductReqDto {

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @NotBlank
    private String brand;

    @NotBlank
    private String material;

    @NotBlank
    @Pattern(regexp = "true|false")
    private String active;

    @NotNull
    @Min(1)
    @JsonProperty("categoryId")
    private Long category;

}

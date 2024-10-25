package com.shopstyle.ms_catalog.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductReqDto {

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @NotBlank
    private String brand;

    private String material;

    @NotBlank
    @Pattern(regexp = "true|false")
    private String active;

    @NotNull
    @Min(1)
    @JsonProperty("categoryId")
    private Long category;

}

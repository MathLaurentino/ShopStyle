package com.shopstyle.ms_catalog.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SkuPutDto {

    @NotNull
    @Positive
    private BigDecimal price;

    @NotNull
    @Positive
    private Integer quantity;

    @NotBlank
    private String color;

    @NotNull
    @Positive
    private Float size;

    @NotNull
    @Positive
    private Float height;

    @NotNull
    @Positive
    private Float width;

    @NotBlank
    private Set<@NotBlank String> images;

}

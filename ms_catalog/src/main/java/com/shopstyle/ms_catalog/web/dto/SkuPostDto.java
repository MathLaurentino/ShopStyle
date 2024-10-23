package com.shopstyle.ms_catalog.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SkuPostDto {

    @NotNull
    @Positive
    private BigDecimal price;

    @NotNull
    @Positive
    private Integer quantity;

    @NotBlank(message = "Color is required.")
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

    @NotNull
    private List<@NotBlank String> images;

    @NotNull(message = "ProductId is required.")
    @JsonProperty("productId")
    private Long product;

}

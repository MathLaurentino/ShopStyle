package com.shopstyle.ms_catalog.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class SkuGetDto {

    private Long id;
    private BigDecimal price;
    private Integer quantity;
    private String color;
    private Float size;
    private Float height;
    private Float width;
    private Set<MediaGetDto> medias;
    @JsonProperty("productId")
    private Long product;

}

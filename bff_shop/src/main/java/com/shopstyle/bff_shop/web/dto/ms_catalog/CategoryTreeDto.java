package com.shopstyle.bff_shop.web.dto.ms_catalog;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class CategoryTreeDto {
    private Long id;
    private String name;
    private boolean active;
    private List<CategoryTreeDto> children;
}
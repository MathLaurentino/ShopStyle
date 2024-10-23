package com.shopstyle.ms_catalog.web.dto;

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

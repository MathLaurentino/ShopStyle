package com.shopstyle.ms_catalog.web.dto.mapper;

import com.shopstyle.ms_catalog.entity.Category;
import com.shopstyle.ms_catalog.web.dto.CategoryGetDto;
import com.shopstyle.ms_catalog.web.dto.CategoryReqDto;
import com.shopstyle.ms_catalog.web.dto.CategoryTreeDto;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class CategoryMapper {

    public static CategoryTreeDto toTreeDto(Category category) {
        CategoryTreeDto dto = new CategoryTreeDto();
        dto.setId(category.getId());
        dto.setName(category.getName());
        dto.setActive(category.getActive());

        List<CategoryTreeDto> subcategoryDtos = category.getSubcategories().stream()
            .map(CategoryMapper::toTreeDto)
            .collect(Collectors.toList());
        
        dto.setSubcategories(subcategoryDtos);
        return dto;
    }

    public static Category toCategory(CategoryReqDto dto) {
        return new ModelMapper().map(dto, Category.class);
    }

    public static CategoryGetDto toDto(Category category) {
        var dto = new CategoryGetDto();

        dto.setId(category.getId());
        dto.setName(category.getName());
        dto.setActive(category.getActive());
        if (category.getParentCategory() != null) {
            dto.setParentCategory(category.getParentCategory().getId());
        }

        return dto;
    }

}

package com.shopstyle.ms_catalog.service;

import com.shopstyle.ms_catalog.web.dto.CategoryGetDto;
import com.shopstyle.ms_catalog.web.dto.CategoryReqDto;
import com.shopstyle.ms_catalog.web.dto.CategoryTreeDto;

import java.util.List;

public interface CategoryService {

    CategoryGetDto createCategory (CategoryReqDto dto);

    CategoryGetDto updateCategory (CategoryReqDto dto);

    List<CategoryTreeDto> getAllCategoriesAsTree();

    Void deleteCategoryById(Long id);

}

package com.shopstyle.ms_catalog.web.controller;

import com.shopstyle.ms_catalog.web.dto.CategoryGetDto;
import com.shopstyle.ms_catalog.web.dto.CategoryReqDto;
import com.shopstyle.ms_catalog.web.dto.CategoryTreeDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CategoryController {

    ResponseEntity<CategoryGetDto> createCategory (CategoryReqDto dto);

    ResponseEntity<CategoryGetDto> updateCategory (CategoryReqDto dto);

    ResponseEntity<List<CategoryTreeDto>> getAllCategoriesAsTree();

    ResponseEntity<Void> deleteCategoryById(Long id);

}

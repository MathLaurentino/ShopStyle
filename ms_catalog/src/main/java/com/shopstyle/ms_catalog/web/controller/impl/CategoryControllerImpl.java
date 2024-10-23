package com.shopstyle.ms_catalog.web.controller.impl;

import com.shopstyle.ms_catalog.service.CategoryService;
import com.shopstyle.ms_catalog.web.controller.CategoryController;
import com.shopstyle.ms_catalog.web.dto.CategoryGetDto;
import com.shopstyle.ms_catalog.web.dto.CategoryReqDto;
import com.shopstyle.ms_catalog.web.dto.CategoryTreeDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("v1/category")
@RestController
public class CategoryControllerImpl implements CategoryController {

    private final CategoryService service;

    @Override
    @PostMapping
    public ResponseEntity<CategoryGetDto> createCategory(@Valid @RequestBody CategoryReqDto dto) {
        var categoryDto = service.createCategory(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryDto);
    }

    @Override
    public ResponseEntity<CategoryGetDto> updateCategory(@Valid @RequestBody CategoryReqDto dto) {
        return null;
    }

    @Override
    @GetMapping
    public ResponseEntity<List<CategoryTreeDto>> getAllCategoriesAsTree() {
        var dto = service.getAllCategoriesAsTree();
        return ResponseEntity.ok(dto);
    }

    @Override
    public ResponseEntity<Void> deleteCategoryById(@PathVariable Long id) {
        return null;
    }

}

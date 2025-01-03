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
    @PutMapping("/{id}")
    public ResponseEntity<CategoryTreeDto> updateCategory(@Valid @RequestBody CategoryReqDto dto, @PathVariable Long id) {
        var treeDto = service.updateCategory(dto, id);
        return ResponseEntity.ok(treeDto);
    }

    @Override
    @GetMapping
    public ResponseEntity<List<CategoryTreeDto>> getAllCategoriesAsTree() {
        var dto = service.getAllCategoriesAsTree();
        return ResponseEntity.ok(dto);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategoryById(@PathVariable Long id) {
        service.deleteCategoryById(id);
        return ResponseEntity.noContent().build();
    }

}

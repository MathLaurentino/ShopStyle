package com.shopstyle.ms_catalog.service.impl;

import com.shopstyle.ms_catalog.entity.Category;
import com.shopstyle.ms_catalog.exception.EntityNotFoundException;
import com.shopstyle.ms_catalog.repository.CategoryRepository;
import com.shopstyle.ms_catalog.service.CategoryService;
import com.shopstyle.ms_catalog.web.dto.CategoryGetDto;
import com.shopstyle.ms_catalog.web.dto.CategoryReqDto;
import com.shopstyle.ms_catalog.web.dto.CategoryTreeDto;
import com.shopstyle.ms_catalog.web.dto.mapper.CategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repository;

    @Override
    public CategoryGetDto createCategory(CategoryReqDto dto) {
        var category = CategoryMapper.toCategory(dto);

        if (dto.getParentCategory() != null) {
            var parentCategory = repository.findById(dto.getParentCategory()).orElseThrow(
                    () -> new EntityNotFoundException("Category not found"));
            category.setParentCategory(parentCategory);
        }

        return CategoryMapper.toDto(repository.save(category));
    }

    // TODO
    @Override
    public CategoryGetDto updateCategory(CategoryReqDto dto) {
        return null;
    }

    @Override
    public List<CategoryTreeDto> getAllCategoriesAsTree() {
        List<Category> rootCategories = repository.findByParentCategoryIsNull();

        return rootCategories.stream()
                .map(CategoryMapper::toTreeDto)
                .collect(Collectors.toList());
    }

    // TODO
    @Override
    public Void deleteCategoryById(Long id) {
        return null;
    }

}

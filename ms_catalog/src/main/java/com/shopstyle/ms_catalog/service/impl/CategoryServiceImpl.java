package com.shopstyle.ms_catalog.service.impl;

import com.shopstyle.ms_catalog.entity.Category;
import com.shopstyle.ms_catalog.exception.CategoryHasChildrenException;
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

    @Override
    public CategoryTreeDto updateCategory(CategoryReqDto dto, Long id) {
        var category = repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Category not found"));

        if (dto.getParentCategory() != null) {
            var parentCategory = repository.findById(dto.getParentCategory()).orElseThrow(
                    () -> new EntityNotFoundException("Category not found"));
            category.setParentCategory(parentCategory);
        }
        category.setName(dto.getName());
        category.setActive(dto.getActive().equals("true"));

        return CategoryMapper.toTreeDto(repository.save(category));
    }

    @Override
    public List<CategoryTreeDto> getAllCategoriesAsTree() {
        List<Category> rootCategories = repository.findByParentCategoryIsNull();

        return rootCategories.stream()
                .map(CategoryMapper::toTreeDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteCategoryById(Long id) {
        Category category = repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Category not found"));

        if (!category.getSubcategories().isEmpty()) {
            String subcategoryNames = category.getSubcategories().stream()
                    .map(Category::getName)
                    .collect(Collectors.joining(", "));

            throw new CategoryHasChildrenException("Category has subcategories: " + subcategoryNames +
                    ". You need to delete them first.");
        }

        repository.deleteById(id);
    }

}

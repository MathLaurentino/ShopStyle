package com.shopstyle.ms_catalog.service.impl;

import com.shopstyle.ms_catalog.entity.Category;
import com.shopstyle.ms_catalog.entity.Product;
import com.shopstyle.ms_catalog.exception.CategoryHasChildrenException;
import com.shopstyle.ms_catalog.exception.EntityNotFoundException;
import com.shopstyle.ms_catalog.repository.CategoryRepository;
import com.shopstyle.ms_catalog.repository.ProductRepository;
import com.shopstyle.ms_catalog.service.ProductService;
import com.shopstyle.ms_catalog.web.dto.ProductGetDto;
import com.shopstyle.ms_catalog.web.dto.ProductReqDto;
import com.shopstyle.ms_catalog.web.dto.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public ProductGetDto createProduct(ProductReqDto dto) {
        Category category = categoryRepository.findById(dto.getCategory()).orElseThrow(
                () -> new EntityNotFoundException("Category not found"));

        if (!category.getSubcategories().isEmpty()) {
            throw new CategoryHasChildrenException("Product cannot be added to category '" + category.getName() +
                    "' because it has subcategories.");
        }

        var product = ProductMapper.toProduct(dto);
        product.setCategory(category);

        return ProductMapper.toDto(productRepository.save(product));
    }

    @Override
    public ProductGetDto getProduct(Long id) {
        Product product = productRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Product not found"));

        return ProductMapper.toDto(product);
    }

    @Override
    public List<ProductGetDto> getAllProduct() {
        return List.of();
    }

    @Override
    public ProductGetDto updateProduct(ProductReqDto dto, Long id) {
        return null;
    }

    @Override
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Product not found"));

        productRepository.deleteById(id);
    }

}

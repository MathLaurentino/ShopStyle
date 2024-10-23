package com.shopstyle.ms_catalog.web.controller.impl;

import com.shopstyle.ms_catalog.service.ProductService;
import com.shopstyle.ms_catalog.web.controller.ProductController;
import com.shopstyle.ms_catalog.web.dto.ProductGetDto;
import com.shopstyle.ms_catalog.web.dto.ProductReqDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("v1/products")
@RestController
public class ProductControllerImpl implements ProductController {

    private final ProductService service;

    @Override
    @PostMapping
    public ResponseEntity<ProductGetDto> createProduct(@Valid @RequestBody ProductReqDto dto) {
        var getDto = service.createProduct(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(getDto);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<ProductGetDto> getProduct(@PathVariable Long id) {
        var getDto = service.getProduct(id);
        return ResponseEntity.ok(getDto);
    }

    //TODO
    @Override
    @GetMapping
    public ResponseEntity<List<ProductGetDto>> getAllProduct() {
        return null;
    }

    //TODO
    @Override
    @PutMapping("/{id}")
    public ResponseEntity<ProductGetDto> updateProduct(@Valid @RequestBody ProductReqDto dto, @PathVariable Long id) {
        return null;
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        service.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

}

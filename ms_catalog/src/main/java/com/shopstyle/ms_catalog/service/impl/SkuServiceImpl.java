package com.shopstyle.ms_catalog.service.impl;

import com.shopstyle.ms_catalog.entity.Product;
import com.shopstyle.ms_catalog.entity.Sku;
import com.shopstyle.ms_catalog.exception.EntityNotFoundException;
import com.shopstyle.ms_catalog.repository.ProductRepository;
import com.shopstyle.ms_catalog.repository.SkuRepository;
import com.shopstyle.ms_catalog.service.SkuService;
import com.shopstyle.ms_catalog.web.dto.SkuGetDto;
import com.shopstyle.ms_catalog.web.dto.SkuPostDto;
import com.shopstyle.ms_catalog.web.dto.SkuPutDto;
import com.shopstyle.ms_catalog.web.dto.mapper.SkuMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SkuServiceImpl implements SkuService {

    private final SkuRepository skuRepository;
    private final ProductRepository productRepository;

    @Override
    public SkuGetDto createSku(SkuPostDto dto) {
        Product product = productRepository.findById(dto.getProduct()).orElseThrow(
                () -> new EntityNotFoundException("Product not found"));

        Sku sku = SkuMapper.toSku(dto);
        sku.setProduct(product);

        return SkuMapper.toDto(skuRepository.save(sku));
    }

    //TODO
    @Override
    public SkuGetDto updateSku(SkuPutDto dto) {
        return null;
    }

    @Override
    public void deleteSku(Long id) {
        skuRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Sku not found"));

        skuRepository.deleteById(id);
    }

}

package com.shopstyle.ms_catalog.service.impl;

import com.shopstyle.ms_catalog.entity.Media;
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

import java.util.List;
import java.util.stream.Collectors;

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


    @Override
    public SkuGetDto updateSku(SkuPutDto dto, Long id) {
        var sku = skuRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Sku not found"));

        sku.setPrice(dto.getPrice());
        sku.setQuantity(dto.getQuantity());
        sku.setColor(dto.getColor());
        sku.setSize(dto.getSize());
        sku.setHeight(dto.getHeight());
        sku.setWidth(dto.getWidth());
        sku.getMedias().clear();

        List<Media> newMedias = dto.getImages().stream()
                .map(imageUrl -> {
                    Media media = new Media();
                    media.setImageUrl(imageUrl);
                    media.setSku(sku);
                    return media;
                })
                .collect(Collectors.toList());
        sku.getMedias().addAll(newMedias);

        return SkuMapper.toDto(skuRepository.save(sku));
    }

    @Override
    public SkuGetDto getSkuById(Long id) {
        Sku sku = skuRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Sku not found"));

        return SkuMapper.toDto(sku);
    }

    @Override
    public void deleteSku(Long id) {
        skuRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Sku not found"));

        skuRepository.deleteById(id);
    }

}

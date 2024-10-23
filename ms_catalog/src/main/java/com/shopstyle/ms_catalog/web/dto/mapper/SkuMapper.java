package com.shopstyle.ms_catalog.web.dto.mapper;

import com.shopstyle.ms_catalog.entity.Sku;
import com.shopstyle.ms_catalog.web.dto.*;
import org.modelmapper.ModelMapper;

import java.util.stream.Collectors;

public class SkuMapper {

    public static Sku toSku(SkuPostDto dto) {
        return new ModelMapper().map(dto, Sku.class);
    }

    public static Sku toSku(SkuPutDto dto) {
        return new ModelMapper().map(dto, Sku.class);
    }

    public static SkuGetDto toDto(Sku sku) {
        SkuGetDto getDto = new SkuGetDto();
        getDto.setId(sku.getId());
        getDto.setPrice(sku.getPrice());
        getDto.setQuantity(sku.getQuantity());
        getDto.setColor(sku.getColor());
        getDto.setSize(sku.getSize());
        getDto.setHeight(sku.getHeight());
        getDto.setWidth(sku.getWidth());

        if (!sku.getMedias().isEmpty()) {
             var mediaGetDtos = sku.getMedias().stream().map(MediaMapper::toDto).collect(Collectors.toSet());
            getDto.setMedias(mediaGetDtos);
        }

        getDto.setProduct(sku.getProduct().getId());

        return getDto;
    }

}

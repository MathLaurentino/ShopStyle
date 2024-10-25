package com.shopstyle.ms_catalog.web.dto.mapper;

import com.shopstyle.ms_catalog.entity.Media;
import com.shopstyle.ms_catalog.entity.Sku;
import com.shopstyle.ms_catalog.web.dto.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class SkuMapper {


    public static Sku toSku(SkuPostDto dto) {
        Sku sku = new Sku();
        mapCommonSkuFields(sku, dto.getPrice(), dto.getQuantity(), dto.getColor(), dto.getSize(), dto.getHeight(), dto.getWidth(), dto.getImages());
        return sku;
    }


    public static Sku toSku(SkuPutDto dto) {
        Sku sku = new Sku();
        mapCommonSkuFields(sku, dto.getPrice(), dto.getQuantity(), dto.getColor(), dto.getSize(), dto.getHeight(), dto.getWidth(), dto.getImages());
        return sku;
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

    private static List<Media> mapImagesToMediaSet(List<String> images, Sku sku) {
        return images.stream()
                .map(imageUrl -> {
                    Media media = new Media();
                    media.setImageUrl(imageUrl);
                    media.setSku(sku);
                    return media;
                })
                .collect(Collectors.toList());
    }

    private static void mapCommonSkuFields(Sku sku, BigDecimal price, Integer quantity, String color, Float size, Float height, Float width, List<String> images) {
        sku.setPrice(price);
        sku.setQuantity(quantity);
        sku.setColor(color);
        sku.setSize(size);
        sku.setHeight(height);
        sku.setWidth(width);

        List<Media> mediaSet = mapImagesToMediaSet(images, sku);
        sku.setMedias(mediaSet);
    }

}

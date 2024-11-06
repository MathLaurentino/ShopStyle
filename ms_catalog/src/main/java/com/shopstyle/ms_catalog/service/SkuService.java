package com.shopstyle.ms_catalog.service;

import com.shopstyle.ms_catalog.web.dto.SkuGetDto;
import com.shopstyle.ms_catalog.web.dto.SkuPostDto;
import com.shopstyle.ms_catalog.web.dto.SkuPutDto;
import com.shopstyle.ms_catalog.web.dto.kafka.SkuDto;

import java.util.List;

public interface SkuService {

    SkuGetDto createSku(SkuPostDto dto);

    SkuGetDto updateSku(SkuPutDto dto, Long id);

    SkuGetDto getSkuById(Long id);

    void decreaseSkuQuantity(List<SkuDto> skuDtos);

    void deleteSku(Long id);

}

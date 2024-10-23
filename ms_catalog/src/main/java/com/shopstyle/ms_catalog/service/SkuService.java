package com.shopstyle.ms_catalog.service;

import com.shopstyle.ms_catalog.web.dto.SkuGetDto;
import com.shopstyle.ms_catalog.web.dto.SkuPostDto;
import com.shopstyle.ms_catalog.web.dto.SkuPutDto;

public interface SkuService {

    SkuGetDto createSku(SkuPostDto dto);

    SkuGetDto updateSku(SkuPutDto dto, Long id);

    void deleteSku(Long id);

}

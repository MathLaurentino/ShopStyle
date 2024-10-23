package com.shopstyle.ms_catalog.web.controller;

import com.shopstyle.ms_catalog.web.dto.SkuGetDto;
import com.shopstyle.ms_catalog.web.dto.SkuPostDto;
import com.shopstyle.ms_catalog.web.dto.SkuPutDto;
import org.springframework.http.ResponseEntity;

public interface SkuController {

    ResponseEntity<SkuGetDto> createSku(SkuPostDto dto);

    ResponseEntity<SkuGetDto> updateSku(SkuPutDto dto);

    ResponseEntity<Void> deleteSku(Long id);

}

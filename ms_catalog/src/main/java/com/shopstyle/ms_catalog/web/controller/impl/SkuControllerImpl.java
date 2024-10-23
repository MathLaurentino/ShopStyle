package com.shopstyle.ms_catalog.web.controller.impl;

import com.shopstyle.ms_catalog.service.SkuService;
import com.shopstyle.ms_catalog.web.controller.SkuController;
import com.shopstyle.ms_catalog.web.dto.SkuGetDto;
import com.shopstyle.ms_catalog.web.dto.SkuPostDto;
import com.shopstyle.ms_catalog.web.dto.SkuPutDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("v1/skus")
@RestController
public class SkuControllerImpl implements SkuController {

    private final SkuService service;

    @Override
    @PostMapping
    public ResponseEntity<SkuGetDto> createSku(@Valid @RequestBody SkuPostDto dto) {
        var getDto = service.createSku(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(getDto);
    }

    //TODO
    @Override
    public ResponseEntity<SkuGetDto> updateSku(@Valid @RequestBody SkuPutDto dto) {
        return null;
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSku(@PathVariable Long id) {
        service.deleteSku(id);
        return ResponseEntity.noContent().build();
    }

}

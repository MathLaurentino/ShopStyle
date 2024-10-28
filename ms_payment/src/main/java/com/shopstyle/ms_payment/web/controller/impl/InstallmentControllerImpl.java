package com.shopstyle.ms_payment.web.controller.impl;

import com.shopstyle.ms_payment.service.InstallmentService;
import com.shopstyle.ms_payment.web.controller.InstallmentController;
import com.shopstyle.ms_payment.web.dto.InstallmentGetDto;
import com.shopstyle.ms_payment.web.dto.InstallmentReqDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("v1/installments")
@RestController
public class InstallmentControllerImpl implements InstallmentController {

    private final InstallmentService service;

    @Override
    @PostMapping
    public ResponseEntity<InstallmentGetDto> createInstallment(@Valid @RequestBody InstallmentReqDto dto) {
        var getDto = service.createInstallment(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(getDto);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<InstallmentGetDto> updateInstallment(@Valid @RequestBody InstallmentReqDto dto, @PathVariable Long id) {
        var getDto = service.updateInstallment(dto, id);
        return ResponseEntity.ok(getDto);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInstallment(@PathVariable Long id) {
        return null;
    }

}

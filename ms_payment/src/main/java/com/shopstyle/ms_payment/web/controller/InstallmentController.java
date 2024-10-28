package com.shopstyle.ms_payment.web.controller;

import com.shopstyle.ms_payment.web.dto.InstallmentGetDto;
import com.shopstyle.ms_payment.web.dto.InstallmentReqDto;
import org.springframework.http.ResponseEntity;

public interface InstallmentController {

    ResponseEntity<InstallmentGetDto> createInstallment(InstallmentReqDto dto);

    ResponseEntity<InstallmentGetDto> updateInstallment(InstallmentReqDto dto, Long id);

    ResponseEntity<Void> deleteInstallment(Long id);

}

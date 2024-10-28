package com.shopstyle.ms_payment.service;

import com.shopstyle.ms_payment.web.dto.InstallmentGetDto;
import com.shopstyle.ms_payment.web.dto.InstallmentReqDto;

public interface InstallmentService {

    InstallmentGetDto createInstallment(InstallmentReqDto dto);

    InstallmentGetDto updateInstallment(InstallmentReqDto dto, Long id);

    void deleteInstallment(Long id);

}

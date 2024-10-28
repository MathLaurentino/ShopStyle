package com.shopstyle.ms_payment.web.dto.mapper;

import com.shopstyle.ms_payment.entity.Installment;
import com.shopstyle.ms_payment.web.dto.InstallmentGetDto;
import com.shopstyle.ms_payment.web.dto.InstallmentReqDto;
import org.modelmapper.ModelMapper;

public class InstallmentMapper {

    public static Installment toInstallment(InstallmentReqDto dto) {
        Installment installment = new Installment();

        installment.setAmount(dto.getAmount());
        if (dto.getBrand() != null) {
            installment.setBrand(dto.getBrand());
        }

        return installment;
    }

    public static InstallmentGetDto toDto(Installment installment) {
        return new ModelMapper().map(installment, InstallmentGetDto.class);
    }

}

package com.shopstyle.bff_shop.web.dto.ms_payment;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Installment implements Serializable {

    private Long id;
    private Integer amount;
    private String brand;

}
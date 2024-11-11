package com.shopstyle.bff_shop.web.dto.ms_customer;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerGetDto implements Serializable {

    private Long id;
    private String cpf;
    private String firstName;
    private String lastName;
    private Sex sex;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate birthdate;
    private String email;
    private boolean active;
    private Set<Address> addresses;

}
package com.shopstyle.ms_customer.web.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerPutDto {

    @NotBlank
    @CPF
    private String cpf;

    @Size(min = 3)
    private String firstName;

    @Size(min = 3)
    private String lastName;

    @NotBlank
    @Pattern(regexp = "Feminino|Masculino")
    private String sex;

    @NotNull
    private LocalDate birthdate;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Pattern(regexp = "true|false")
    private String active;

}

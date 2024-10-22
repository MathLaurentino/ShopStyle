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
public class CustomerPostDto {

    @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}", message = "O CPF deve estar no formato xxx.xxx.xxx-xx")
    @NotBlank
    @CPF
    private String cpf;

    @NotBlank
    @Size(min = 3)
    private String firstName;

    @NotBlank
    @Size(min = 3)
    private String lastName;

    @NotBlank
    @Pattern(regexp = "Feminino|Masculino")
    private String sex;

    @NotNull
    @Past
    private LocalDate birthdate;

    @NotBlank
    @Email
    private String email;

    @Size(min = 6)
    @NotBlank
    private String password;

    @NotBlank
    @Pattern(regexp = "true|false")
    private String active;

}

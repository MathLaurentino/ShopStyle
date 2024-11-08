package com.shopstyle.ms_customer.web.controller;

import com.shopstyle.ms_customer.jwt.JwtToken;
import com.shopstyle.ms_customer.web.dto.CustomerLoginDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;

public interface LoginController {

    ResponseEntity<JwtToken> autenticar(CustomerLoginDto dto);

}

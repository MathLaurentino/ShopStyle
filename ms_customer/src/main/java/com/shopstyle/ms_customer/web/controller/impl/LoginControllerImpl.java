package com.shopstyle.ms_customer.web.controller.impl;

import com.shopstyle.ms_customer.jwt.JwtToken;
import com.shopstyle.ms_customer.service.AuthenticationService;
import com.shopstyle.ms_customer.web.controller.LoginController;
import com.shopstyle.ms_customer.web.dto.CustomerLoginDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("v1/login")
@RestController
public class LoginControllerImpl implements LoginController {

    private final AuthenticationService authenticationService;

    @Override
    @PostMapping
    public ResponseEntity<JwtToken> autenticar(@RequestBody @Valid CustomerLoginDto dto) {
        return ResponseEntity.ok(authenticationService.autenticar(dto));
    }

}

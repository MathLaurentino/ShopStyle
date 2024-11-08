package com.shopstyle.ms_customer.service.impl;

import com.shopstyle.ms_customer.exception.InvalidCredencialException;
import com.shopstyle.ms_customer.jwt.JwtToken;
import com.shopstyle.ms_customer.jwt.JwtUserDetailsService;
import com.shopstyle.ms_customer.service.AuthenticationService;
import com.shopstyle.ms_customer.web.dto.CustomerLoginDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final JwtUserDetailsService detailsService;
    private final AuthenticationManager authenticationManager;

    @Override
    public JwtToken autenticar(CustomerLoginDto dto) {
        try {
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword());

            authenticationManager.authenticate(authenticationToken);

            return detailsService.getTokenAuthenticated(dto.getEmail());
        } catch (AuthenticationException ex) {
            throw new InvalidCredencialException(dto.getEmail());
        }
    }

}

package com.shopstyle.ms_customer.service;

import com.shopstyle.ms_customer.jwt.JwtToken;
import com.shopstyle.ms_customer.web.dto.CustomerLoginDto;

public interface AuthenticationService {

    public JwtToken autenticar(CustomerLoginDto dto);

}

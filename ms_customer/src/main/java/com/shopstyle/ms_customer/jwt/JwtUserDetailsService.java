package com.shopstyle.ms_customer.jwt;

import com.shopstyle.ms_customer.entity.Customer;
import com.shopstyle.ms_customer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class JwtUserDetailsService implements UserDetailsService {

    private final CustomerService customerService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = customerService.getCustomerByEmail(username);
        return new JwtUserDetails(customer);
    }

    public JwtToken getTokenAuthenticated(String username) {
        return JwtUtils.createToken(username);
    }

}

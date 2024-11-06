package com.shopstyle.ms_order.servece.impl;

import com.shopstyle.ms_order.exception.CustomerInactiveException;
import com.shopstyle.ms_order.exception.EntityNotFoundException;
import com.shopstyle.ms_order.exception.ErrorMicroServiceComunicationException;
import com.shopstyle.ms_order.exception.InvalidAddressException;
import com.shopstyle.ms_order.servece.CustomerFeignService;
import com.shopstyle.ms_order.servece.CustomerService;
import com.shopstyle.ms_order.web.dto.OrderReqDto;
import com.shopstyle.ms_order.web.dto.feign.CustomerDtoFeign;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerFeignService customerFeignService;

    @Override
    public Boolean customerDataIsValid(OrderReqDto dto) {
        try {
            ResponseEntity<CustomerDtoFeign> responseEntity = customerFeignService.findCustomerById(dto.getCustomer().getId());
            CustomerDtoFeign customer = responseEntity.getBody();

            assert customer != null;
            if (!customer.getActive()) {
                throw new CustomerInactiveException("Customer is inactive");
            }
            if (customer.getAddresses().stream().noneMatch(address -> address.getId().equals(dto.getCustomer().getAddressId()))) {
                throw new InvalidAddressException("Invalid Address id");
            }

            return true;
        } catch (FeignException.FeignClientException e){
            int status = e.status();
            if(HttpStatus.NOT_FOUND.value() == status){
                throw new EntityNotFoundException("Customer not found");
            }
            throw new ErrorMicroServiceComunicationException(e.getMessage(), status);
        }
    }

}

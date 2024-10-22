package com.shopstyle.ms_customer.web.controller.impl;

import com.shopstyle.ms_customer.service.AddressService;
import com.shopstyle.ms_customer.web.controller.AddressController;
import com.shopstyle.ms_customer.web.dto.AddressGetDto;
import com.shopstyle.ms_customer.web.dto.AddressPostDto;
import com.shopstyle.ms_customer.web.dto.AddressPutDto;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("v1/address")
@RestController
public class AddressControllerImpl implements AddressController {

    private final AddressService service;

    @Override
    @PostMapping
    public ResponseEntity<AddressGetDto> createAddress(@Valid @RequestBody AddressPostDto dto) {
        var AddressGetDto = service.createAddress(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(AddressGetDto);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<AddressGetDto> updateAddress(@PathVariable Long id, @Valid @RequestBody AddressPutDto dto) {
        var addressDto = service.updateAddress(id, dto);
        return ResponseEntity.status(HttpStatus.OK).body(addressDto);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAddress(@RequestParam Long id) {
        return null;
    }

}

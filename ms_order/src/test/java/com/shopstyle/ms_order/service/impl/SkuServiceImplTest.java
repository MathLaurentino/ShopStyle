package com.shopstyle.ms_order.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.shopstyle.ms_order.exception.EntityNotFoundException;
import com.shopstyle.ms_order.exception.ErrorMicroServiceComunicationException;
import com.shopstyle.ms_order.exception.InsufficientStockException;
import com.shopstyle.ms_order.feign.SkuFeignService;
import com.shopstyle.ms_order.servece.impl.SkuServiceImpl;
import com.shopstyle.ms_order.web.dto.CartItemDto;
import com.shopstyle.ms_order.web.dto.feign.SkuDtoFeign;
import feign.FeignException;
import feign.Request;
import feign.RequestTemplate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

@ExtendWith(MockitoExtension.class)
class SkuServiceImplTest {

    @Mock
    private SkuFeignService skuFeignService;

    @InjectMocks
    private SkuServiceImpl skuService;

    @Test
    void testCartTotalPrice_Success() {
        CartItemDto cartItem = new CartItemDto(1L, 2);
        SkuDtoFeign skuDtoFeign = new SkuDtoFeign(1L, 10, new BigDecimal("50.00"));
        ResponseEntity<SkuDtoFeign> responseEntity = ResponseEntity.ok(skuDtoFeign);

        when(skuFeignService.findSkuById(cartItem.getSkuId())).thenReturn(responseEntity);

        BigDecimal totalPrice = skuService.cartTotalPrice(Collections.singletonList(cartItem));
        assertNotNull(totalPrice);
        assertEquals(new BigDecimal("100.00"), totalPrice);
    }

    @Test
    void testCartTotalPrice_InsufficientStock() {
        CartItemDto cartItem = new CartItemDto(1L, 10);
        SkuDtoFeign skuDtoFeign = new SkuDtoFeign(1L, 5, new BigDecimal("50.00"));
        ResponseEntity<SkuDtoFeign> responseEntity = ResponseEntity.ok(skuDtoFeign);

        when(skuFeignService.findSkuById(cartItem.getSkuId())).thenReturn(responseEntity);

        assertThrows(InsufficientStockException.class, () -> {
            skuService.cartTotalPrice(Collections.singletonList(cartItem));
        });

        verify(skuFeignService).findSkuById(cartItem.getSkuId());
    }

    @Test
    void testCartTotalPrice_SkuNotFound() {
        CartItemDto cartItem = new CartItemDto(1L, 2);
        Request request = Request.create(Request.HttpMethod.GET, "url",
                new HashMap<>(), null, new RequestTemplate());

        FeignException.FeignClientException exception = new FeignException.FeignClientException(
                404, "Not Found", request, null, null
        );

        when(skuFeignService.findSkuById(cartItem.getSkuId())).thenThrow(exception);
        assertThrows(EntityNotFoundException.class, () -> {
            skuService.cartTotalPrice(Collections.singletonList(cartItem));
        });
        verify(skuFeignService).findSkuById(cartItem.getSkuId());
    }

    @Test
    void testCartTotalPrice_MicroServiceCommunicationError() {
        CartItemDto cartItem = new CartItemDto(1L, 2);
        Request request = Request.create(Request.HttpMethod.GET, "url",
                new HashMap<>(), null, new RequestTemplate());

        when(skuFeignService.findSkuById(cartItem.getSkuId())).thenThrow(new FeignException.FeignClientException(
            500, "Internal Server Error", request, null, null
        ));

        assertThrows(ErrorMicroServiceComunicationException.class, () -> {
            skuService.cartTotalPrice(Collections.singletonList(cartItem));
        });
        verify(skuFeignService).findSkuById(cartItem.getSkuId());
    }

    @Test
    void testCartTotalPrice_EmptyCart() {
        BigDecimal totalPrice = skuService.cartTotalPrice(Collections.emptyList());

        assertNotNull(totalPrice);
        assertEquals(BigDecimal.ZERO, totalPrice);
    }
}

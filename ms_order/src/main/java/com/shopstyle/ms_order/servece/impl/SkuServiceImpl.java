package com.shopstyle.ms_order.servece.impl;

import com.shopstyle.ms_order.exception.EntityNotFoundException;
import com.shopstyle.ms_order.exception.ErrorMicroServiceComunicationException;
import com.shopstyle.ms_order.exception.InsufficientStockException;
import com.shopstyle.ms_order.servece.CustomerFeignService;
import com.shopstyle.ms_order.servece.SkuFeignService;
import com.shopstyle.ms_order.servece.SkuService;
import com.shopstyle.ms_order.web.dto.CartItemDto;
import com.shopstyle.ms_order.web.dto.feign.SkuDtoFeign;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor
@Service
public class SkuServiceImpl implements SkuService {

    private final CustomerFeignService customerFeignService;
    private final SkuFeignService skuFeignService;

    @Override
    public BigDecimal cartTotalPrice(List<CartItemDto> dtos) {
        try {
            BigDecimal price = BigDecimal.valueOf(0);

            for (CartItemDto cartItem : dtos) {
                ResponseEntity<SkuDtoFeign> responseEntity = skuFeignService.findSkuById(cartItem.getSkuId());
                SkuDtoFeign skuDtoFeign = responseEntity.getBody();

                assert skuDtoFeign != null;
                if (skuDtoFeign.getQuantity() < cartItem.getQuantity()){
                    throw new InsufficientStockException("Insufficient stock for sku " + skuDtoFeign.getId());
                }

                price = price.add(skuDtoFeign.getPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity())));
            }

            return price;
        } catch (FeignException.FeignClientException e){
            int status = e.status();
            if(HttpStatus.NOT_FOUND.value() == status){
                throw new EntityNotFoundException("Sku not found");
            }
            throw new ErrorMicroServiceComunicationException(e.getMessage(), status);
        }
    }
}

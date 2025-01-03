package com.shopstyle.bff_shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class BffShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(BffShopApplication.class, args);
	}

}

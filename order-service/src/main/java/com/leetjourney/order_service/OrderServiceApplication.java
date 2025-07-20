/*
 * Copyright (C) 2025 Leetjourney
 * Licensed under the CC BY-NC 4.0 License.
 * See LICENSE file for details.
 */


package com.leetjourney.order_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class OrderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderServiceApplication.class, args);
	}

}

package com.usermanagement.controller;

import com.usermanagement.entity.Product;
import com.usermanagement.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("products")
@AllArgsConstructor
public class ProductController {

	private final ProductService productService;

	@GetMapping
	public Flux<Product> getAll() {
		return productService.getAll();
	}

	@GetMapping("{id}")
	public Mono getById(@PathVariable("id") final String id) {
		return productService.getById(id);
	}

}

package com.productmanagement.controller;

import com.productmanagement.entity.Product;
import com.productmanagement.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
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

	@PutMapping("{id}")
	public Mono updateById(@PathVariable("id") final String id, @RequestBody final Product product) {
		return productService.update(id, product);
	}

	@PostMapping
	public Mono save(@RequestBody final Product product) {
		return productService.save(product);
	}

}

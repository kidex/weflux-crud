package com.usermanagement.service;

import com.usermanagement.entity.Product;
import com.usermanagement.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Transactional
@AllArgsConstructor
public class ProductService {
	private final ProductRepository productRepository;

	public Flux<Product> getAll() {
		return productRepository.findAll().switchIfEmpty(Flux.empty());
	}

	public Mono<Product> getById(final String id) {
		return productRepository.findById(id);
	}

}

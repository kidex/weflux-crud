package com.productmanagement.service;

import com.productmanagement.entity.Product;
import com.productmanagement.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;

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

	public Mono update(final String id, final Product product) {
		return productRepository.save(product);
	}

	public Mono save(final Product product) {
		return productRepository.save(product);
	}

	public Mono delete(final String id) {
		final Mono<Product> dbProduct = getById(id);
		if (Objects.isNull(dbProduct)) {
			return Mono.empty();
		}
		return getById(id).switchIfEmpty(Mono.empty()).filter(Objects::nonNull).flatMap(productToBeDeleted -> productRepository
				.delete(productToBeDeleted).then(Mono.just(productToBeDeleted)));
	}
}

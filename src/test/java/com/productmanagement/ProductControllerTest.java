package com.productmanagement;

import com.productmanagement.controller.ProductController;
import com.productmanagement.entity.Product;
import com.productmanagement.service.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Mono;

import static org.mockito.BDDMockito.given;


@RunWith(SpringRunner.class)
@WebFluxTest(ProductController.class)
public class ProductControllerTest {

	@Autowired
	private WebTestClient webTestClient;
	@MockBean
	private ProductService productService;

	@Test
	public void getAll() {
		webTestClient.get().uri("/products").exchange().expectStatus().is2xxSuccessful();
	}

	@Test
	public void getById() throws Exception {
		given(this.productService.getById("string"))
				.willReturn(Mono.just(Product.builder().name("product").description("product-description").build()));
		this.webTestClient.get().uri("/products/string")
				.exchange()
				.expectStatus().isOk()
				.expectBody().jsonPath("$.name").isEqualTo("product");
	}

	@Test
	public void update() {
		final Product mockProduct = Product.builder().id("string").name("product").description("product-description").build();
		given(this.productService.update("string", mockProduct))
				.willReturn(Mono.just(mockProduct));
		this.webTestClient.put().uri("/products/string").body(BodyInserters.fromObject(mockProduct))
				.exchange()
				.expectStatus().isOk()
				.expectBody().jsonPath("$.name").isEqualTo("product");
	}

	@Test
	public void save() {
		final Product mockProduct = Product.builder().id("string").name("product").description("product-description").build();
		given(this.productService.save(mockProduct))
				.willReturn(Mono.just(mockProduct));
		this.webTestClient.post().uri("/products").body(BodyInserters.fromObject(mockProduct))
				.exchange()
				.expectStatus().isOk()
				.expectBody().jsonPath("$.name").isEqualTo("product");
	}

	@Test
	public void delete() {
		final Product mockProduct = Product.builder().id("string").name("product").description("product-description").build();
		given(this.productService.delete("string"))
				.willReturn(Mono.just(mockProduct));
		this.webTestClient.delete().uri("/products/string")
				.exchange()
				.expectStatus().isOk()
				.expectBody().jsonPath("$.name").isEqualTo("product");
	}

}

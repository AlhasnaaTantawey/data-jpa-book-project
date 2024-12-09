package com.global.book.service;

import com.global.book.dto.ProductDto;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Service
public class ProductService {
    private static final String BASE_PRODUCT_URL="http://localhost:8081/api/v1/product";

    WebClient client=WebClient.create(BASE_PRODUCT_URL);

    public Flux<ProductDto> getAllAuthors() {
        return client.get()
                .retrieve()
                .bodyToFlux(ProductDto.class);
    }
}

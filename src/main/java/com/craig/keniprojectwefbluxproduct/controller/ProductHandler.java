package com.craig.keniprojectwefbluxproduct.controller;

import com.craig.keniprojectwefbluxproduct.dto.ProductDto;
import com.craig.keniprojectwefbluxproduct.exception.ProductNotFoundException;
import com.craig.keniprojectwefbluxproduct.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.net.URI;


@Component
@Slf4j
public class ProductHandler {

    private final ProductService productService;

    @Autowired
    public ProductHandler(ProductService productService) {
        this.productService = productService;
    }

    public Mono<ServerResponse> findAll() {
        return ServerResponse.ok()
                .body(productService.findAll(), ProductDto.class);
    }

    public Mono<ServerResponse> findById(ServerRequest request) {
        String id = request.pathVariable("id");
        return productService.findById(id)
                .flatMap(product -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(product))
                .switchIfEmpty(Mono.error(new ProductNotFoundException("CLIENT DOES NOT EXIST")))
                .onErrorResume(error -> Mono.error(new ProductNotFoundException(error.getMessage())));
    }

    public Mono<ServerResponse> save(ServerRequest request) {
        Mono<ProductDto> productDtoMono = request.bodyToMono(ProductDto.class);
        return productDtoMono.flatMap(productService::create)
                .flatMap(productDto -> ServerResponse.created(URI.create("/product/".concat(productDto.getId())))
                        .bodyValue(productDto))
                .switchIfEmpty(Mono.error(new ProductNotFoundException("CLIENT DOES NOT EXIST")))
                .onErrorResume(error -> Mono.error(new ProductNotFoundException(error.getMessage())));
    }

    public Mono<ServerResponse> update(ServerRequest request) {
        String id = request.pathVariable("id");
        Mono<ProductDto> productDtoMono = request.bodyToMono(ProductDto.class);
        return productDtoMono.flatMap(productDto -> ServerResponse.ok()
                .body(productService.update(productDto, id), ProductDto.class));

    }

    public Mono<ServerResponse> delete(ServerRequest request) {
        String id = request.pathVariable("id");
        return productService.delete(id).then(ServerResponse.noContent().build())
                .onErrorResume(throwable -> Mono.error(new RuntimeException(throwable.getMessage())));
    }

}

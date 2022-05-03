package com.craig.keniprojectwefbluxproduct.service;

import com.craig.keniprojectwefbluxproduct.dto.ProductDto;
import com.craig.keniprojectwefbluxproduct.exception.ProductNotFoundException;
import com.craig.keniprojectwefbluxproduct.mapper.ProductMapper;
import com.craig.keniprojectwefbluxproduct.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.craig.keniprojectwefbluxproduct.mapper.ProductMapper.mapToProduct;

@Service
public class ProductService implements IBaseService<ProductDto> {


    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public Mono<ProductDto> create(ProductDto o) {
        return productRepository.save(mapToProduct(o))
                .flatMap(ProductMapper::toProductDto);
    }

    @Override
    public Flux<ProductDto> findAll() {
        return productRepository.findAll()
                .flatMap(ProductMapper::toProductDto);
    }

    @Override
    public Mono<ProductDto> findById(String id) {
        return productRepository.findById(id)
                .switchIfEmpty(Mono.error(new ProductNotFoundException(id)))
                .flatMap(ProductMapper::toProductDto);
    }

    @Override
    public Mono<ProductDto> update(ProductDto o, String id) {
        return productRepository.findById(id)
                .map(product -> mapToProduct(o))
                .flatMap(productRepository::save)
                .flatMap(ProductMapper::toProductDto);
    }

    @Override
    public Mono<Void> delete(String id) {
        return productRepository.findById(id)
                .switchIfEmpty(Mono.error(new ProductNotFoundException(id)))
                .flatMap(product -> productRepository.deleteById(id));
    }
}

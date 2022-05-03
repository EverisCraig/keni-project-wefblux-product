package com.craig.keniprojectwefbluxproduct.mapper;

import com.craig.keniprojectwefbluxproduct.document.Product;
import com.craig.keniprojectwefbluxproduct.dto.ProductDto;
import reactor.core.publisher.Mono;


public class ProductMapper {

//    @Mappings({
//
//    })
//    ProductDto toProductDto(Product product) ;
//
//    @Mappings({
//
//    })
//    Product toProduct(ProductDto productDto) ;

    public static Product mapToProduct(ProductDto productDto) {
        return Product.builder()
                .id(productDto.getId())
                .name(productDto.getName())
                .description(productDto.getDescription())
                .image(productDto.getImage())
                .price(productDto.getPrice())
                .category(productDto.getCategory())
                .build();
    }

    public static Mono<ProductDto> toProductDto(Product product) {
        ProductDto dto=ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .image(product.getImage())
                .price(product.getPrice())
                .category(product.getCategory())
                .build();
        return Mono.just(dto);
    }

}

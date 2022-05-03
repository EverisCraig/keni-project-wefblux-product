package com.craig.keniprojectwefbluxproduct.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IBaseService<T> {

    Mono<T> create(T o);

    Flux<T> findAll();

    Mono<T> findById(String id);

    Mono<T> update(T o, String id);

    Mono<Void> delete(String id);
}

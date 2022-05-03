package com.craig.keniprojectwefbluxproduct.config;

import com.craig.keniprojectwefbluxproduct.controller.ProductHandler;
import com.craig.keniprojectwefbluxproduct.dto.ProductDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class ClientRouterConfig {
    @Bean
    @RouterOperations({
            @RouterOperation(
                    path = "/products",
                    produces = {
                            MediaType.APPLICATION_JSON_VALUE
                    },
                    method = RequestMethod.GET,
                    beanClass = ProductHandler.class,
                    beanMethod = "findAll",
                    operation = @Operation(
                            operationId = "findAll",
                            responses = {
                                    @ApiResponse(
                                            responseCode = "200",
                                            description = "Successful operation",
                                            content = @Content(schema = @Schema(
                                                    implementation = ProductDto.class
                                            ))
                                    )
                            }
                    )
            ),
            @RouterOperation(
                    path = "/product/{id}",
                    produces = {
                            MediaType.APPLICATION_JSON_VALUE
                    },
                    method = RequestMethod.GET,
                    beanClass = ProductHandler.class,
                    beanMethod = "findById",
                    operation = @Operation(
                            operationId = "findById",
                            responses = {
                                    @ApiResponse(
                                            responseCode = "200",
                                            description = "Successful operation",
                                            content = @Content(schema = @Schema(
                                                    implementation = ProductDto.class
                                            ))
                                    ),
                                    @ApiResponse(
                                            responseCode = "404",
                                            description = "Product not found"
                                    )
                            },
                            parameters = {
                                    @Parameter(
                                            name = "id",
                                            in = ParameterIn.PATH,
                                            description = "Product id",
                                            required = true
                                    )
                            }
                    )
            ),
            @RouterOperation(
                    path = "/product/new",
                    produces = {
                            MediaType.APPLICATION_JSON_VALUE
                    },
                    method = RequestMethod.POST,
                    beanClass = ProductHandler.class,
                    beanMethod = "save",
                    operation = @Operation(
                            operationId = "save",
                            responses = {
                                    @ApiResponse(
                                            responseCode = "200",
                                            description = "successful operation",
                                            content = @Content(schema = @Schema(
                                                    implementation = ProductDto.class
                                            ))
                                    )
                            },
                            requestBody = @RequestBody(
                                    content = @Content(schema = @Schema(
                                            implementation = ProductDto.class
                                    ))
                            )

                    )
            ),
            @RouterOperation(
                    produces = {
                            MediaType.APPLICATION_JSON_VALUE
                    },
                    path = "/product/update/{id}",
                    method = RequestMethod.PUT,
                    beanClass = ProductHandler.class,
                    beanMethod = "update",
                    operation = @Operation(
                            operationId = "update",
                            responses = {
                                    @ApiResponse(
                                            responseCode = "200",
                                            description = "successful operation",
                                            content = @Content(schema = @Schema(
                                                    implementation = ProductDto.class
                                            ))
                                    )
                            },
                            requestBody = @RequestBody(
                                    content = @Content(schema = @Schema(
                                            implementation = ProductDto.class
                                    ))
                            ),
                            parameters = {
                                    @Parameter(
                                            name = "id",
                                            in = ParameterIn.PATH,
                                            description = "Product id",
                                            required = true
                                    )
                            }

                    )
            ),
            @RouterOperation(
                    produces = {
                            MediaType.APPLICATION_JSON_VALUE
                    },
                    path = "/product/delete/{id}",
                    method = RequestMethod.DELETE,
                    beanClass = ProductHandler.class,
                    beanMethod = "delete",
                    operation = @Operation(
                            operationId = "delete",
                            responses = {
                                    @ApiResponse(
                                            responseCode = "200",
                                            description = "successful operation",
                                            content = @Content(schema = @Schema(
                                                    implementation = ProductDto.class
                                            ))
                                    )
                            },
                            parameters = {
                                    @Parameter(
                                            name = "id",
                                            in = ParameterIn.PATH,
                                            description = "Product id",
                                            required = true
                                    )
                            }

                    )
            )
    })
    public RouterFunction<ServerResponse> routes(ProductHandler productHandler) {
        return route(GET("/products"), request -> productHandler.findAll())
                .andRoute(GET("/product/{id}"),productHandler::findById)
                .andRoute(POST("/product"), productHandler::save)
                .andRoute(PUT("/product/update/{id}"), productHandler::update)
                .andRoute(DELETE("/product/delete/{id}"), productHandler::delete);
    }
}

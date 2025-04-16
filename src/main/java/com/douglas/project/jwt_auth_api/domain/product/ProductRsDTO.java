package com.douglas.project.jwt_auth_api.domain.product;


public record ProductRsDTO(String id, String name, Integer price) {

    public ProductRsDTO(Product product){
        this(product.getId(), product.getName(), product.getPrice());
    }
}

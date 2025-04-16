package com.douglas.project.jwt_auth_api.controller;

import com.douglas.project.jwt_auth_api.domain.product.Product;
import com.douglas.project.jwt_auth_api.domain.product.ProductRqDTO;
import com.douglas.project.jwt_auth_api.domain.product.ProductRsDTO;
import com.douglas.project.jwt_auth_api.repository.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProdutcController {
    @Autowired
    private ProductRepository repository;

    @PostMapping
    public ResponseEntity postProduct(@RequestBody @Valid ProductRqDTO body){
        Product newProduct = new Product(body);

        this.repository.save(newProduct);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity getAllProducts(){
        List<ProductRsDTO> productList = this.repository.findAll().stream().map(ProductRsDTO::new).toList();

        return ResponseEntity.ok(productList);
    }
}

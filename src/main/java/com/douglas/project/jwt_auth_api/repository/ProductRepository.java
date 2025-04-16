package com.douglas.project.jwt_auth_api.repository;

import com.douglas.project.jwt_auth_api.domain.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {
}

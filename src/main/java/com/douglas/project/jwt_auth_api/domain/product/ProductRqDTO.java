package com.douglas.project.jwt_auth_api.domain.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductRqDTO(@NotBlank
                           String name,
                           @NotNull
                           Integer price) {
}

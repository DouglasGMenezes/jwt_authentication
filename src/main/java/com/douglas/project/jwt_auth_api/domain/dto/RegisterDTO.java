package com.douglas.project.jwt_auth_api.domain.dto;

import com.douglas.project.jwt_auth_api.domain.user.UserRole;

public record RegisterDTO(String login , String password , UserRole role) {
}

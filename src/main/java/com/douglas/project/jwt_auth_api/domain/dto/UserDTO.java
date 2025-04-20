package com.douglas.project.jwt_auth_api.domain.dto;

import com.douglas.project.jwt_auth_api.domain.user.User;
import com.douglas.project.jwt_auth_api.domain.user.UserRole;

public record UserDTO(String login, UserRole role) {

    public UserDTO(User user) {
        this(user.getLogin(), user.getRole());
    }
}

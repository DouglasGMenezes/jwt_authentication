package com.douglas.project.jwt_auth_api.domain.user;

public record UserDTO(String login, UserRole role) {

    public UserDTO(User user) {
        this(user.getLogin(), user.getRole());
    }
}

package com.douglas.project.jwt_auth_api.controller;

import com.douglas.project.jwt_auth_api.domain.dto.AuthDTO;
import com.douglas.project.jwt_auth_api.domain.dto.LoginrRsDTO;
import com.douglas.project.jwt_auth_api.domain.dto.RegisterDTO;
import com.douglas.project.jwt_auth_api.domain.dto.UserDTO;
import com.douglas.project.jwt_auth_api.domain.user.User;
import com.douglas.project.jwt_auth_api.service.AuthService;
import com.douglas.project.jwt_auth_api.service.TokenService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private AuthenticationManager authenticationManager;
    private AuthService authService;
    private final TokenService tokenService;

    public AuthController(AuthenticationManager authenticationManager , AuthService authService, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.authService = authService;
        this.tokenService = tokenService;
    }



    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = authService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthDTO data){
        var userNamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(userNamePassword);
        User user = (User) auth.getPrincipal();
        String token = tokenService.generateToken(user);
        return ResponseEntity.ok(new LoginrRsDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody @Valid RegisterDTO data) {
            authService.register(data);
            return ResponseEntity.ok("Usuário cadastrado com sucesso.");
    }

    @DeleteMapping("/delete/{login}")
    public ResponseEntity<String> deleteUser(@PathVariable String login) {
            authService.deleteUser(login);
            return ResponseEntity.ok("Usuário deletado com sucesso!");

    }


}

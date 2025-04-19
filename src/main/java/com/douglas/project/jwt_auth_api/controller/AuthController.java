package com.douglas.project.jwt_auth_api.controller;

import com.douglas.project.jwt_auth_api.domain.user.AuthDTO;
import com.douglas.project.jwt_auth_api.domain.user.RegisterDTO;
import com.douglas.project.jwt_auth_api.domain.user.User;
import com.douglas.project.jwt_auth_api.domain.user.UserDTO;
import com.douglas.project.jwt_auth_api.repository.UserRepository;
import com.douglas.project.jwt_auth_api.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private AuthenticationManager authenticationManager;
    private AuthService authService;

    public AuthController(AuthenticationManager authenticationManager , AuthService authService) {
        this.authenticationManager = authenticationManager;
        this.authService = authService;
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

        return ResponseEntity.ok().build();
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody @Valid RegisterDTO data) {
        try {
            authService.register(data);
                return ResponseEntity.ok("Usuário cadastrado com sucesso.");
        } catch (IllegalAccessException e) {
                return ResponseEntity.badRequest().body("Usuario invalido ou já existente.");
        }
    }

    @DeleteMapping("/delete/{login}")
    public ResponseEntity<String> deleteUser(@PathVariable String login) {
        try {
            authService.deleteUser(login);
            return ResponseEntity.ok("Usuário deletado com sucesso!");
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


}

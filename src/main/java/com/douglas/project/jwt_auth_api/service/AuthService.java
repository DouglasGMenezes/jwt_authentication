package com.douglas.project.jwt_auth_api.service;

import com.douglas.project.jwt_auth_api.domain.user.RegisterDTO;
import com.douglas.project.jwt_auth_api.domain.user.User;
import com.douglas.project.jwt_auth_api.domain.user.UserDTO;
import com.douglas.project.jwt_auth_api.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthService implements UserDetailsService {

    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByLogin(username);
    }


    public List<UserDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserDTO::new)
                .toList();
    }

    public void register(RegisterDTO data) throws IllegalAccessException {
        if (userRepository.findByLogin(data.login()) != null) {
            throw new IllegalAccessException("Usuário já existe.");
        }
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User user = new User(data.login(), encryptedPassword , data.role());

        userRepository.save(user);
    }

    public void deleteUser(String login) {
        User user = (User) userRepository.findByLogin(login);
        if (user == null) {
            throw new UsernameNotFoundException("Usuário não encontrado.");
        }
        userRepository.delete(user);
    }

}

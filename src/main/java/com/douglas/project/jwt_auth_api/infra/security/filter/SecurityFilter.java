package com.douglas.project.jwt_auth_api.infra.security.filter;

import com.douglas.project.jwt_auth_api.repository.UserRepository;
import com.douglas.project.jwt_auth_api.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    private TokenService tokenService;
    private UserRepository userRepository;

    public SecurityFilter(TokenService tokenService , UserRepository userRepository) {
        this.tokenService = tokenService;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = this.recoverToken(request);
        if (token != null) {
            var login = tokenService.validateToken(token);
            UserDetails user = userRepository.findByLogin(login);

            System.out.println("Authorities do usuário: " + user.getAuthorities());

            var auth = new UsernamePasswordAuthenticationToken(user,null,user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
        filterChain.doFilter(request,response);
    }

    private String recoverToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        if (authHeader == null) return null;
        return authHeader.replace("Bearer ", "");
    }

}

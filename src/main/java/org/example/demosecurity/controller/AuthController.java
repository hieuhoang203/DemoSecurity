package org.example.demosecurity.controller;

import org.example.demosecurity.dtos.AuthResponse;
import org.example.demosecurity.dtos.LoginDTO;
import org.example.demosecurity.entity.Account;
import org.example.demosecurity.entity.common.Roles;
import org.example.demosecurity.repository.AccountRepository;
import org.example.demosecurity.security.JwtConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AccountRepository repository;

    @Autowired
    private JwtConfig jwtConfig;

    @PostMapping(value = "/register")
    public ResponseEntity<String> register(@RequestBody LoginDTO loginDTO) {
        if (this.repository.existsAccountByLogin(loginDTO.getLogin())) {
            return new ResponseEntity<>("User name taken!", HttpStatus.BAD_REQUEST);
        }
        Account account = Account.builder()
                .login(loginDTO.getLogin())
                .pass(passwordEncoder.encode(loginDTO.getPass()))
                .role(Roles.USER)
                .build();
        this.repository.save(account);
        return new ResponseEntity<>("Create account successfully!", HttpStatus.CREATED);
    }

    @PostMapping(value = "/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginDTO loginDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getLogin(), loginDTO.getPass()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtConfig.generateToken(authentication.getName());
        return new ResponseEntity<>(new AuthResponse(token), HttpStatus.OK);
    }
}

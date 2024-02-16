package com.example.pooling.controller;

import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.pooling.dto.LoginDto;
import com.example.pooling.dto.MessageDto;
import com.example.pooling.dto.RegisterDto;
import com.example.pooling.jwt.JwtHelper;
import com.example.pooling.model.Role;
import com.example.pooling.model.Role.RoleName;
import com.example.pooling.model.User;
import com.example.pooling.repository.RoleRepository;
import com.example.pooling.repository.UserRepository;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/auth")
@Slf4j
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtHelper jwtHelper;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public MessageDto login(@RequestBody LoginDto loginDto) {
        log.info("Login: {}", loginDto);
        var authentication = new UsernamePasswordAuthenticationToken(loginDto.username(), loginDto.password());
        try {
            authenticationManager.authenticate(authentication);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("not valid username or password");
        }

        var userDetails = userDetailsService.loadUserByUsername(loginDto.username());

        String token = jwtHelper.generateToken(userDetails);

        return new MessageDto(Map.of("token", token));
    }

    @PostMapping("/register")
    public User register(@Valid @RequestBody RegisterDto registerDto) {
        User user = modelMapper.map(registerDto, User.class);
        Role userRole = roleRepository.findByRoleName(RoleName.USER)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "couldn't set role"));
        user.addRole(userRole);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return user;
    }
}

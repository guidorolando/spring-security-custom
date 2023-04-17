package com.example.authtest.rest;

import com.example.authtest.config.CustomUserDetails;
import com.example.authtest.config.LoginPasswordAuthenticationServiceToken;
import com.example.authtest.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(new LoginPasswordAuthenticationServiceToken(loginRequest.getLogin(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateToken(authentication);

        /*CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        List<String> userTypes = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
                .collect(Collectors.toList());*/

        //return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(), userTypes));
        return ResponseEntity.ok(new JwtResponse(jwt, "secret", Arrays.asList()));
    }

    /*@PostMapping("/usuario")
    public ResponseEntity<CreateUserResponse> createUser(@RequestBody CreateUserRequest createUserRequest) {
        return new ResponseEntity<CreateUserResponse>(this.userService.createUser(createUserRequest), HttpStatus.CREATED);
    }*/
}

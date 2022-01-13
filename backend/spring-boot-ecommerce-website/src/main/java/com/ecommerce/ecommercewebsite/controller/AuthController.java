package com.ecommerce.ecommercewebsite.controller;

import com.ecommerce.ecommercewebsite.dao.RoleRepository;
import com.ecommerce.ecommercewebsite.dao.UserRepository;
import com.ecommerce.ecommercewebsite.entity.User;
import com.ecommerce.ecommercewebsite.security.payload.request.LoginRequest;
import com.ecommerce.ecommercewebsite.security.payload.request.SignupRequest;
import com.ecommerce.ecommercewebsite.security.payload.response.JwtResponse;
import com.ecommerce.ecommercewebsite.security.payload.response.MessageResponse;
import com.ecommerce.ecommercewebsite.security.jwt.JwtUtils;
import com.ecommerce.ecommercewebsite.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/auth", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), Collections.singletonList(userDetails.getAuthorities().toString())));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signupRequest) {
        if (userRepository.existsByEmail(signupRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already taken!"));
        }

        // Create new user's account
        User user = new User(signupRequest.getEmail(), encoder.encode(signupRequest.getPassword()), signupRequest.getFirst_name(),
                signupRequest.getLast_name(), signupRequest.getPhone_number());

        user.setUser_role_id(1);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}

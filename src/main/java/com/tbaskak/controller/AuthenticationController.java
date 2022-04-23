package com.tbaskak.controller;

import com.tbaskak.authentication.UserDetails;
import com.tbaskak.entity.User;
import com.tbaskak.model.authentication.JwtRequest;
import com.tbaskak.model.authentication.JwtResponse;
import com.tbaskak.repository.UserRepository;
import com.tbaskak.service.UserService;
import com.tbaskak.util.JwtUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;

@RestController
public class AuthenticationController {
    @Autowired
    UserService userService;

    @Autowired
    JwtUtility jwtUtility;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity authenticate(@Valid @RequestBody JwtRequest jwtRequest) throws NoSuchAlgorithmException {
        User user = userService.login(jwtRequest.getEmail(),jwtRequest.getPassword());
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getEmail(),user.getPassword()));

        final UserDetails userDetails = userService.loadUserByUsername(jwtRequest.getEmail());

        final String token = jwtUtility.generateToken(userDetails);

        return new ResponseEntity<>(new JwtResponse(token), HttpStatus.OK) ;
    }

    @PostMapping("/signup")
    public ResponseEntity signup(@Valid @RequestBody User user){
        return new ResponseEntity<>(userService.singUp(user),HttpStatus.CREATED);
    }
}

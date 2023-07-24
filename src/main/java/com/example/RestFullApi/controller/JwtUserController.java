package com.example.RestFullApi.controller;

import com.example.RestFullApi.config.jwt.JwtTokenUtil;
import com.example.RestFullApi.dto.AuthRequest;
import com.example.RestFullApi.dto.JwtResponse;
import com.example.RestFullApi.facade.UserFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
public class JwtUserController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserFacade userFacade;


    @Autowired
    public JwtUserController(
            AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil, UserFacade userFacade) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userFacade = userFacade;
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthRequest authenticationRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
           return ResponseEntity.status(401).body(e.getMessage());
        }

        final UserDetails userDetails = userFacade.loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }
    @PostMapping("/registration")
    public ResponseEntity<?> createUser(@RequestBody AuthRequest authRequest) {
        try {
            userFacade.createUser(authRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body("User add");
        }
        catch (Exception ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }
}


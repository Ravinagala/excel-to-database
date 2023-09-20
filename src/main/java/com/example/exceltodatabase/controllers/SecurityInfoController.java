package com.example.exceltodatabase.controllers;

import com.example.exceltodatabase.entities.AuthRequest;
import com.example.exceltodatabase.entities.SecurityInfo;
import com.example.exceltodatabase.services.JwtService;
import com.example.exceltodatabase.services.SecurityInfoSecurityDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class SecurityInfoController {
    @Autowired
    SecurityInfoSecurityDetailsService securityInfoSecurityDetailsService;

    @Autowired
    JwtService jwtService;

    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping("/add-security-user")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String addNewUser(@RequestBody SecurityInfo securityInfo){
        return securityInfoSecurityDetailsService.addUser(securityInfo);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public List<SecurityInfo> getRoles(){
        return securityInfoSecurityDetailsService.getRoles();
    }


    @PostMapping("/authenticate")
    public String authenticate(@RequestBody AuthRequest authRequest){
        Authentication authentication = authenticationManager.authenticate
                (new UsernamePasswordAuthenticationToken
                        (authRequest.getUsername(),authRequest.getPassword()));

        if(authentication.isAuthenticated()){
            return jwtService.generateToken(authRequest.getUsername());
        }else
            throw new UsernameNotFoundException("Invalid User Request!");

    }


}

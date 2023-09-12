package com.example.exceltodatabase.controllers;

import com.example.exceltodatabase.entities.SecurityInfo;
import com.example.exceltodatabase.services.SecurityInfoSecurityDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class SecurityInfoController {
    @Autowired
    SecurityInfoSecurityDetailsService securityInfoSecurityDetailsService;

    @PostMapping("/add-security-user")
    @PreAuthorize("hasAuthority('ROLE_ADMIN,ROLE_USER')")
    public String addNewUser(@RequestBody SecurityInfo securityInfo){
        return securityInfoSecurityDetailsService.addUser(securityInfo);
    }

    @GetMapping
    public List<SecurityInfo> getRoles(){
        return securityInfoSecurityDetailsService.getRoles();
    }


}

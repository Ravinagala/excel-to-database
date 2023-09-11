package com.example.exceltodatabase.controllers;

import com.example.exceltodatabase.entities.SecurityInfo;
import com.example.exceltodatabase.services.SecurityInfoSecurityDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/roles")
public class SecurityInfoController {
    @Autowired
    SecurityInfoSecurityDetailsService securityInfoSecurityDetailsService;

    @PostMapping("/add-security-user")
    public String addNewUser(@RequestBody SecurityInfo securityInfo){
        return securityInfoSecurityDetailsService.addUser(securityInfo);
    }


}

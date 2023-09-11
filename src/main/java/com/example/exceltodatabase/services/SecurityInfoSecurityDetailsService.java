package com.example.exceltodatabase.services;

import com.example.exceltodatabase.entities.SecurityInfo;
import com.example.exceltodatabase.repos.SecurityInfoRepo;
import com.example.exceltodatabase.security.SecurityInfoSecurityDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SecurityInfoSecurityDetailsService implements UserDetailsService {
    @Autowired
    SecurityInfoRepo securityInfoRepo;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<SecurityInfo> securityInfo = securityInfoRepo.findByName(username);
        return securityInfo.map(SecurityInfoSecurityDetails::new)
                .orElseThrow(()->new UsernameNotFoundException("user not found"+username));
    }

    public String addUser(SecurityInfo securityInfo){
        securityInfo.setPassword(passwordEncoder.encode(securityInfo.getPassword()));
        securityInfoRepo.save(securityInfo);
        return "user added to system";
    }
}

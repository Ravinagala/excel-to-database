package com.example.exceltodatabase.repos;

import com.example.exceltodatabase.entities.SecurityInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SecurityInfoRepo extends JpaRepository<SecurityInfo,Integer> {

    Optional<SecurityInfo> findByName(String username);
}

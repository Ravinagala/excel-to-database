package com.example.exceltodatabase.repos;

import com.example.exceltodatabase.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Integer> {
}

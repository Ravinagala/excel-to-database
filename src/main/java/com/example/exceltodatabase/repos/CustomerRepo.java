package com.example.exceltodatabase.repos;

import com.example.exceltodatabase.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepo extends JpaRepository<Customer,Integer> {

}

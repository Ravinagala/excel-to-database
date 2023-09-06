package com.example.exceltodatabase.controllers;

import com.example.exceltodatabase.entities.Customer;
import com.example.exceltodatabase.services.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("customers")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @PostMapping("/upload-customers-data")
    public ResponseEntity<?> uploadCustomersData(@RequestParam("file")MultipartFile file){
        customerService.saveCustomersToDatabase(file);
        return ResponseEntity.ok(Map.of("Message","Customers Data Uploaded and save to database successfully"));
    }

    @GetMapping("/getData")
    public ResponseEntity<List<Customer>> getCustomers(){
        return new ResponseEntity<>(customerService.getCustomers(), HttpStatus.FOUND);
    }

}

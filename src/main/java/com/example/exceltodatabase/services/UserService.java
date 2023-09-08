package com.example.exceltodatabase.services;

import com.example.exceltodatabase.entities.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {
    boolean hasCsvFormat(MultipartFile file);


    void processAndSaveData(MultipartFile file);

    List<User> getUsers();

}

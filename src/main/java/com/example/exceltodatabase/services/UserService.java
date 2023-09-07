package com.example.exceltodatabase.services;

import org.springframework.web.multipart.MultipartFile;

public interface UserService {
    boolean hasCsvFormat(MultipartFile file);


    void processAndSaveData(MultipartFile file);

}

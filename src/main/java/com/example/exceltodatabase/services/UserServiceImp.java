package com.example.exceltodatabase.services;

import com.example.exceltodatabase.entities.User;
import com.example.exceltodatabase.repos.UserRepo;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    UserRepo userRepo;

    @Override
    public boolean hasCsvFormat(MultipartFile file) {
        String type = "text/csv";
        if (!type.equals(file.getContentType()))
            return false;
        return true;
    }

    @Override
    public void processAndSaveData(MultipartFile file) {
        try {
            List<User> users = csvToUsers(file.getInputStream());
            userRepo.saveAll(users);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private List<User> csvToUsers(InputStream inputStream) {
        try {
            BufferedReader fileReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());
            List<User> users = new ArrayList<>();
            List<CSVRecord> records = csvParser.getRecords();
            for(CSVRecord csvRecord : records){
                User user = new User(csvRecord.get("id"),csvRecord.get("height"),csvRecord.get("weight"));
                users.add(user);
            }
            return users;
        } catch (IOException e) {
            e.printStackTrace();

        }
        return null;
    }
}
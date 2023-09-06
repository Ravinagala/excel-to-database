package com.example.exceltodatabase.services;

import com.example.exceltodatabase.entities.Customer;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class ExcelUploadService {
    public static boolean isValidExcelFile(MultipartFile file){
        return Objects.equals(file.getContentType(),"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
    }
    public static List<Customer> getCustomersDataFromExcel(InputStream inputStream) throws IOException {
        List<Customer> customers = new ArrayList<>();
        try{
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = workbook.getSheet("Customer");
            int rowIndex = 0;
            for(Row row : sheet){
                if(rowIndex == 0){
                    rowIndex++;
                    continue;
                }
                Iterator<Cell> cellIterator = row.iterator();
                int cellIndex = 0;
                Customer customer = new Customer();
                while (cellIterator.hasNext()){
                    Cell cell = cellIterator.next();
                    switch (cellIndex){
                        case 0 -> customer.setCustomerId((int) cell.getNumericCellValue());
                        case 1 -> customer.setFirstName(cell.getStringCellValue());
                        case 2 -> customer.setLastName(cell.getStringCellValue());
                        case 3 -> customer.setCountry(cell.getStringCellValue());
                        case 4 -> customer.setTelephone((long) cell.getNumericCellValue());
                        default -> {

                        }
                    }
                    cellIndex++;
                }
                customers.add(customer);
            }
        }catch (Exception e){
            e.getStackTrace();
        }
        return customers;

    }
}

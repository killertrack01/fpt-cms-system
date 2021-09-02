//package com.example.fptacademysystem.services;
//
//import java.io.IOException;
//import java.util.List;
//
//import com.example.fptacademysystem.helper.ExcelHelper;
////import com.example.fptacademysystem.helper.ExcelStudentHelper;
//import com.example.fptacademysystem.model.Student;
//import com.example.fptacademysystem.repository.StudentRepository;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//
//@Service
//public class ImportExcelFileService {
//
//    @Autowired
//    StudentRepository repository;
//
//    public void save(MultipartFile files) {
//        try {
//          List<Student> student = ExcelHelper.excelToStud(files.getInputStream());
//          repository.saveAll(student);
//        } catch (IOException e) {
//          throw new RuntimeException("fail to store excel data: " + e.getMessage());
//        }
//    }
//
//}

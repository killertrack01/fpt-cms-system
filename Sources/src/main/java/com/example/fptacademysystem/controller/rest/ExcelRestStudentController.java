package com.example.fptacademysystem.controller.rest;import com.example.fptacademysystem.services.ImportStudentExcel;import com.example.fptacademysystem.services.ImportSubjectExcel;import java.io.File;import java.io.IOException;import java.io.InputStream;import java.nio.file.Files;import java.nio.file.Path;import java.nio.file.Paths;import java.util.List;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.core.io.InputStreamSource;import org.springframework.web.bind.annotation.PostMapping;import org.springframework.web.bind.annotation.RequestParam;import org.springframework.web.bind.annotation.RestController;import org.springframework.web.multipart.MultipartFile;@RestControllerpublic class ExcelRestStudentController {//    @Autowired//    ImportStudentExcel importstudentservice;////    @PostMapping(value = "/api/importdatastudentexcel")//    public String uploadFileStudentExcel(@RequestParam("file") MultipartFile file) throws IOException {//        File f = new File(file.getOriginalFilename());//        File absolute = f.getAbsoluteFile();////        System.out.println(absolute.getPath());//        importstudentservice.importData(file);//        return "true";//    }}
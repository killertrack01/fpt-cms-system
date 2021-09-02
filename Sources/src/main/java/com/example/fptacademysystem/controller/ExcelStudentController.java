package com.example.fptacademysystem.controller;

import com.example.fptacademysystem.helper.ExcelHelper;
//import com.example.fptacademysystem.helper.ExcelStudentHelper;
import com.example.fptacademysystem.message.ResponseMessage;
import com.example.fptacademysystem.services.ExcelService;
//import com.example.fptacademysystem.services.ImportExcelFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class ExcelStudentController {

//    @Autowired
//    ImportExcelFileService fileService;

    @Autowired
    ExcelService fileService;

    @PostMapping("/api/admin/importstudent")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("files") MultipartFile files) {
        
        String message = "";

        if (ExcelHelper.hasExcelFormat(files)) {
            try {

                fileService.save(files); // Save File
                message = "Uploaded the file successfully: " + files.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));

            } catch (Exception e) {
                //message = "Could not upload the file: " + files.getOriginalFilename() + "!";
                message = e.getMessage();
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
            }
        }

        message = "Please upload an excel file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
    }

   
}

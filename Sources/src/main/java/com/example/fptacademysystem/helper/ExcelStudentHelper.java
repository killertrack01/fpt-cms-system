//package com.example.fptacademysystem.helper;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//import com.example.fptacademysystem.model.Student;
//
//import org.apache.poi.ss.usermodel.Workbook;
//import org.apache.poi.xssf.usermodel.XSSFRow;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.springframework.web.multipart.MultipartFile;
//
//public class ExcelStudentHelper {
//    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
//    static String[] HEADERs = { "StudentId", "RollNumber", "FullName", "DOB", "Gender", "IDCard", "DateOfIssue", "PlaceOfIssue",
//            "MobilePhone", "Email", "CollegeEmail", "Image", "Major", "Status", "Address", "CreateAt", "UpdateAt", "RemoveAt"};
//    static String SHEET = "Student";
//
//    // Check Format File Excel
//    public static boolean hasExcelFormat(MultipartFile files) {
//        if (!TYPE.equals(files.getContentType())) {
//            return false;
//        }
//        return true;
//    }
//
//    public static List<Student> excelToStudent(InputStream is) {
//
//        try {
//            Workbook workbook   = new XSSFWorkbook(is);
//            // Sheet sheet = workbook.getSheet(SHEET);
//            List<Student> list  = new ArrayList<>();
//            XSSFSheet worksheet = (XSSFSheet) workbook.getSheetAt(0);
//
//            for(int index = 0; index < worksheet.getPhysicalNumberOfRows(); index++){
//
//                if(index > 0){
//
//                    Student student = new Student();
//                    XSSFRow row     = worksheet.getRow(index);
//                    Integer id      = (int) row.getCell(0).getNumericCellValue();
//                    Date dob        = (Date) row.getCell(3).getDateCellValue();
//                    Date doi        = (Date) row.getCell(6).getDateCellValue();
//                    Date createat   = (Date) row.getCell(15).getDateCellValue();
//                    Date updateat   = (Date) row.getCell(16).getDateCellValue();
//
//                    student.setStuid(id); // 0 - Id
//                    student.setRollnum(row.getCell(1).getStringCellValue()); // Rollnum
//                    student.setFullnm(row.getCell(2).getStringCellValue()); // Fullname
//                    student.setDob(dob); // 3 - Dob
//                    student.setGender(row.getCell(4).getStringCellValue());
//                    student.setIdcard(row.getCell(5).getStringCellValue());
//                    student.setDoi(doi); // 6 - Doi
//                    student.setPoi(row.getCell(7).getStringCellValue());
//                    student.setMobphone(row.getCell(8).getStringCellValue());
//                    student.setEmail(row.getCell(9).getStringCellValue());
//                    student.setCollegeemail(row.getCell(10).getStringCellValue());
//                    student.setImg(row.getCell(11).getStringCellValue());
//                    student.setMajor(row.getCell(12).getStringCellValue());
//                    student.setStustatus(row.getCell(13).getStringCellValue());
//                    student.setAddress(row.getCell(14).getStringCellValue());
//                    student.setCreateat(createat); // 15 - Create At
//                    student.setUpdateat(updateat); // 16 - Update At
//                    student.setRemoveat(row.getCell(17).getStringCellValue());
//
//                    list.add(student);
//                }
//            }
//            workbook.close();
//            return list;
//        } catch (IOException e) {
//            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
//        }
//    }
//}

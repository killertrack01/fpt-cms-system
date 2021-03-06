package com.example.fptacademysystem.helper;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.example.fptacademysystem.model.Student;
import com.example.fptacademysystem.model.Subject;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

public class ExcelSubjectHelper {
    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    static String[] HEADERs = { "ShortName", "SubjectName", "Branch"};
    static String SHEET = "Subject";

    // Check Format File Excel
    public static boolean hasExcelFormat(MultipartFile file) {
        if (!TYPE.equals(file.getContentType())) {
            return false;
        }
        return true;
    }

    public static ByteArrayInputStream tutorialsToExcel(List<Subject> subjects) {

        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
            Sheet sheet = workbook.createSheet(SHEET);

            // Header
            Row headerRow = sheet.createRow(0);

            for (int col = 0; col < HEADERs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(HEADERs[col]);
            }

            int rowIdx = 1;
            for (Subject subject : subjects) {
                Row row = sheet.createRow(rowIdx++);
                row.createCell(0).setCellValue(subject.getShortnm());
                row.createCell(1).setCellValue(subject.getSubjnm());
                row.createCell(2).setCellValue(subject.getBranchid());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to import data to Excel file: " + e.getMessage());
        }
    }

    public static List<Subject> excelToSubject(InputStream is) {


        try {
            Workbook workbook = new XSSFWorkbook(is);

            Sheet sheet = workbook.getSheet(SHEET);
            Iterator<Row> rows = sheet.iterator();

            List<Subject> subjects = new ArrayList<Subject>();

            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();

                // skip header
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cellsInRow = currentRow.iterator();

                Subject subject = new Subject();
                subject.setRemoveat("No");

                int cellIdx = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();

                    switch (cellIdx) {
                        case 0:
                            subject.setShortnm(currentCell.getStringCellValue());
                            break;
                        case 1:
                            subject.setSubjnm(currentCell.getStringCellValue());
                            break;
                        case 2:
                            subject.setBranchid(Integer.valueOf((int) currentCell.getNumericCellValue()));
                            break;
                        default:
                            break;
                    }
                    cellIdx++;
                }
                subjects.add(subject);
            }
            workbook.close();

            return subjects;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }
}

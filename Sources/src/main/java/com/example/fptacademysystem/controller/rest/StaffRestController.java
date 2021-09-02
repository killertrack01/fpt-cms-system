package com.example.fptacademysystem.controller.rest;


import com.example.fptacademysystem.dto.StaffAccountDTO;
import com.example.fptacademysystem.dto.StaffDTO;
import com.example.fptacademysystem.model.Staff;
import com.example.fptacademysystem.model.StaffAccount;
import com.example.fptacademysystem.services.LecturerService;
import com.example.fptacademysystem.services.StaffAccountService;
import com.example.fptacademysystem.services.StaffService;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/admin/staff")
public class StaffRestController {

    @Autowired
    StaffService staffService;
    @Autowired
    StaffAccountService saccountSerivce;

    @RequestMapping(value = "/postCreate", produces = "application/json")
    public String postCreate(StaffDTO dto) {
        JSONObject jsonObject = new JSONObject();
        List<Staff> list = staffService.findAll();
        try
        {
            for (Staff staff : list)
            {
                if (staff.getRollnum().equals(dto.getRollnum()))
                {
                    jsonObject.put("title", "error");
                    jsonObject.put("message", "RollNumber Has Existed!");
                    return jsonObject.toString();
                }
                if (staff.getIdcard().equals(dto.getIdcard()))
                {
                    jsonObject.put("title", "error");
                    jsonObject.put("message", "IDCard Has Existed!");
                    return jsonObject.toString();
                }
                if (staff.getEmail().equals(dto.getEmail()))
                {
                    jsonObject.put("title", "error");
                    jsonObject.put("message", "Email Has Existed!");
                    return jsonObject.toString();
                }
                if (staff.getCompanyemail().equals(dto.getCompanyemail()))
                {
                    jsonObject.put("title", "error");
                    jsonObject.put("message", "CompanyEmail Has Existed!");
                    return jsonObject.toString();
                }
                if (staff.getPhone().equals(dto.getPhone()))
                {
                    jsonObject.put("title", "error");
                    jsonObject.put("message", "Phone Has Existed!");
                    return jsonObject.toString();
                }
            }
            MultipartFile file = dto.getImg();
            if (!file.isEmpty())
            {
                try (BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(
                        new File("../cms-academy-training-system/src/main/resources/static/dist/img/staffs",
                                file.getOriginalFilename()))))
                {
                    outputStream.write(file.getBytes());
                    outputStream.flush();
                    outputStream.close();
                } catch (IOException ex)
                {
                    Logger.getLogger(LecturerService.class.getName()).log(Level.SEVERE, null, ex);
                }
                // insert data to database
                staffService.postCreate(dto);

                //find new staff
                Staff staff = staffService.findNewStaff();

                // insert data to staffaccount table
                StaffAccount updateAccount = new StaffAccount();
                updateAccount.setRollnum(dto.getRollnum());
                updateAccount.setPass("123");
                updateAccount.setRemoveat("No");
                Staff s = new Staff();
                s.setStaffid(staff.getStaffid());
                updateAccount.setStaffid(s);
                saccountSerivce.postCreate(updateAccount);

                jsonObject.put("title", "success");
                jsonObject.put("message", "Successfully!");

            } else
            {
                jsonObject.put("title", "error");
                jsonObject.put("message", "Avatar Not Found!");
            }
        } catch (JSONException ex)
        {
            ex.printStackTrace();
        }
        return jsonObject.toString();
    }

    @RequestMapping(value = "/postEdit", produces = "application/json")
    public String postEdit(StaffDTO dto) {
        JSONObject jsonObject = new JSONObject();
        List<Staff> list = staffService.findAll();
        int staffid = Integer.parseInt(dto.getStaffid());
        try
        {
            for (Staff staff : list)
            {
                if (staff.getStaffid() != staffid)
                {
                    if (staff.getRollnum().equals(dto.getRollnum()))
                    {
                        jsonObject.put("title", "error");
                        jsonObject.put("message", "RollNumber Has Existed!");
                        return jsonObject.toString();
                    }
                    if (staff.getIdcard().equals(dto.getIdcard()))
                    {
                        jsonObject.put("title", "error");
                        jsonObject.put("message", "IDCard Has Existed!");
                        return jsonObject.toString();
                    }
                    if (staff.getEmail().equals(dto.getEmail()))
                    {
                        jsonObject.put("title", "error");
                        jsonObject.put("message", "Email Has Existed!");
                        return jsonObject.toString();
                    }
                    if (staff.getCompanyemail().equals(dto.getCompanyemail()))
                    {
                        jsonObject.put("title", "error");
                        jsonObject.put("message", "CompanyEmail Has Existed!");
                        return jsonObject.toString();
                    }
                    if (staff.getPhone().equals(dto.getPhone()))
                    {
                        jsonObject.put("title", "error");
                        jsonObject.put("message", "Phone Has Existed!");
                        return jsonObject.toString();
                    }
                } else
                {
                    break;
                }
            }
            MultipartFile file = dto.getImg();
            if (!file.isEmpty())
            {
                try (BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(
                        new File("../cms-academy-training-system/src/main/resources/static/dist/img/staffs",
                                file.getOriginalFilename()))))
                {
                    outputStream.write(file.getBytes());
                    outputStream.flush();
                    outputStream.close();
                } catch (IOException ex)
                {
                    Logger.getLogger(LecturerService.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            // insert data to database
            staffService.postEdit(dto);

            // cap nhat staff account
            if (!dto.getStaffpass().isEmpty())
            {
                StaffAccount staffAccount = saccountSerivce.findAccountByStaffid(Integer.parseInt(dto.getStaffid()));
                StaffAccount updateAccount = new StaffAccount();
                updateAccount.setStaffaccid(staffAccount.getStaffaccid());
                updateAccount.setRollnum(staffAccount.getRollnum());
                updateAccount.setPass(dto.getStaffpass());
                updateAccount.setRemoveat("No");
                Staff s = new Staff();
                s.setStaffid(Integer.parseInt(dto.getStaffid()));
                updateAccount.setStaffid(s);
                saccountSerivce.postCreate(updateAccount);
            }
            jsonObject.put("title", "success");
            jsonObject.put("message", "Successfully!");
        } catch (JSONException ex)
        {
            ex.printStackTrace();
        }
        return jsonObject.toString();
    }

    @RequestMapping(value = "/findStaffById/{id}", produces = "application/json")
    public String findStaffById(@PathVariable("id") int id) {
        JSONObject jsonObject = new JSONObject();
        try
        {
            Staff staff = staffService.findStaffByStaffid(id);
            jsonObject.put("staffid", staff.getStaffid());
            jsonObject.put("rollnum", staff.getRollnum());
        } catch (JSONException ex)
        {
            Logger.getLogger(StaffRestController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jsonObject.toString();
    }

    @RequestMapping(value = "/updateStaffRollnum", produces = "application/json")
    public String updateStaffRollnum(@RequestBody StaffAccountDTO dto) {
        JSONObject jsonObject = new JSONObject();
        try
        {
            int staffid = Integer.parseInt(dto.getStaffid());
            String rollnum = dto.getRollnum();
            staffService.updateStaffRollnum(rollnum, staffid);
            saccountSerivce.updateStaffRollnum(rollnum, staffid);

            jsonObject.put("title", "success");
            jsonObject.put("message", "Successfully!");
        } catch (JSONException ex)
        {
            Logger.getLogger(StaffRestController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jsonObject.toString();
    }

    @RequestMapping(value = "/deletestaff/{id}")
    public String deleteStaff(@PathVariable("id") int id) {
        JSONObject jSONObject = new JSONObject();
        try
        {
            staffService.deleteStaff(id);
            jSONObject.put("title", "success");
        } catch (JSONException ex)
        {
            Logger.getLogger(StaffRestController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jSONObject.toString();
    }
}
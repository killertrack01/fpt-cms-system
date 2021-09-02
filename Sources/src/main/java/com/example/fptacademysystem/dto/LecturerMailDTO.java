/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.fptacademysystem.dto;

import java.io.Serializable;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author ADMIN
 */
public class LecturerMailDTO implements Serializable{
    private String idsendmail;
    private String mailsubject;
    private String mailmessage;
    private MultipartFile attachments;
    
    public LecturerMailDTO(){}

    public String getIdsendmail() {
        return idsendmail;
    }

    public void setIdsendmail(String idsendmail) {
        this.idsendmail = idsendmail;
    }

    public String getMailsubject() {
        return mailsubject;
    }

    public void setMailsubject(String mailsubject) {
        this.mailsubject = mailsubject;
    }

    public String getMailmessage() {
        return mailmessage;
    }

    public void setMailmessage(String mailmessage) {
        this.mailmessage = mailmessage;
    }

    public MultipartFile getAttachments() {
        return attachments;
    }

    public void setAttachments(MultipartFile attachments) {
        this.attachments = attachments;
    }
    
    
}

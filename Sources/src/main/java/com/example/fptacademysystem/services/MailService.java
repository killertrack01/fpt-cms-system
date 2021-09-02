/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.fptacademysystem.services;

import com.example.fptacademysystem.configuration.Mail;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;
 
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 *
 * @author ADMIN
 */
@Service("mailService")
public class MailService {
    @Autowired
    JavaMailSender mailSender;
    
    @Value("${spring.mail.username}")
    private String adminemail;
 
    public void sendEmail(Mail mail) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
 
        try {
 
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            String mailContent = "";
 
            mimeMessageHelper.setSubject(mail.getMailSubject());
            mimeMessageHelper.setFrom(new InternetAddress(adminemail, "FAP FPT ACADEMY"));
            mimeMessageHelper.setTo(mail.getMailTo());
            
            //mailContent = "<hr><img src='cid:logoImage' />";
            mimeMessageHelper.setText(mail.getMailContent() + mailContent);
            
//            ClassPathResource resource = new ClassPathResource("/static/dis/img/logo.png");
//            mimeMessageHelper.addInline("logoImage", resource);
            
            if(!mail.getAttachments().isEmpty()){
                String fileName = StringUtils.cleanPath(mail.getAttachments().getOriginalFilename());
                
                InputStreamSource source = new InputStreamSource() {
                    @Override
                    public InputStream getInputStream() throws IOException {
                        return mail.getAttachments().getInputStream();
                    }
                };
                mimeMessageHelper.addAttachment(fileName, source);
            }
 
            mailSender.send(mimeMessage);
 
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException ex)
          {
              Logger.getLogger(MailService.class.getName()).log(Level.SEVERE, null, ex);
          }
    }
    
    
     public void sendEmailAdmin(Mail mail) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
 
        try {
 
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            String mailContent = "";
 
            mimeMessageHelper.setSubject(mail.getMailSubject());
            mimeMessageHelper.setFrom(new InternetAddress(mail.getMailFrom()));
            mimeMessageHelper.setTo(adminemail);
            
            //mailContent = "<hr><img src='cid:logoImage' />";
            mimeMessageHelper.setText(mail.getMailContent() + mailContent);
            
//            ClassPathResource resource = new ClassPathResource("/static/dis/img/logo.png");
//            mimeMessageHelper.addInline("logoImage", resource);
            
            if(!mail.getAttachments().isEmpty()){
                String fileName = StringUtils.cleanPath(mail.getAttachments().getOriginalFilename());
                
                InputStreamSource source = new InputStreamSource() {
                    @Override
                    public InputStream getInputStream() throws IOException {
                        return mail.getAttachments().getInputStream();
                    }
                };
                mimeMessageHelper.addAttachment(fileName, source);
            }
 
            mailSender.send(mimeMessage);
 
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}

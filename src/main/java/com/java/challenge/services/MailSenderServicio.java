package com.java.challenge.services;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import java.io.IOException;
import org.springframework.stereotype.Service;

@Service
public class MailSenderServicio {
    
    private String email_sender = ""; //set your email sender here
    
    public void sendMail(String to){
        String key = System.getenv("SENDGRID_API_KEY");  //set a new environment variable called SENDGRID_API_KEY with your sendgrid key value 
        Email from = new Email(email_sender);
        Email toEmail = new Email(to);
        
        String subject = "Bienvenido a mi API";
        Content content = new Content("text/plain", "Bienvenido a mi API de Disney challenge por Alkemy");
        
        Mail mail = new Mail(from, subject, toEmail, content);
        
        SendGrid sendGrid = new SendGrid(key);
        Request request = new Request();
        
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sendGrid.api(request);
        } catch (IOException e) {
            System.out.println("Error trying to send email");
        }
    }
}

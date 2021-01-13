package com.ID0420FF19OWidya.util;

import java.util.Properties;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.Message;
import javax.mail.Address;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Authenticator;
import javax.mail.Session;

public class sendEmail
{
    static final String FROM = "widya.limarto@gmail.com";
    static final String PASS = "123456789??//!!";
    
    public static void sendVerificationEmail(final String email, final String verificationID) {
        final String to = email;
        final String subject = "Thank you for joining us. Please click the link for verification";
        final String body = "http://localhost:8080/ID0420FF19OWidya/register/verification?vid=" + verificationID + "&email=" + email;
        final String sendMailResult = sendemail(to, subject, body);
    }
    
    public static String sendemail(String recipientEmail, String subject, String body) {
		Properties p = System.getProperties();
		p.put("mail.smtp.auth", "true");
		p.put("mail.transport.protocol", "smtp");
		p.put("mail.smtp.host", "smtp.gmail.com");
		p.put("mail.smtp.port", "587");
		p.put("mail.smtp.starttls.enable","true");
		p.put("mail.smtp.starttls.required","true"); 
		p.put("mail.smtp.socketFactory.port","587");
		p.put("mail.smtp.socketFactory.port", "javax.net.ssl.SSLSocketFactory");

		Session session = Session.getDefaultInstance(p,new javax.mail.Authenticator() {  
			@Override    
			protected PasswordAuthentication getPasswordAuthentication() {  
			    return new PasswordAuthentication(FROM,PASS);  
			}  
		});
        try {
            final MimeMessage mes = new MimeMessage(session);
            mes.setFrom((Address)new InternetAddress("widya.limarto@gmail.com"));
            mes.setRecipients(Message.RecipientType.TO, (Address[])InternetAddress.parse(recipientEmail));
            mes.setSubject(subject);
            mes.setText(body);
            Transport.send((Message)mes);
            System.out.println("Sent message successfully....");
            return "success";
        }
        catch (MessagingException e) {
            e.printStackTrace();
            return "error";
        }
    }
}
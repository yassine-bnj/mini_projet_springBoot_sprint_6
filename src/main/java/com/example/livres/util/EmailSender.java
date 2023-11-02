package com.example.livres.util;



import com.sun.mail.smtp.SMTPAddressFailedException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;

import java.util.Properties;

@Service
public class EmailSender {

	
	@Value("${spring.mail.host}")
	private String smtpHost;
    @Value("${spring.mail.port}")
    private String smtpPort;

    @Value("${spring.mail.username}")
    private String smtpUsername;

    @Value("${spring.mail.password}")
    private String smtpPassword;

    @Value("${spring.mail.properties.mail.smtp.auth}")
    private String smtpAuth;

    @Value("${spring.mail.properties.mail.smtp.starttls.enable}")
    private String smtpStartTLS;
	
	   public ResponseEntity<Object> sendEmail(String to, String subject, String body) {
		   // SMTP server configuration
	        Properties properties = new Properties();
	        
	       System.out.println(smtpHost);
	        
	        properties.put("mail.smtp.host", smtpHost);
	        properties.put("mail.smtp.port", "587");
	        properties.put("mail.smtp.starttls.enable", "true");
	        properties.put("mail.smtp.auth", "true");

	        // Sender and recipient information
	        //String from = smtpUsername;

	        // Create a Session object with authentication
	        Session session = Session.getInstance(properties, new Authenticator() {
	            @Override
	            protected PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication("yassinebenjeddou6@gmail.com", "iwgy uccl dbid uaof");
	            }
	        });

	        try {
	            // Create a MimeMessage object
	            MimeMessage message = new MimeMessage(session);
	            message.setFrom(new InternetAddress("yassinebenjeddou6@gmail.com"));
	            message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
	            message.setSubject(subject);
	         // Create a MimeMultipart to handle the email content
	            MimeMultipart multipart = new MimeMultipart();

	            // Create a MimeBodyPart for the HTML content
	            MimeBodyPart htmlPart = new MimeBodyPart();
	            htmlPart.setContent(body, "text/html");

	            // Add the HTML part to the multipart
	            multipart.addBodyPart(htmlPart);

	            // Send the email
	            message.setContent(multipart);

	            Transport.send(message);
	          //  System.out.println("Email sent successfully.");
	        }
			catch (SMTPAddressFailedException e) {
				return new  ResponseEntity<>("{\"message\": \"Email not sent\"}", HttpStatus.BAD_REQUEST);
			}
			catch (SendFailedException e){
				return new  ResponseEntity<>("{\"message\": \"Email not sent\"}", HttpStatus.BAD_REQUEST);
			}

			catch (MessagingException e) {
				e.printStackTrace();
				return new  ResponseEntity<>("{\"message\": \"Email not sent\"}", HttpStatus.BAD_REQUEST);

			}
return  new  ResponseEntity<>("{\"message\": \"Email sent successfully\"}", HttpStatus.OK);
	    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
   /* @Autowired
     JavaMailSender mailSender=new JavaMailSenderImpl();



    public ResponseEntity<String> sendSimpleEmail(String toEmail,
                                                  String subject,
                                                  String body
    ) {



        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("yassinebenjeddou6@gmail.com");
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);
        mailSender.send(message);
        System.out.println("Mail Send...");

        return    ResponseEntity.ok().body("Password reset email sent.");
    }
/*
    public String sendSimpleEmail(String to,String msg,String subject)
    {




        SimpleMailMessage message = new SimpleMailMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(msg );
            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
            //    return ResponseEntity.badRequest().body("Error while sending password reset email.");
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "Error while sending   email.");
        }

        // return ResponseEntity.ok().body("Password reset email sent.");
        return "Password reset email sent.";

    }*/




}

package tech.torbay.securityservice.email;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

public class SpringBootEmail {
	// Send a normal text email
	@Autowired
    private JavaMailSender javaMailSender;
	
	public void sendEmail(String toEmail) {
		
		javaMailSender = getJavaMailSender();

        SimpleMailMessage msg = new SimpleMailMessage();
//	        msg.setTo("mayaclinic@onlinedemo.co");
        msg.setTo(toEmail);
//        msg.setFrom("mayaclinic@onlinedemo.co");

        msg.setSubject("This is Welcome Email From Torbay Tech Condonuity Application");
        msg.setText("Thanks for using our application");

        javaMailSender.send(msg);

    }
	
	
	public void sendWelcomeEmailWithAttachment(String toEmail, String content) throws MessagingException, IOException {
		
		javaMailSender = getJavaMailSender();

        MimeMessage msg = javaMailSender.createMimeMessage();

        // true = multipart message
        MimeMessageHelper helper = new MimeMessageHelper(msg, true);
		
        helper.setTo(toEmail);
        msg.setFrom(new InternetAddress("condonuitydev@gmail.com", "Condonuity"));
        helper.setSubject("Welcome to Condonuity");

        // default = text/plain
        //helper.setText("Check attachment for image!");

        // true = text/html
        helper.setText("<div style=\"width: 100%; border: 1px solid gray;\">\r\n" + 
        		"<div style=\"padding: 1em; color: white; background-color: lightgrey; clear: left; text-align: left;\"><img src=\"http://torbay.tech/wp-content/uploads/2018/01/torbay-dark-3-300x101.png\" width=\"180\" height=\"80\" /></div>\r\n" + 
        		"</div>\r\n" + 
        		"<div style=\"border-left: 0px solid gray; padding: 1em; overflow: hidden;\">\r\n" + 
        		"<div style=\"padding-top: 20px; font-size: 30px; font-weight: bold;\">Wecome to Condonuity</div>\r\n" + 
        		"<div style=\"padding-top: 40px; padding-left: 80px; font-size: 18px;\">Thank you for Registering in Condonuity Application..</div>\r\n" + 
        		"</div>"+
        "<div style=\"padding-top: 40px; padding-left: 80px; font-size: 18px;\">Please verify email to continue Registration\n"
        + content +"</div>\r\n" + 
        "</div>", true);

		// hard coded a file path
        //FileSystemResource file = new FileSystemResource(new File("path/android.png"));

        // Add attachment Here
//        helper.addAttachment("my_photo.png", new ClassPathResource("android.png"));

        javaMailSender.send(msg);

    }
	
	
//	@Bean
//	public JavaMailSender getJavaMailSender() {
//	    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
//	    mailSender.setHost("gator4061.hostgator.com");
////		    mailSender.setPort(587); //ttls
//	    mailSender.setPort(465); //ssl
//	     
//	    mailSender.setUsername("mayaclinic@onlinedemo.co");
//	    mailSender.setPassword("mayaclinic@123");
//	     
//	    Properties props = mailSender.getJavaMailProperties();
//	    props.put("mail.transport.protocol", "smtp");
//	    props.put("mail.smtp.auth", "true");
////		    props.put("mail.smtp.starttls.enable", "true");//ttls port 587
//	    props.put("mail.smtp.ssl.enable", "true");//ssl
//	    props.put("mail.smtp.socketFactory.port", "465"); //SSL Port
//		props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory"); //SSL Factory Class
//	    props.put("mail.debug", "true");
//	     
//	    return mailSender;
//	}
	
	@Bean
	public JavaMailSender getJavaMailSender() {
	    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
	    mailSender.setHost("smtp.gmail.com");
//		    mailSender.setPort(587); //ttls
	    mailSender.setPort(465); //ssl
	     
	    mailSender.setUsername("condonuitydev@gmail.com");
	    mailSender.setPassword("yixtxkshnfaykpsh");
	     
	    Properties props = mailSender.getJavaMailProperties();
	    props.put("mail.transport.protocol", "smtp");
	    props.put("mail.smtp.auth", "true");
//		    props.put("mail.smtp.starttls.enable", "true");//ttls port 587
	    props.put("mail.smtp.ssl.enable", "true");//ssl
	    props.put("mail.smtp.socketFactory.port", "465"); //SSL Port
		props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory"); //SSL Factory Class
	    props.put("mail.debug", "true");
	     
	    return mailSender;
	}


	public void sendPasswordResetEmailWithAttachment(String toEmail, String content) throws MessagingException, IOException  {
		// TODO Auto-generated method stub
		javaMailSender = getJavaMailSender();

        MimeMessage msg = javaMailSender.createMimeMessage();

        // true = multipart message
        MimeMessageHelper helper = new MimeMessageHelper(msg, true);
		
        helper.setTo(toEmail);
        msg.setFrom(new InternetAddress("condonuitydev@gmail.com", "Condonuity"));
        helper.setSubject("Reset your password");

        // default = text/plain
        //helper.setText("Check attachment for image!");

        // true = text/html
        helper.setText("<div style=\"width: 100%; border: 1px solid gray;\">\r\n" + 
        		"<div style=\"padding: 1em; color: white; background-color: lightgrey; clear: left; text-align: left;\"><img src=\"http://condonuityappdev.eastus2.cloudapp.azure.com/assets/images/logos/condo-logo.png\" width=\"180\" height=\"80\" /></div>\r\n" + 
        		"</div>\r\n" + 
        		"<div style=\"border-left: 0px solid gray; padding: 1em; overflow: hidden;\">\r\n" + 
        		"<div style=\"padding-top: 20px; font-size: 30px; font-weight: bold;\">Reset your password</div>\r\n" + 
        		"<div style=\"padding-top: 40px; padding-left: 80px; font-size: 18px;\">You requested to reset the password to your Condonuity application with the email address("+ toEmail +")</div>\r\n" + 
        		"</div>"+
        "<div style=\"padding-top: 40px; padding-left: 80px; font-size: 18px;\">Please click the link to reset your password\n"
        + content +"</div>\r\n" + 
        "<div style=\"padding-top: 40px; padding-left: 80px; font-size: 18px;\">Thanks,\r\n\nCondonuity Team \n</div>\r\n" +
        "</div>", true);


        javaMailSender.send(msg);
	}
} 

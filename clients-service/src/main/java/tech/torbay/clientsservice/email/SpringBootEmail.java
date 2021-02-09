package tech.torbay.clientsservice.email;

import java.io.IOException;
import java.util.List;
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
	
	@Bean
	public JavaMailSender getJavaMailSender() {
	    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
	    mailSender.setHost("smtp.gmail.com");
//		    mailSender.setPort(587); //ttls
	    mailSender.setPort(465); //ssl
	     
//	    mailSender.setUsername("condonuitydev@gmail.com");
//	    mailSender.setPassword("yixtxkshnfaykpsh");
	    mailSender.setUsername("No-Reply@condonuity.ca");
	    mailSender.setPassword("xzawboabvqykafpv");
	     
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

	public void sendUnreadNotificationAlertNotification(String userEmail, String username, long notificationCount, String organisationName,
			String content, String subject) throws MessagingException, IOException {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				javaMailSender = getJavaMailSender();

		        MimeMessage msg = javaMailSender.createMimeMessage();

		        // true = multipart message
		        MimeMessageHelper helper = new MimeMessageHelper(msg, true);
				
		        helper.setTo(userEmail);
		        msg.setFrom(new InternetAddress("no-reply@condonuity.ca", "Condonuity"));
		        helper.setSubject(subject);

		        // default = text/plain
		        //helper.setText("Check attachment for image!");

		        // true = text/html
		        String loginURL = "http://condonuitytest.eastus.cloudapp.azure.com/"; 
		        String header = "Notifications Alert";
		        String loginURLContent = "<div style=\"margin-top:40px;margin-bottom:40px;\"><a href="+ loginURL +" style=\"background:#d84d34;height:40px;color:#fff;padding:20px 40px;text-decoration:none\">Login</a>" +"</div>\r\n" ;
		        helper.setText("<div style=\"width: 100%;\">\r\n" +
		        		"<div style=\"text-align: center;\"><img src=\"http://condonuitytest.eastus.cloudapp.azure.com/assets/images/logos/condo-logo.png\" width=\"227\" height=\"168\" /></div>\r\n" +
		        		"</div>\r\n" +
		        		"<div style=\"width:60%;background:#f7f7f7;text-align:center;margin:0 auto;padding-top:40px;padding-bottom:50px;border-radius:10px;\">\r\n" +
		        		"<div style=\"font-size:30px;font-weight:bold;padding-bottom:30px;color:#373F49;\">"+header+"</div>\r\n" +
		        		"<div style=\"font-size:16px;color:#97a3b4;line-height:32px;padding:2px 20px;\">Hi, "+username+"</div>\r\n" +
						"<div style=\"font-size: 16px;margin-top:30px;color:#373F49;\">Unread Notification Alert From "+organisationName+"\n</div>\r\n" +
		        		
		         "<div style=\"margin-top:40px;background:#fff;margin-bottom:40px;\"><a style=\"min-height:40px;color:#d84d34;padding:20px 10px;text-decoration:none\">"+content+"</a>" +"</div>\r\n" +
//		        "<div style=\"font-size: 16px;color:#97a3b4;\">Thanks,\r\n\nCondonuity Team \n</div>\r\n" +
				loginURLContent+
		        "<div style=\"font-size: 16px;color:#97a3b4;\">do not reply to this email \n</div>\r\n" +
		        "</div>\r\n" +				
						"</div>", true);

		        javaMailSender.send(msg);
	}
} 

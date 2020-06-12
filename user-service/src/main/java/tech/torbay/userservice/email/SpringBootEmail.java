package tech.torbay.userservice.email;

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
//        helper.setText("<div style=\"width: 100%; border: 1px solid gray;\">\r\n" + 
//        		"<div style=\"padding: 1em; color: white; background-color: lightgrey; clear: left; text-align: left;\"><img src=\"http://torbay.tech/wp-content/uploads/2018/01/torbay-dark-3-300x101.png\" width=\"180\" height=\"80\" /></div>\r\n" + 
//        		"</div>\r\n" + 
//        		"<div style=\"border-left: 0px solid gray; padding: 1em; overflow: hidden;\">\r\n" + 
//        		"<div style=\"padding-top: 20px; font-size: 30px; font-weight: bold;\">Welcome to Condonuity</div>\r\n" + 
//        		"<div style=\"padding-top: 40px; padding-left: 80px; font-size: 18px;\">Thank you for Registering in Condonuity Application..</div>\r\n" + 
//        		"</div>"+
//        "<div style=\"padding-top: 40px; padding-left: 80px; font-size: 18px;\">Please verify your email to continue Registration\n"
//        + content +"</div>\r\n" + 
//        "</div>", true);
        helper.setText("<div style=\"width: 100%;\">\r\n" +
        		"<div style=\"text-align: center;\"><img src=\"http://condonuityappdev.eastus2.cloudapp.azure.com/assets/images/logos/condo-logo.png\" width=\"227\" height=\"168\" /></div>\r\n" +
        		"</div>\r\n" +
        		"<div style=\"width:60%;background:#f7f7f7;text-align:center;margin:0 auto;padding-top:40px;padding-bottom:50px;border-radius:10px;\">\r\n" +
        		"<div style=\"font-size:30px;font-weight:bold;padding-bottom:30px;color:#373F49;\">Welcome to Condonuity</div>\r\n" +
        		"<div style=\"font-size:16px;color:#97a3b4;line-height:32px;padding:2px 20px;\">Thank you for you registration request</div>\r\n" +
				"<div style=\"font-size: 16px;margin-top:30px;color:#373F49;\">Please verify your email to continue Registration" +"</div>\r\n" +
        		
         "<div style=\"margin-top:40px;margin-bottom:40px;\"><a href="+ content +" style=\"background:#d84d34;height:40px;color:#fff;padding:20px 40px;text-decoration:none\">VERIFY EMAIL</a>" +"</div>\r\n" +
//        "<div style=\"font-size: 16px;color:#97a3b4;\">Thanks,\r\n\nCondonuity Team \n</div>\r\n" +
        "<div style=\"font-size: 16px;color:#97a3b4;\">*This request will expire after 7 days \n</div>\r\n" +
        "</div>\r\n" +				
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

        helper.setText("<div style=\"width: 100%;\">\r\n" +
        		"<div style=\"text-align: center;\"><img src=\"http://condonuityappdev.eastus2.cloudapp.azure.com/assets/images/logos/condo-logo.png\" width=\"227\" height=\"168\" /></div>\r\n" +
        		"</div>\r\n" +
        		"<div style=\"width:60%;background:#f7f7f7;text-align:center;margin:0 auto;padding-top:40px;padding-bottom:50px;border-radius:10px;\">\r\n" +
        		"<div style=\"font-size:30px;font-weight:bold;padding-bottom:30px;color:#373F49;\">Reset your password</div>\r\n" +
        		"<div style=\"font-size:16px;color:#97a3b4;line-height:32px;padding:2px 20px;\">We've received your request to reset your password related to the email address: (<a style=\"color:#d84d34;\">"+ toEmail +"</a>) </div>\r\n" +
				"<div style=\"font-size: 16px;margin-top:30px;color:#373F49;\">Please click the button to reset your password" +"</div>\r\n" +
        		
         "<div style=\"margin-top:40px;margin-bottom:40px;\"><a href="+ content +" style=\"background:#d84d34;height:40px;color:#fff;padding:20px 40px;text-decoration:none\">RESET PASSWORD</a>" +"</div>\r\n" +
//        "<div style=\"font-size: 16px;color:#97a3b4;\">Thanks,\r\n\nCondonuity Team \n</div>\r\n" +
        "<div style=\"font-size: 16px;color:#97a3b4;\">*This request will expire after 7 days \n</div>\r\n" +
        "</div>\r\n" +				
				"</div>", true);

        javaMailSender.send(msg);
	}
	
public void sendInviteAcceptEmailWithAttachment(String toEmail, String username, String organisationName, String content) throws MessagingException, IOException {
		
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
//        helper.setText("<div style=\"width: 100%; border: 1px solid gray;\">\r\n" + 
//        		"<div style=\"padding: 1em; color: white; background-color: lightgrey; clear: left; text-align: left;\"><img src=\"http://torbay.tech/wp-content/uploads/2018/01/torbay-dark-3-300x101.png\" width=\"180\" height=\"80\" /></div>\r\n" + 
//        		"</div>\r\n" + 
//        		"<div style=\"border-left: 0px solid gray; padding: 1em; overflow: hidden;\">\r\n" + 
//        		"<div style=\"padding-top: 20px; font-size: 30px; font-weight: bold;\">Welcome to Condonuity</div>\r\n" + 
//        		"<div style=\"padding-top: 40px; padding-left: 80px; font-size: 18px;\">Thank you for Registering in Condonuity Application..</div>\r\n" + 
//        		"</div>"+
//        "<div style=\"padding-top: 40px; padding-left: 80px; font-size: 18px;\">Please verify your email to continue Registration\n"
//        + content +"</div>\r\n" + 
//        "</div>", true);
        helper.setText("<div style=\"width: 100%;\">\r\n" +
        		"<div style=\"text-align: center;\"><img src=\"http://condonuityappdev.eastus2.cloudapp.azure.com/assets/images/logos/condo-logo.png\" width=\"227\" height=\"168\" /></div>\r\n" +
        		"</div>\r\n" +
        		"<div style=\"width:60%;background:#f7f7f7;text-align:center;margin:0 auto;padding-top:40px;padding-bottom:50px;border-radius:10px;\">\r\n" +
        		"<div style=\"font-size:30px;font-weight:bold;padding-bottom:30px;color:#373F49;\">Welcome to Condonuity</div>\r\n" +
        		"<div style=\"font-size:16px;color:#97a3b4;line-height:32px;padding:2px 20px;\">Hi, "+username+"</div>\r\n" +
				"<div style=\"font-size: 16px;margin-top:30px;color:#373F49;\">You have received an invitation from "+organisationName +"</div>\r\n" +
        		
         "<div style=\"margin-top:40px;margin-bottom:40px;\"><a href="+ content +" style=\"background:#d84d34;height:40px;color:#fff;padding:20px 40px;text-decoration:none\">ACCEPT INVITE</a>" +"</div>\r\n" +
//        "<div style=\"font-size: 16px;color:#97a3b4;\">Thanks,\r\n\nCondonuity Team \n</div>\r\n" +
        "<div style=\"font-size: 16px;color:#97a3b4;\">This request will expire after 7 days \n</div>\r\n" +
        "</div>\r\n" +				
				"</div>", true);
        

		// hard coded a file path
        //FileSystemResource file = new FileSystemResource(new File("path/android.png"));

        // Add attachment Here
//        helper.addAttachment("my_photo.png", new ClassPathResource("android.png"));

        javaMailSender.send(msg);

    }


	public void sendUserAlertEmailForRemovalFromSystem(String userEmail, String username, List<String> organisationName,
			String content) throws MessagingException, IOException {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				javaMailSender = getJavaMailSender();

		        MimeMessage msg = javaMailSender.createMimeMessage();

		        // true = multipart message
		        MimeMessageHelper helper = new MimeMessageHelper(msg, true);
				
		        helper.setTo(userEmail);
		        msg.setFrom(new InternetAddress("condonuitydev@gmail.com", "Condonuity"));
		        helper.setSubject("User Account Status Update");

		        // default = text/plain
		        //helper.setText("Check attachment for image!");

		        // true = text/html

		        helper.setText("<div style=\"width: 100%;\">\r\n" +
		        		"<div style=\"text-align: center;\"><img src=\"http://condonuityappdev.eastus2.cloudapp.azure.com/assets/images/logos/condo-logo.png\" width=\"227\" height=\"168\" /></div>\r\n" +
		        		"</div>\r\n" +
		        		"<div style=\"width:60%;background:#f7f7f7;text-align:center;margin:0 auto;padding-top:40px;padding-bottom:50px;border-radius:10px;\">\r\n" +
		        		"<div style=\"font-size:30px;font-weight:bold;padding-bottom:30px;color:#373F49;\">Alert From Condonuity</div>\r\n" +
		        		"<div style=\"font-size:16px;color:#97a3b4;line-height:32px;padding:2px 20px;\">Hi, "+username+"</div>\r\n" +
						"<div style=\"font-size: 16px;margin-top:30px;color:#373F49;\">Your User Account Status Update Alert for following Organisations, \n"+String.join(", ", organisationName) +"</div>\r\n" +
		        		
		         "<div style=\"margin-top:40px;margin-bottom:40px;\"><a href="+ content +" style=\"background:#fff;height:40px;color:#d84d34;padding:20px 40px;text-decoration:none\">"+content+"</a>" +"</div>\r\n" +
//		        "<div style=\"font-size: 16px;color:#97a3b4;\">Thanks,\r\n\nCondonuity Team \n</div>\r\n" +
		        "<div style=\"font-size: 16px;color:#97a3b4;\">do not reply to this email \n</div>\r\n" +
		        "</div>\r\n" +				
						"</div>", true);

		        javaMailSender.send(msg);
	}
	
	public void sendOrganisationAlertEmailForRemovalFromSystem(String userEmail, String organisationName,
			String content) throws MessagingException, IOException {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				javaMailSender = getJavaMailSender();

		        MimeMessage msg = javaMailSender.createMimeMessage();

		        // true = multipart message
		        MimeMessageHelper helper = new MimeMessageHelper(msg, true);
				
		        helper.setTo(userEmail);
		        msg.setFrom(new InternetAddress("condonuitydev@gmail.com", "Condonuity"));
		        helper.setSubject("Organisation Account Status Update");

		        // default = text/plain
		        //helper.setText("Check attachment for image!");

		        // true = text/html

		        helper.setText("<div style=\"width: 100%;\">\r\n" +
		        		"<div style=\"text-align: center;\"><img src=\"http://condonuityappdev.eastus2.cloudapp.azure.com/assets/images/logos/condo-logo.png\" width=\"227\" height=\"168\" /></div>\r\n" +
		        		"</div>\r\n" +
		        		"<div style=\"width:60%;background:#f7f7f7;text-align:center;margin:0 auto;padding-top:40px;padding-bottom:50px;border-radius:10px;\">\r\n" +
		        		"<div style=\"font-size:30px;font-weight:bold;padding-bottom:30px;color:#373F49;\">Alert From Condonuity</div>\r\n" +
		        		"<div style=\"font-size:16px;color:#97a3b4;line-height:32px;padding:2px 20px;\">Hi, "+organisationName+"</div>\r\n" +
						"<div style=\"font-size: 16px;margin-top:30px;color:#373F49;\">Your Organisation Account Status Update Alert \n</div>\r\n" +
		        		
		         "<div style=\"margin-top:40px;margin-bottom:40px;\"><a href="+ content +" style=\"background:#fff;height:40px;color:#d84d34;padding:20px 40px;text-decoration:none\">"+content+"</a>" +"</div>\r\n" +
//		        "<div style=\"font-size: 16px;color:#97a3b4;\">Thanks,\r\n\nCondonuity Team \n</div>\r\n" +
		        "<div style=\"font-size: 16px;color:#97a3b4;\">do not reply to this email \n</div>\r\n" +
		        "</div>\r\n" +				
						"</div>", true);

		        javaMailSender.send(msg);
	}
	
} 

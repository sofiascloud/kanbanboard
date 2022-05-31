package com.niit.notificationapplication.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;


import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Component
public class SmtpMailSender {

	@Autowired
	private JavaMailSender javaMailSender;	//instance of the JavaMailSender, supports MIMEMessage


	public void send(String to, String subject, String text) throws MessagingException {
		
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper; //helper class for the creation of MIME messages.
		// It offers support for images,
		// typical mail attachments and text content in an HTML layout.

		helper = new MimeMessageHelper(message, true);
		helper.setSubject(subject);
		helper.setTo(to);
		helper.setText(text, true);

		javaMailSender.send(message);

	}

}

package com.deb.flightreservation.util;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.deb.flightreservation.Controller.FlightController;

@Component
public class EmailUtil {
	
	@Value("${com.deb.flightreservation.itinerary.email.text}")
	private String EMAIL_TEXT = "Please find the attachment";
	
	@Value("${com.deb.flightreservation.itinerary.email.subject}")
	private String EMAIL_SUBJECT = "Itinerary for your flight";
	private Logger LOGGER=LoggerFactory.getLogger(EmailUtil.class);
	@Autowired
	private JavaMailSender sender;
	
	public void sendItinerary(String toAddress,String filePath)
	{
		LOGGER.info("Inside send itinerary :");
	
		MimeMessage message=sender.createMimeMessage();
		try {
			MimeMessageHelper messageHelper=new MimeMessageHelper(message,true);
			messageHelper.setTo(toAddress);
			messageHelper.setSubject(EMAIL_SUBJECT);
			messageHelper.setText(EMAIL_TEXT);
			messageHelper.addAttachment("Itinerary",new File(filePath));
			sender.send(message);
		}
		catch(MessagingException e)
		{
			LOGGER.error("Inside send Itinerary"+e);
		}
		
	}

}

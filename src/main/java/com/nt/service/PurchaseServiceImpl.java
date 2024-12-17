package com.nt.service;

import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.MimeMessage;

@Service
public class PurchaseServiceImpl implements IPurchaseMgmtService{

	@Autowired
	private JavaMailSender sender;
	
	@Value("${spring.mail.username}")
	private String fromEmailId;
	
	@Override
	public String shopping(String[] items, Double[] prices, String[] toemailsIds) throws Exception{
		//calculate the bill amount
		double totAmt = 0.0;
		for(double p : prices)
		{
			totAmt = totAmt + p;
		}
		String msg1 = Arrays.toString(items)+" Are Purchased having Prices"+Arrays.toString(prices)+" with the bill Amt"+totAmt;
		//trigger the email msg
		String msg2 = sendMail(msg1, toemailsIds, fromEmailId);
		
		
		return msg1+"...."+msg2;
	}
	
	private String sendMail(String msg, String [] toEmailIds, String fromEmailIds) throws Exception
	{
		MimeMessage message = sender.createMimeMessage();//represents email message
		MimeMessageHelper helper = new MimeMessageHelper(message, true);//allowing attachments
		//setting the header value
		helper.setSentDate(new Date());
		
		helper.setCc(toEmailIds);
		helper.setSubject("Open it to know it");
		helper.setText(msg);
		helper.addAttachment("k.jpg", new ClassPathResource("k.jpg"));
		
		//send message
		sender.send(message);
		return "Email Message is sent...";
	}

}

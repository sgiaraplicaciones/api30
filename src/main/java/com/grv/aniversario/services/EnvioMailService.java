package com.grv.aniversario.services;

import java.util.Date;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.*;

import org.springframework.stereotype.Service;



@Service
public class EnvioMailService {
	
		public String sendEmail(Session session, String toEmail, String subject, String body){
			try
		    {
		      MimeMessage msg = new MimeMessage(session);
		      //set message headers
		      msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
		      msg.addHeader("format", "flowed");
		      msg.addHeader("Content-Transfer-Encoding", "8bit");

		      msg.setFrom(new InternetAddress("no_responder@sgiar.org.ar", "SGIAR"));

		      //msg.setReplyTo(InternetAddress.parse("no_reply@example.com", false));

		      msg.setSubject(subject, "UTF-8");

		      msg.setText(body, "UTF-8", "html");

		      msg.setSentDate(new Date());

		      msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
		      //System.out.println("Message is ready");
	    	  Transport.send(msg);  

		      return "EMail Sent Successfully!!";
		    }
		    catch (Exception e) {
		      e.printStackTrace();
		      return "ERROR Sending mail!!";
		    }
		
    }
    
}



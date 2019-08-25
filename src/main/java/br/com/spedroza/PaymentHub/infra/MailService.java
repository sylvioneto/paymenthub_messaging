package br.com.spedroza.PaymentHub.infra;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.stereotype.Service;

@Service
public class MailService {

	public void sendMail(String subject, String fromAddress, String fromName, 
						 String toAddress, String textBody,
						 String username, String password) throws EmailException {
		System.out.println("Begin of MailService.sendMail...");
		Email mailMessage = new SimpleEmail();
		
		// setting server configuration
		mailMessage.setHostName("smtp.mail.yahoo.com");
		mailMessage.setSmtpPort(465);
		mailMessage.setAuthenticator(new DefaultAuthenticator(username, password));
		mailMessage.setSSLOnConnect(true);
		mailMessage.setDebug(true);
		mailMessage.setStartTLSEnabled(true);
		
		// setting message
		mailMessage.setSubject(subject);
		mailMessage.setFrom(fromAddress, fromName);
		mailMessage.addTo(toAddress);
		mailMessage.setMsg(textBody);
		
		mailMessage.send();
		System.out.println("Enf of MailService.sendMail...");
	}
}

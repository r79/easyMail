package io.r79.easyMail;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EasyMail {

	private String from;
	private String to;
	private String smtp;
	private String subject;
	private String text;
	private boolean builded;
	private MimeMessage message;

	public EasyMail(String from, String to, String smtp) {
		this.from = from;
		this.to = to;
		this.smtp = smtp;
		this.subject = "";
		this.text = "";
	}
	
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	private void buildMail() throws MessagingException {
		Properties properties = System.getProperties();

		properties.setProperty("mail.smtp.host", smtp);

		Session session = Session.getDefaultInstance(properties);

		message = new MimeMessage(session);
		message.setFrom(new InternetAddress(from));

		// Set To: header field of the header.
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

		message.setSubject(subject);
		message.setText(text);
	}
	
	public void buildAndSendMail() throws MessagingException {
		if(!builded) {
			buildMail();
		}
		Transport.send(message);
	}
}

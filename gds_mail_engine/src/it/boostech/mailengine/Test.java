package it.boostech.mailengine;

import java.io.IOException;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Test extends HttpServlet {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private String html = "Mail di prova";
	private String subject = "Test mail";

	String from = "noreply@gds-italia.it";
	String host = "mail.gds-italia.it";
	String user = "gabriele.r@gds-italia.it";
	String pwd = "02478080183sofia";

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			Properties properties = System.getProperties();
			properties.setProperty("mail.smtp.host", host);
			properties.setProperty("mail.smtp.port", "587");
			properties.setProperty("mail.smtp.user", user);
			properties.setProperty("mail.smtp.auth", "true");

			Session session = Session.getDefaultInstance(properties);

			Multipart mhtml = new MimeMultipart();
			BodyPart body = new MimeBodyPart();
			body.setContent(html, "text/html");
			mhtml.addBodyPart(body);

			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));

			message.addRecipient(Message.RecipientType.TO, new InternetAddress(request.getParameter("email")));
			message.setSubject(subject);
			message.setContent(mhtml);

			Transport t = session.getTransport("smtp");
	        t.connect((String) properties.get("mail.smtp.user"), pwd);
	        t.sendMessage(message, message.getAllRecipients());
	        t.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

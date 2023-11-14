package it.boostech.mailengine;

import java.security.GeneralSecurityException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.TimerTask;

import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.Servlet;
import javax.servlet.ServletContext;

import com.sun.mail.util.MailSSLSocketFactory;

import it.boostech.utils.Config;
import it.boostech.utils.Constants;
import it.boostech.utils.ReadProperty;

public class SendMail extends TimerTask implements Constants {
	private String html;
	private String subject = "";
	private String recipients = "";

	private ServletContext context;
	String from = "noreply@gds-italia.it";
	String host = "mail.gds-italia.it";
	String user = "noreply@gds-italia.it";
	String pwd = "gabriele2014";
	Properties properties = System.getProperties();

	public SendMail() {
		super();
		properties.setProperty("mail.smtp.host", host);
		properties.setProperty("mail.smtp.port", "587");
		properties.setProperty("mail.smtp.user", user);
		properties.setProperty("mail.smtp.auth", "true");
	}

	public SendMail(ServletContext servletContext) {
		properties.setProperty("mail.smtp.host", host);
		properties.setProperty("mail.smtp.port", "587");
		properties.setProperty("mail.smtp.user", user);
		properties.setProperty("mail.smtp.auth", "true");
		this.context = servletContext;
	}

	@Override
	public void run() {
		if (!((Boolean) context.getAttribute(RUN)) == false) {
			String id = null;
			Session session = Session.getDefaultInstance(properties);
			Connection conn = null;
			Statement stmt1 = null;
			Statement stmt2 = null;
			String log = "";

			SimpleDateFormat dateform = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");

			MailSSLSocketFactory sf = null;
			try {
				sf = new MailSSLSocketFactory();
				sf.setTrustAllHosts(true);
			} catch (GeneralSecurityException e) {
				e.printStackTrace();
			}

			properties.put("mail.smtp.starttls.enable", "true");
			properties.put("mail.smtp.auth", "true");
			properties.put("mail.smtp.ssl.trust", "*");
			properties.put("mail.smtp.ssl.socketFactory", sf);

			try {
				conn = DriverManager.getConnection(
						Config.getJDBCConn(),
						Config.getJDBCUSR(),
						Config.getJDBCPass());
				stmt1 = conn.createStatement();
				stmt2 = conn.createStatement();
				ResultSet rs = stmt1.executeQuery(
						" SELECT * FROM email_queue "
						+ "	where sended = 0 "
						+ " ORDER BY id LIMIT 0,"
						+ Config.getNumberOfMessages());
				log = (String) context.getAttribute("maillog");

				if (log == null) {
					log = "";
				}

				while (rs.next()) {
					html = rs.getString("html");
					subject = rs.getString("subject");
					id = rs.getString("id");

					if (Config.isDebug() == true) {
						recipients = Config.getMAIL_CONTROL();
						recipients = "marco.tripolini@gmail.com";
					} else {
						recipients = rs.getString("recipients");
					}

					Multipart mhtml = new MimeMultipart();
					BodyPart body = new MimeBodyPart();
					body.setContent(html, "text/html");
					mhtml.addBodyPart(body);
					// ********************************************************************************
					MimeMessage message = new MimeMessage(session);
					message.setFrom(new InternetAddress(from));
					// ********************************************************************************
					message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipients));
					message.setSubject(subject);
					message.setContent(mhtml);
					// ********************************************************************************
					Transport t = session.getTransport("smtp");
					t.connect((String) properties.get("mail.smtp.user"), pwd);
					try {
						t.sendMessage(message, message.getAllRecipients());
						stmt2.executeUpdate("update email_queue set sended = 1, sended_at='"
								+ new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()) + "', "
								+ "real_recipients='" + recipients + "' " + "where id =" + id);
					} catch (javax.mail.SendFailedException e) {
						stmt2.executeUpdate("update email_queue set sended = -1, sended_at='"
								+ new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()) + "' "
								+ "real_recipients='" + recipients + "' " + "where id =" + id);
					}

					t.close();
					log += "messaggio " + id + " inviato alle " + dateform.format(new Date()) + "<br>";

					System.out.println("messaggio " + id + " inviato alle " + dateform.format(new Date()));

					// ********************************************************************************
					Thread.sleep(Integer.parseInt(Config.getActivationDelay()));

					if (log.length() > 1140) {
						log = log.substring(log.lastIndexOf("<br>"), log.length());
					}
				}

				log += (dateform.format(new Date()) + " tick " + "<br>" );

				System.out.println(dateform.format(new Date()) + " mail_engine tick" );

				if (log.length() > 1140) {
					log = log.substring(log.lastIndexOf("<br>"), log.length());
				}

				context.setAttribute("maillog", log);

				rs.close();
				stmt1.close();
				stmt2.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}

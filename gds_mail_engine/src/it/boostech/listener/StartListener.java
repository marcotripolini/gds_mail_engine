package it.boostech.listener;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.*;

import it.boostech.mailengine.SendMail;
import it.boostech.utils.Config;
import it.boostech.utils.Constants;
import it.boostech.utils.ReadProperty;

public class StartListener implements ServletContextListener, Constants {

	Timer timer = new Timer();

	public void contextInitialized(ServletContextEvent event) {
		ServletContext sc = event.getServletContext();
		String realpath = "";
		realpath = event.getServletContext().getRealPath("/");
		event.getServletContext().setAttribute(RUN, new Boolean (true));
		System.out.println ("Nome dell'applicazione: " + event.getServletContext().getServletContextName());
		System.out.println ("Path reale : " + realpath);
	    new ReadProperty(realpath, event.getServletContext().getServletContextName());

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch(Exception e) {
			e.printStackTrace();
		}

		if (Config.getInvio() == true) {
			TimerTask task = new SendMail(event.getServletContext());
//			timer.scheduleAtFixedRate(new TaskSMSSede01(sc), cal.getTime(), 1000 * period);

			timer.scheduleAtFixedRate(task,
					Integer.parseInt(Config.getActivationDelay()),
					Integer.parseInt(Config.getActivationPeriod()));
		}
	}

	public void contextDestroyed(ServletContextEvent event) {
		timer.cancel();
	}

	public boolean exists(String URLName) {
		try {
			HttpURLConnection.setFollowRedirects(false);
			// note : you may also need
			// HttpURLConnection.setInstanceFollowRedirects(false)
			HttpURLConnection conn = (HttpURLConnection) new URL(URLName).openConnection();
			conn.setRequestMethod("HEAD");
			return (conn.getResponseCode() == HttpURLConnection.HTTP_OK);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
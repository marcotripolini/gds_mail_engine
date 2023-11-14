package it.boostech.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.boostech.utils.Config;

/**
 * Servlet implementation class Unsubscribe
 */
public class Unsubscribe extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Unsubscribe() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		String uuid = request.getParameter("uuid");
		int result = -1;

		// devo eseguire l'unsubscribe
		if (uuid != null && !(uuid.equals(""))) {
			request.setAttribute("uuid", uuid);
			request.setAttribute("result", result);
		}

		if (uuid.length() != 40) {
			request.getRequestDispatcher("error.jsp").forward(request, response);
			return;
		}

	    Connection conn = null;
		Statement stmt1 = null;

		try {
			conn = DriverManager.getConnection( Config.getJDBCConn(),
												Config.getJDBCUSR(),
												Config.getJDBCPass());
			stmt1 = conn.createStatement();

			String query = "update email_stack set unsubscribe = 1 where uuid='" + uuid + "'";
			result = stmt1.executeUpdate(query);

			stmt1.close();
			conn.close();
		} catch(Exception e) {
			e.printStackTrace();
		}


		request.getRequestDispatcher("unsubscribe.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

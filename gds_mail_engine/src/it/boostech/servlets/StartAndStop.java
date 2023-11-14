package it.boostech.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.boostech.utils.Config;
import it.boostech.utils.Constants;

public class StartAndStop extends HttpServlet implements Constants {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public StartAndStop() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uname = request.getParameter("uname");
		String passw = request.getParameter("passw");
		String action = request.getParameter("action");
		if (checkUser(uname, passw) == true) {
			Boolean status = (Boolean) request.getServletContext().getAttribute(RUN);

			if (action.equals("true")) {
				status = true;
			} else {
				status = false;
			}
			request.getServletContext().setAttribute(RUN, status);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private boolean checkUser(String uname, String passw) {

		int result = 0;
		try {
			Connection conn = DriverManager.getConnection( Config.getJDBCConn(),
												Config.getJDBCUSR(),
												Config.getJDBCPass());
			Statement stmt1 = conn.createStatement();
			ResultSet rs = stmt1.executeQuery(
					" SELECT count(*) as quanti "
					+ " FROM admin_users where "
					+ " username = '" + uname + "' "
					+ " and password = '" + passw  + "'");
			while (rs.next()) {
				result = rs.getInt("quanti");
			}
			stmt1.close();
			rs.close();
			conn.close();
		} catch(Exception e) {
			e.printStackTrace();
		}

		if (result == 1) {
			return true;
		} else {
			return false;
		}
	}
}

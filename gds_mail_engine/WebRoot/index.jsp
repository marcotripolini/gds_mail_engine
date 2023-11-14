<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="refresh" content="10"/>

<title>GDS MAIL ENGINE</title>
</head>
<body>
<h2>

	In questo momento il server &egrave;
	<%
		if (((Boolean)pageContext.getServletContext().getAttribute("RUN")) == true ){
			out.print("ATTIVO");
		} else {
			out.print("DISATTIVO");
		}
	%>

	e sono le ore:

	<%
		out.print((new java.text.SimpleDateFormat("HH:mm:ss dd/MM/yyyy")).format(new java.util.Date()));
	%>
</h2>

	<br>
	<hr>
	BOOSTECH MAIL ENGINE<br>
	<%=pageContext.getServletContext().getAttribute("maillog")
	%>
</body>
</html>
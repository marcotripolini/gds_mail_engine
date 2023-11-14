package it.boostech.mailengine;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class writeMail extends HttpServlet {
	/**
	 *
	 */
	private static final long serialVersionUID = -6458027697063522055L;
	private String text = "";
	private String subject = "";
	private String html = "";
	private String recipients = "";

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setHeader("Access-Control-Allow-Origin", "*");

		try {
			String cmd = request.getParameter("cmd");

			Connection conn = DriverManager.getConnection("jdbc:mysql://mysql.gds-italia.it:3306/gds-i41_master","gds-i41_dbuser", "dbuser01");
			Statement stmt = conn.createStatement();
			String query = "SELECT * FROM email_new WHERE email_new.key = '" + cmd + "'";
			ResultSet rs = stmt.executeQuery(query);

			//response.getWriter().println(cmd);

			if(rs.next()) {
				switch(cmd) {
					case "NUOVA_CARD_RICHIEDE":
					case "GDSITA_NUOVA_CARD_RICHIEDE":
						text = "Text version of email";
						subject = rs.getString("title");
						html = rs.getString("email");
						html = html.replace("__sha1__", request.getParameter("sha1"))
						.replace("__gdscard__", request.getParameter("gdscard"))
						.replace("__password__", request.getParameter("password"))
						.replace("__nome__", request.getParameter("nome"))
						.replace("__cognome__", request.getParameter("cognome"))
						.replace("__email__", request.getParameter("email"))
						.replace("__telefono_a__", request.getParameter("telefono_a"))
						.replace("__indirizzo__", request.getParameter("indirizzo"))
						.replace("__cap__", request.getParameter("cap"))
						.replace("__localita__", request.getParameter("comune"))
						.replace("__provincia__", request.getParameter("provincia"));

						break;

					case "NUOVA_CARD_RICHIEDE_AMMIN" :

						text = "Text version of email";
						subject = rs.getString("title");
						html = rs.getString("email");

						subject = subject.replace("__nome__", request.getParameter("nome"))
						.replace("__cognome__", request.getParameter("cognome"));

						html = html.replace("__gdscard__", request.getParameter("gdscard"))
						.replace("__password__", request.getParameter("password"))
						.replace("__nome__", request.getParameter("nome"))
						.replace("__cognome__", request.getParameter("cognome"))
						.replace("__email__", request.getParameter("email"))
						.replace("__telefono_a__", request.getParameter("telefono_a"))
						.replace("__indirizzo__", request.getParameter("indirizzo"))
						.replace("__cap__", request.getParameter("cap"))
						.replace("__localita__", request.getParameter("comune"))
						.replace("__provincia__", request.getParameter("provincia"));

						break;

					case "NUOVA_CARD_REGISTRA_PROVV" :
					case "NUOVA_CARD_REGISTRA" :

						text = "Text version of email";
						subject = rs.getString("title");
						html = rs.getString("email");

						html = html.replace("__sha1__", request.getParameter("sha1"))
						.replace("__gdscard__", request.getParameter("gdscard"))
						.replace("__password__", request.getParameter("password"))
						.replace("__nome__", request.getParameter("nome"))
						.replace("__cognome__", request.getParameter("cognome"))
						.replace("__email__", request.getParameter("email"))
						.replace("__telefono__", request.getParameter("telefono_a"))
						.replace("__indirizzo__", request.getParameter("indirizzo"))
						.replace("__cap__", request.getParameter("cap"))
						.replace("__comune__", request.getParameter("comune"))
						.replace("__provincia__", request.getParameter("provincia"));

						break;

					case "CAMBIO_PASSWORD" :

						text = "Text version of email";
						subject = rs.getString("title");
						html = rs.getString("email");

						html = html.replace("__sha1__", request.getParameter("codice_controllo"))
						.replace("__gdscard__", request.getParameter("gdscard"))
						.replace("__nome__", request.getParameter("nome"))
						.replace("__cognome__", request.getParameter("cognome"))
						.replace("__email__", request.getParameter("email"))
						.replace("__telefono__", request.getParameter("telefono_a"))
						.replace("__indirizzo__", request.getParameter("indirizzo"))
						.replace("__cap__", request.getParameter("cap"))
						.replace("__comune__", request.getParameter("comune"))
						.replace("__provincia__", request.getParameter("provincia"));

						break;

					case "NUOVA_CARD_REGISTRA_AMMIN" :

						text = "Text version of email";
						subject = rs.getString("title");
						html = rs.getString("email");

						subject = subject.replace("__nome__", request.getParameter("nome"))
						.replace("__cognome__", request.getParameter("cognome"));

						html = html.replace("__gdscard__", request.getParameter("tessera"))
						.replace("__nome__", request.getParameter("nome"))
						.replace("__cognome__", request.getParameter("cognome"))
						.replace("__email__", request.getParameter("email"))
						.replace("__telefono__", request.getParameter("telefono_a"))
						.replace("__cap__", request.getParameter("cap"))
						.replace("__comune__", request.getParameter("comune"))
						.replace("__provincia__", request.getParameter("provincia"));

						break;

					case "CAMBIO_PASSWORD_AMMIN" :

						text = "Text version of email";
						subject = rs.getString("title");
						html = rs.getString("email");

						subject = subject.replace("__nome__", request.getParameter("nome"))
						.replace("__cognome__", request.getParameter("cognome"));

						html = html.replace("__gdscard__", request.getParameter("tessera"))
						.replace("__nome__", request.getParameter("nome"))
						.replace("__cognome__", request.getParameter("cognome"));

						break;

					case "RISPOSTA_ACQUISTO" :
					case "RISPOSTA_ACQUISTO_AMMIN" :
					case "PRENOTAZIONE_SPESA_CLIENTE":
					case "PRENOTAZIONE_SPESA_AMMIN":
					case "GDSITA_RISPOSTA_ACQUISTO" :
					case "GDSITA_PRENOTAZIONE_SPESA_CLIENTE":

						text = "Text version of email";
						subject = rs.getString("title");
						html = rs.getString("email");

						subject = subject.replace("__nome__", request.getParameter("nome"))
						.replace("__cognome__", request.getParameter("cognome"))
						.replace("__ordine__", request.getParameter("ordine"))
						.replace("__dataordine__", request.getParameter("dataordine"));

						String dett = "";
						String[] bufdett = request.getParameter("elencoarticoli").split("\\;");
						for(String d : bufdett) {
							dett += "<tr>";
							String[] bd = d.split("\\|");
							if(bd[0] != "") {
								dett += "<td>" + bd[0] + "</td>";
								dett += "<td align=\"right\">&euro;&nbsp;" + bd[1] + "</td>";
								dett += "<td align=\"center\">" + bd[2] + "</td>";
								dett += "<td align=\"right\">&euro;&nbsp;" + bd[3] + "</td>";
							}
							dett += "</tr>";
						}

						html = html.replace("__ordine__", request.getParameter("ordine"));
						html = html.replace("__dataordine__", request.getParameter("dataordine"));
						html = html.replace("__nome__", request.getParameter("nome"));
						html = html.replace("__cognome__", request.getParameter("cognome"));
						html = html.replace("__localita__", request.getParameter("localita"));
						html = html.replace("__indirizzo__", request.getParameter("indirizzo"));
						html = html.replace("__cap__", request.getParameter("cap"));
						html = html.replace("__provincia__", request.getParameter("provincia"));
						html = html.replace("__d_nominativo__", request.getParameter("d_nominativo"));
						html = html.replace("__d_localita__", request.getParameter("d_localita"));
						html = html.replace("__d_indirizzo__", request.getParameter("d_indirizzo"));
						html = html.replace("__d_cap__", request.getParameter("d_cap"));
						html = html.replace("__d_provincia__", request.getParameter("d_provincia"));
						html = html.replace("__dataindconsegna__", request.getParameter("dataindconsegna"));
						html = html.replace("__fasciaconsegna__", request.getParameter("fasciaconsegna"));
						html = html.replace("__elencoarticoli__", dett);
//						html = html.replace("__totalearticoli__", request.getParameter("totalearticoli"))
						html = html.replace("__codicesconto__", request.getParameter("codicesconto"));
						html = html.replace("__nettomerce__", request.getParameter("nettomerce"));
						html = html.replace("__spesetrasporto__", request.getParameter("spesetrasporto"));

						if (request.getParameter("importo") != null ) {
							html = html.replace("__importo__", request.getParameter("importo"));
						} else {
							html = html.replace("__importo__", "");
						}

						if (request.getParameter("codfiscale") != null ) {
							html = html.replace("__codfiscale__", request.getParameter("codfiscale"));
						} else {
							html = html.replace("__codfiscale__", "");
						}

						break;

					case "COMUNICAZIONI_GDS_AMMIN" :

						text = "Text version of email";
						subject = rs.getString("title");
						html = rs.getString("email");

						subject = subject.replace("__nome__", request.getParameter("nome"))
						.replace("__cognome__", request.getParameter("cognome"))
						.replace("__gdscard__", request.getParameter("gdscard"))
						.replace("__oggetto__", request.getParameter("oggetto"));

						html = html.replace("__nome__", request.getParameter("nome"))
						.replace("__cognome__", request.getParameter("cognome"))
						.replace("__gdscard__", request.getParameter("gdscard"))
						.replace("__indirizzo__", request.getParameter("indirizzo"))
						.replace("__localita__", request.getParameter("localita"))
						.replace("__provincia__", request.getParameter("provincia"))
						.replace("__oggetto__", request.getParameter("oggetto"))
						.replace("__corpomessaggio__", request.getParameter("corpomessaggio"));

						break;

					case "MI_PIACEREBBE" :

						text = "Text version of email";
						subject = rs.getString("title");
						html = rs.getString("email");

						subject = subject.replace("__nome__", request.getParameter("nome"))
						.replace("__cognome__", request.getParameter("cognome"))
						.replace("__gdscard__", request.getParameter("gdscard"));

						html = html.replace("__nome__", request.getParameter("nome"))
						.replace("__cognome__", request.getParameter("cognome"))
						.replace("__gdscard__", request.getParameter("gdscard"))
						.replace("__indirizzo__", request.getParameter("indirizzo"))
						.replace("__localita__", request.getParameter("localita"))
						.replace("__provincia__", request.getParameter("provincia"))
						.replace("__articolo__", request.getParameter("articolo"))
						.replace("__marchio__", request.getParameter("marchio"))
						.replace("__formato__", request.getParameter("formato"))
						.replace("__barcode__", request.getParameter("barcode"));

						break;

					case "COUPON" :

						text = "Text version of email";
						subject = rs.getString("title");
						html = rs.getString("email");

						html = html.replace("__nome__", request.getParameter("nome"))
						.replace("__tessera__", request.getParameter("tessera"))
						.replace("__codice__", request.getParameter("codice"))
						.replace("__valore__", request.getParameter("valore"))
						.replace("__valido_fino_al__", request.getParameter("valido_fino_al"));

						break;

					case "COUPON_AMMIN" :

						text = "Text version of email";
						subject = rs.getString("title");
						html = rs.getString("email");

						subject = subject.replace("__nome__", request.getParameter("nome"))
						.replace("__cognome__", request.getParameter("cognome"))
						.replace("__tessera__", request.getParameter("tessera"));

						html = html.replace("__nome__", request.getParameter("nome"))
						.replace("__cognome__", request.getParameter("cognome"))
						.replace("__tessera__", request.getParameter("tessera"))
						.replace("__codice__", request.getParameter("codice"))
						.replace("__valore__", request.getParameter("valore"))
						.replace("__valido_fino_al__", request.getParameter("valido_fino_al"));

						break;

					case "ASSEGNAZIONE_TESSERA_DEF" :

						text = "Text version of email";
						subject = rs.getString("title");
						html = rs.getString("email");

						html = html.replace("__gdscard__", request.getParameter("gdscard"))
						.replace("__password__", request.getParameter("password"));

						break;

					case "ASSEGNAZIONE_TESSERA_DEF_AMMIN" :

						text = "Text version of email";
						subject = rs.getString("title");
						html = rs.getString("email");

						subject = subject.replace("__nome__", request.getParameter("nome"))
						.replace("__cognome__", request.getParameter("cognome"));

						html = html.replace("__nome__", request.getParameter("nome"))
						.replace("__cognome__", request.getParameter("cognome"))
						.replace("__gdscard__", request.getParameter("gdscard"));

						break;

					case "UPD_PERSONAL" :

						text = "Text version of email";
						subject = rs.getString("title");
						html = rs.getString("email");

						html = html.replace("__nome__", request.getParameter("nome"))
						.replace("__cognome__", request.getParameter("cognome"))
						.replace("__tessera__", request.getParameter("tessera"))
						.replace("__email__", request.getParameter("email"))
						.replace("__password__", request.getParameter("password"));

						break;

					case "UPD_BILLING" :

						text = "Text version of email";
						subject = rs.getString("title");
						html = rs.getString("email");

						html = html.replace("__nome__", request.getParameter("nome"))
						.replace("__cognome__", request.getParameter("cognome"))
						.replace("__tessera__", request.getParameter("tessera"))
						.replace("__email__", request.getParameter("email"))
						.replace("__indirizzo__", request.getParameter("indirizzo"))
						.replace("__cap__", request.getParameter("cap"))
						.replace("__comune__", request.getParameter("comune"))
						.replace("__provincia__", request.getParameter("provincia"));

						break;

					case "UPD_DELIVERY" :

						text = "Text version of email";
						subject = rs.getString("title");
						html = rs.getString("email");

						html = html.replace("__nome__", request.getParameter("nome"))
						.replace("__cognome__", request.getParameter("cognome"))
						.replace("__tessera__", request.getParameter("tessera"))
						.replace("__email__", request.getParameter("email"))
						.replace("__indirizzo__", request.getParameter("indirizzo"))
						.replace("__cap__", request.getParameter("cap"))
						.replace("__comune__", request.getParameter("comune"))
						.replace("__provincia__", request.getParameter("provincia"));

						break;

				}

				if(rs.getString("to") != null)
					recipients = rs.getString("to");
				else
					recipients = request.getParameter("email");

				html = html.replace("'", "&#96;")
				.replace("Ò", "&Ograve;")
				.replace("È", "&Egrave;")
				.replace("É", "&Eacute;")
				.replace("Ì", "&Igrave;")
				.replace("À", "&Agrave;")
				.replace("Ù", "&Ugrave;")
				.replace("ò", "&ograve;")
				.replace("è", "&egrave;")
				.replace("é", "&eacute;")
				.replace("ì", "&igrave;")
				.replace("à", "&agrave;")
				.replace("ù", "&ugrave;");

				boolean test = stmt.execute("INSERT INTO email_queue (subject, recipients, html, text) VALUES ('" + subject + "', '" + recipients + "', '" + html + "', '" + text + "')");

				if(test == false) {
					response.getWriter().print("1");
				} else {
					response.getWriter().print(test);
				}
			}

			stmt.close();
			conn.close();

		} catch(Exception e) {
			e.printStackTrace(response.getWriter());
		}
	}
}

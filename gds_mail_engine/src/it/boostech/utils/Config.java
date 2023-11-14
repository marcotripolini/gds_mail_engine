package it.boostech.utils;

public class Config {
	static boolean debug;

	public static boolean isDebug() {
	    ReadProperty r = ReadProperty.getInstance();
	    if (r.getProperty("debug").equals("true")){
	    	return true;
	    } else if (r.getProperty("debug").equals("false")){
	    	return false;
	    }
		return true;
	}

	public static String getJDBCConn(){
	    ReadProperty r = ReadProperty.getInstance();
	    String JDBC_URL = "";
	    // attivazione dell'invio pianificato di statistiche via email
	    if (r.getProperty("debug").equals("true")){
	    	JDBC_URL = r.getProperty("debugJDBC_URL");
	    } else if (r.getProperty("debug").equals("false")){
	    	JDBC_URL = r.getProperty("productionJDBC_URL");
	    }
	    return JDBC_URL;
	}

	public static String getJDBCPass(){
	    ReadProperty r = ReadProperty.getInstance();
	    String pass = "";
	    // attivazione dell'invio pianificato di statistiche via email
	    if (r.getProperty("debug").equals("true")){
	    	pass = r.getProperty("debugDBPWD");
	    } else if (r.getProperty("debug").equals("false")){
	    	pass = r.getProperty("productionDBPWD");
	    }
	    return pass;
	}

	public static String getJDBCUSR(){
	    ReadProperty r = ReadProperty.getInstance();
	    String user = "";
	    // attivazione dell'invio pianificato di statistiche via email
	    if (r.getProperty("debug").equals("true")){
	    	user = r.getProperty("debugDBUSR");
	    } else if (r.getProperty("debug").equals("false")){
	    	user = r.getProperty("productionDBUSR");
	    }
	    return user;
	}

	public static Boolean getAbilitaRivendita(){
	    ReadProperty r = ReadProperty.getInstance();
	    String res = "";
	    Boolean rivendita = false ;
	    // attivazione del meccanismo di rivendita
	    if (r.getProperty("debug").equals("true")){
	    	res = r.getProperty("debugAbilitaRivendita");
	    } else if (r.getProperty("debug").equals("false")){
	    	res = r.getProperty("productionAbilitaRivendita");
	    }
	    if (res.equals('0')) {
	    	return false;
	    } else {
	    	return true;
	    }
	}

	public static Boolean getManutenzione(){
	    ReadProperty r = ReadProperty.getInstance();
	    Boolean result = null;
	    // attivazione dell'invio pianificato di statistiche via email
	    if (r.getProperty("debug").equals("true")){
	    	result = Boolean.parseBoolean(r.getProperty("debugManutenzione"));
	    } else if (r.getProperty("debug").equals("false")){
	    	result = Boolean.parseBoolean(r.getProperty("productionManutenzione"));
	    }
	    return result;
	}

	public static String getMAIL_CONTROL(){
	    ReadProperty r = ReadProperty.getInstance();
	    String user = "";
	    // attivazione dell'invio pianificato di statistiche via email
	    if (r.getProperty("debug").equals("true")){
	    	user = r.getProperty("debugMAIL_CONTROL");
	    } else if (r.getProperty("debug").equals("false")){
	    	user = r.getProperty("productionMAIL_CONTROL");
	    }
	    return user;
	}

	public static String getMailIscrizioneCorso() {
		ReadProperty r = ReadProperty.getInstance();
		String user = "";
		// attivazione dell'invio pianificato di statistiche via email
		if (r.getProperty("debug").equals("true")) {
			user = r.getProperty("debugMailIscrizioneCorso");
		} else if (r.getProperty("debug").equals("false")) {
			user = r.getProperty("productionMailIscrizioneCorso");
		}
		return user;
	}

	public static String getMailIscrizioneCorsoWA() {
		ReadProperty r = ReadProperty.getInstance();
		String user = "";
		// attivazione dell'invio pianificato di statistiche via email
		if (r.getProperty("debug").equals("true")) {
			user = r.getProperty("debugMailIscrizioneCorsoWA");
		} else if (r.getProperty("debug").equals("false")) {
			user = r.getProperty("productionMailIscrizioneCorsoWA");
		}
		return user;
	}

	public static int getPausaMail() {
		ReadProperty r = ReadProperty.getInstance();
		String result = "";
		// attivazione dell'invio pianificato di statistiche via email
		if (r.getProperty("debug").equals("true")) {
			result = r.getProperty("debugPausaMailSec");
		} else if (r.getProperty("debug").equals("false")) {
			result = r.getProperty("productionPausaMailSec");
		}
		return (Integer.parseInt(result) * 1000);
	}

	public static String getLink() {
		ReadProperty r = ReadProperty.getInstance();
		String result = "";
		// attivazione dell'invio pianificato di statistiche via email
		if (r.getProperty("debug").equals("true")) {
			result = r.getProperty("debugLink");
		} else if (r.getProperty("debug").equals("false")) {
			result = r.getProperty("productionLink");
		}
		return result;
	}

	public static String getMailIscrizioneOperatore() {
		ReadProperty r = ReadProperty.getInstance();
		String user = "";
		// attivazione dell'invio pianificato di statistiche via email
		if (r.getProperty("debug").equals("true")) {
			user = r.getProperty("debugMailIscrizioneOperatore");
		} else if (r.getProperty("debug").equals("false")) {
			user = r.getProperty("productionMailIscrizioneOperatore");
		}
		return user;
	}

	public static String getMailSollecitoAttestato() {
		ReadProperty r = ReadProperty.getInstance();
		String user = "";
		// attivazione dell'invio pianificato di statistiche via email
		if (r.getProperty("debug").equals("true")) {
			user = r.getProperty("debugMailSollecitoAttestato");
		} else if (r.getProperty("debug").equals("false")) {
			user = r.getProperty("productionMailSollecitoAttestato");
		}
		return user;
	}

	public static String getMailModificaUtente() {
		ReadProperty r = ReadProperty.getInstance();
		String user = "";
		// attivazione dell'invio pianificato di statistiche via email
		if (r.getProperty("debug").equals("true")) {
			user = r.getProperty("debugMailModificaUtente");
		} else if (r.getProperty("debug").equals("false")) {
			user = r.getProperty("productionMailModificaUtente");
		}
		return user;
	}

	public static String getMailNotificaOperatore() {
		ReadProperty r = ReadProperty.getInstance();
		String result = "";
		// attivazione dell'invio pianificato di statistiche via email
		if (r.getProperty("debug").equals("true")) {
			result = r.getProperty("debugMailNotificaOperatore");
		} else if (r.getProperty("debug").equals("false")) {
			result = r.getProperty("productionMailNotificaOperatore");
		}
		return result;
	}

	public static String getMailOraInvio() {
		ReadProperty r = ReadProperty.getInstance();
		String result = "";
		// attivazione dell'invio pianificato di statistiche via email
		if (r.getProperty("debug").equals("true")) {
			result = r.getProperty("debugOraInvio");
		} else if (r.getProperty("debug").equals("false")) {
			result = r.getProperty("productionOraInvio");
		}
		return result;
	}

	public static String getMailMinInvio() {
		ReadProperty r = ReadProperty.getInstance();
		String result = "";
		// attivazione dell'invio pianificato di statistiche via email
		if (r.getProperty("debug").equals("true")) {
			result = r.getProperty("debugMinInvio");
		} else if (r.getProperty("debug").equals("false")) {
			result = r.getProperty("productionMinInvio");
		}
		return result;
	}

	public static String getAddCourseWA() {
		ReadProperty r = ReadProperty.getInstance();
		String result = "";
		// attivazione dell'invio pianificato di statistiche via email
		if (r.getProperty("debug").equals("true")) {
			result = r.getProperty("debugAddCourseWA");
		} else if (r.getProperty("debug").equals("false")) {
			result = r.getProperty("productionAddCourseWA");
		}
		return result;
	}

	public static String getAddCourseAndCodFattWA() {
		ReadProperty r = ReadProperty.getInstance();
		String result = "";
		// attivazione dell'invio pianificato di statistiche via email
		if (r.getProperty("debug").equals("true")) {
			result = r.getProperty("debugAddCourseAndCodFattWA");
		} else if (r.getProperty("debug").equals("false")) {
			result = r.getProperty("productionAddCourseAndCodFattWA");
		}
		return result;
	}

	public static String getUnsubscribeLink() {
		ReadProperty r = ReadProperty.getInstance();
		String result = "";
		// attivazione dell'invio pianificato di statistiche via email
		if (r.getProperty("debug").equals("true")) {
			result = r.getProperty("debugUnsubscribeLink");
		} else if (r.getProperty("debug").equals("false")) {
			result = r.getProperty("productionUnsubscribeLink");
		}
		return result;
	}

	public static String getActivationDelay() {
		ReadProperty r = ReadProperty.getInstance();
		String result = "";
		// attivazione dell'invio pianificato di statistiche via email
		if (r.getProperty("debug").equals("true")) {
			result = r.getProperty("debugActivationDelay");
		} else if (r.getProperty("debug").equals("false")) {
			result = r.getProperty("productionActivationDelay");
		}
		return result;
	}

	public static String getActivationPeriod() {
		ReadProperty r = ReadProperty.getInstance();
		String result = "";
		// attivazione dell'invio pianificato di statistiche via email
		if (r.getProperty("debug").equals("true")) {
			result = r.getProperty("debugActivationPeriod");
		} else if (r.getProperty("debug").equals("false")) {
			result = r.getProperty("productionActivationPeriod");
		}
		return result;
	}

	public static String getNumberOfMessages() {
		ReadProperty r = ReadProperty.getInstance();
		String result = "";
		// attivazione dell'invio pianificato di statistiche via email
		if (r.getProperty("debug").equals("true")) {
			result = r.getProperty("debugNumberOfMessages");
		} else if (r.getProperty("debug").equals("false")) {
			result = r.getProperty("productionNumberOfMessages");
		}
		return result;
	}

	public static boolean getInvio() {
		ReadProperty r = ReadProperty.getInstance();
		String result = "";
		// attivazione dell'invio pianificato di statistiche via email
		if (r.getProperty("debug").equals("true")) {
			result = r.getProperty("debugINVIO");
		} else if (r.getProperty("debug").equals("false")) {
			result = r.getProperty("productionINVIO");
		}

		if (result.equals("true")) {
			return true;
		} else {
			return false;
		}
	}
}

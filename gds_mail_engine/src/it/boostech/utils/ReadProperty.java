package it.boostech.utils;

import java.io.*;
import java.util.*;

public class ReadProperty implements Constants {

	private static String dir;
	private static ReadProperty instance;
	private static Properties pro = new Properties();
	private static String appname;

	@SuppressWarnings("static-access")
	public ReadProperty(String dir, String appname) {
		super();
		this.appname = "config";
		this.dir = dir;
		loadPropertyFile(this.dir + "WEB-INF/" + "config");
	}

	public static ReadProperty getInstance() {
		if (instance == null) {
			instance = new ReadProperty(dir, appname);
		}
		return instance;
	}

	public void loadPropertyFile(String filename) {
		try {
			int check = 0;
			while (check == 0) {
				check = 1;

				File f = new File(filename + ".properties");
				if (f.exists()) {
					FileInputStream in = new FileInputStream(f);
					pro.load(in);
				} else {
					check = 0;
					System.out.println("Impossibile trovare il file di configurazione.");
				}
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public String getProperty(String key) {
		String p = pro.getProperty(key);
		return (p);
	}

	public String getRealPath(){
		return dir;
	}

	public static String getAppname() {
		return appname;
	}

	public static void setAppname(String appname) {
		ReadProperty.appname = appname;
	}
}
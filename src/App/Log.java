package App;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Log {
	/**
	 * 0: Critical only
	 * 1: + Error
	 * 2: + Warning
	 * 3: + Debug
	 */
	private static int debugLevel = 3;
	public static String getDateTimeString() {
		SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	    return "[" + formater.format(new Date()) + "]";
	}
	public static void debug(String message) {
		if(debugLevel >= 3) {
			System.out.println(getDateTimeString() + " [DEBUG] : " + message);
		}
	}

	public static void error(String message) {
		if(debugLevel >= 1) {
			System.out.println(getDateTimeString() + "[ERROR] : " + message);
		}
	}
	public static void critical(String message) {
		System.out.println(getDateTimeString() + "[CRITICAL] : " + message);
	}

	
}

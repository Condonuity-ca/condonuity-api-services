package tech.torbay.messageservice;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Utils {

	public static String getDateTime() {
		// TODO Auto-generated method stub
		Date date = new Date();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		// Use Madrid's time zone to format the date in
		df.setTimeZone(TimeZone.getTimeZone("Europe/London"));

		String dateTimeNow = df.format(date);
		
		return dateTimeNow; 
	}

	
}

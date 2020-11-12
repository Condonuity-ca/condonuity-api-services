package tech.torbay.projectservice.Utils;

import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import springfox.documentation.spring.web.json.Json;
import tech.torbay.projectservice.constants.Constants;

public class Utils {
	
	
	public static String ClasstoJsonString(Object object) {
		ObjectMapper mapperObj = new ObjectMapper();
        
        try {
            // get Class object as a json string
            String jsonStr = mapperObj.writeValueAsString(object);
            System.out.println("jsonStr"+jsonStr);
            
            Json json = new Json(jsonStr);
            
            return jsonStr;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
	}
	
	public static Map<String, Object> convertJsonToHashMap(String jsonData) throws JsonParseException, JsonMappingException, IOException {
		// TODO Auto-generated method stub
		ObjectMapper mapper = new ObjectMapper();

		Map<String, Object> map = new HashMap<String, Object>();

		// convert JSON string to Map
		map = mapper.readValue(jsonData, new TypeReference<Map<String, String>>(){});

		System.out.println(map);
		
		return map;
	}

	public static String formatFileSize(long size) {
	    String hrSize = null;

	    double b = size;
	    double k = size/1024.0;
	    double m = ((size/1024.0)/1024.0);
	    double g = (((size/1024.0)/1024.0)/1024.0);
	    double t = ((((size/1024.0)/1024.0)/1024.0)/1024.0);

	    DecimalFormat dec = new DecimalFormat("0.00");

	    if ( t>1 ) {
	        hrSize = dec.format(t).concat(" TB");
	    } else if ( g>1 ) {
	        hrSize = dec.format(g).concat(" GB");
	    } else if ( m>1 ) {
	        hrSize = dec.format(m).concat(" MB");
	    } else if ( k>1 ) {
	        hrSize = dec.format(k).concat(" KB");
	    } else {
	        hrSize = dec.format(b).concat(" Bytes");
	    }

	    return hrSize;
	}
	
	public static Boolean checkDateCrossed(String bidEndDate) {
		
		try {
			Date date = new Date();
			
			long currentDateTime = date.toInstant().atZone(ZoneId.of(Constants.TIME_ZONE_EST)).toInstant().toEpochMilli();
			
			Date endDate=new SimpleDateFormat("yyyy-MM-dd").parse(bidEndDate); 
			 
			long millisOfBidEndDate = endDate.getTime();
			
			if(currentDateTime > millisOfBidEndDate) {
				return true;
			} else {
				return false;
			}
		} catch (Exception exp) {
			exp.printStackTrace();
			return false;
		}
		
	}
	
	public static int checkBidEndDateExpireInDays(String bidEndDate) {
		
		try {
			Date date = new Date();
			Date today_Date = new Date();
			Date bidEnd_Date = new Date();
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			
			String today = date.toInstant().atZone(ZoneId.of(Constants.TIME_ZONE_EST)).toLocalDate().toString();
			String plus1Day = date.toInstant().atZone(ZoneId.of(Constants.TIME_ZONE_EST)).plusDays(Constants.DAY_1).toLocalDate().toString();
			String plus2Days = date.toInstant().atZone(ZoneId.of(Constants.TIME_ZONE_EST)).plusDays(Constants.DAY_2).toLocalDate().toString();
			
			System.out.println("today : "+ today);
			System.out.println("bidEndDate : "+ bidEndDate);
			System.out.println("plus1Day : "+ plus1Day);
			System.out.println("plus2Days : "+ plus2Days);
			

			if(bidEndDate.equals(plus1Day)) {
				return 1;
			} else if(bidEndDate.equals(plus2Days)) {
				return 2;
			} else if(bidEndDate.equals(today)) {
				return 0;
			}
			
			 try {
				   today_Date = dateFormat.parse(today);
			       }
			       catch(Exception e)
			       {
				   e.printStackTrace();
			       }

				// convert date present in the String to java.util.Date.
				try
				{
				    bidEnd_Date = dateFormat.parse(bidEndDate);
				}
				catch(Exception e)
				{
				    e.printStackTrace();
				}
				
				System.out.print("Difference : "+ today_Date.compareTo(bidEnd_Date));

				
			return 3; //else =3 - no action
			 
		} catch (Exception exp) {
			exp.printStackTrace();
			return 3;
		}
		
	}
	
	public static String getDateTime() {
		// TODO Auto-generated method stub
		Date date = new Date();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		// Use Madrid's time zone to format the date in
		df.setTimeZone(TimeZone.getTimeZone("Europe/London"));

		String dateTimeNow = df.format(date);
		
		return dateTimeNow; 
	}
	
	public static String getPlus30DaysDate() {
		
		try {
			Date date = new Date();
			
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			
			String plus30Days = date.toInstant().atZone(ZoneId.of(Constants.TIME_ZONE)).plusDays(Constants.END_DATE_PROJECTS_CONSTANT).toLocalDate().toString();
			
			System.out.println("currentDateTime : "+ plus30Days);
			
//			Date endDate=new SimpleDateFormat("yyyy-MM-dd").parse(plus30Days); 
			
//			System.out.println("endDate : "+ endDate.toString());
			
			return plus30Days;
			 
		} catch (Exception exp) {
			exp.printStackTrace();
			return null;
		}
		
	}
	
}

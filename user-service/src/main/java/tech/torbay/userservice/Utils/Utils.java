package tech.torbay.userservice.Utils;

import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import springfox.documentation.spring.web.json.Json;
import tech.torbay.userservice.constants.Constants;

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
	
	public static String getLinkValidityTime() {
		
		Date date = new Date();
		
		long apiExpiryDateTime = date.toInstant().atZone(ZoneId.of(Constants.TIME_ZONE))
				/* .plusHours(Constants.EXPIRY_DURATION) */.toInstant().toEpochMilli();
		
//		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		df.setTimeZone(TimeZone.getTimeZone(Constants.TIME_ZONE));
//		String apiExpiryDateTime = df.format(localDateTime);
		
		System.out.println("apiExpiryDateTime :"+ apiExpiryDateTime);
		
		return String.valueOf(apiExpiryDateTime);
	}
	
}

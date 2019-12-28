package tech.torbay.securityservice.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import springfox.documentation.spring.web.json.Json;

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

}

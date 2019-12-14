package tech.torbay.securityservice.utils;

import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonCreator;
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

}

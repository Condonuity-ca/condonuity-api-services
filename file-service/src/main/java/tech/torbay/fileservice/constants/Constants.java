package tech.torbay.fileservice.constants;

import java.util.HashMap;
import java.util.Map;

public class Constants {

	public enum UserType {
	    CLIENT(1),
	    VENDOR(2),
	    SUPPORT_USER(3);

	    private int value;
	    private static Map map = new HashMap<>();

	    private UserType(int value) {
	        this.value = value;
	    }

	    static {
	        for (UserType pageType : UserType.values()) {
	            map.put(pageType.value, pageType);
	        }
	    }

	    public static UserType valueOf(int pageType) {
	        return (UserType) map.get(pageType);
	    }

	    public int getValue() {
	        return value;
	    }
	}
	
	public enum StatusCode {
		// API Responses
	    REQUEST_SUCCESS(0),
	    REQUEST_FAILED(1),
	    SERVER_ERROR(2),
	    BAD_REQUEST(3),
	    NOT_FOUND(4),
	    AUTHENTICATION_FAILED(5),
		CONFLICT(6),
		FILE_NOT_FOUND(7);
		
	    private int value;
	    private static Map map = new HashMap<>();

	    private StatusCode(int value) {
	        this.value = value;
	    }

	    static {
	        for (StatusCode pageType : StatusCode.values()) {
	            map.put(pageType.value, pageType);
	        }
	    }

	    public static StatusCode valueOf(int pageType) {
	        return (StatusCode) map.get(pageType);
	    }

	    public int getValue() {
	        return value;
	    }
	}
	
	public enum Availability {
	    INFO_NOT_AVAILABLE(0),
	    AVAILABLE(1),
	    NOT_AVAILABLE(2);

	    private int value;
	    private static Map map = new HashMap<>();

	    private Availability(int value) {
	        this.value = value;
	    }

	    static {
	        for (Availability pageType : Availability.values()) {
	            map.put(pageType.value, pageType);
	        }
	    }

	    public static Availability valueOf(int pageType) {
	        return (Availability) map.get(pageType);
	    }

	    public int getValue() {
	        return value;
	    }
	}
	
	public enum Containers {
	    CLIENT_REGISTRATION_FILES("clientregistrationfiles"),
	    PROJECT_FILES("projectfiles"),
	    BID_FILES("bidfiles"),
	    PROFILE_IMAGES("profileimages"),
	    ORGANISATION_PROFILE_IMAGES("organisationprofileimages"),
	    MESSAGE_INTERNAL_THREAD_FILES("internalthreadfiles"),
		MESSAGE_EXTERNALL_THREAD_FILES("externalthreadfiles"),
		MESSAGE_INTERNAL_COMMENT_FILES("internalCommentfiles"),
		MESSAGE_EXTERNAL_COMMENT_FILES("externalCommentfiles");

	    private String value;
	    private static Map map = new HashMap<>();

	    private Containers(String value) {
	        this.value = value;
	    }

	    static {
	        for (Containers pageType : Containers.values()) {
	            map.put(pageType.value, pageType);
	        }
	    }

	    public static Containers valueOf(int pageType) {
	        return (Containers) map.get(pageType);
	    }

	    public String getValue() {
	        return value;
	    }
	}
	
}

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
		FILE_NOT_FOUND(7),
		UPLOAD_FILE_SIZE_EXCEED(8);
		
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
	
	public enum DeleteStatus {
	    NOT_AVAILABLE(0),
	    ACTIVE(1),
	    INACTIVE(2);

	    private int value;
	    private static Map map = new HashMap<>();

	    private DeleteStatus(int value) {
	        this.value = value;
	    }

	    static {
	        for (DeleteStatus pageType : DeleteStatus.values()) {
	            map.put(pageType.value, pageType);
	        }
	    }

	    public static DeleteStatus valueOf(int pageType) {
	        return (DeleteStatus) map.get(pageType);
	    }

	    public int getValue() {
	        return value;
	    }
	}

	
	public enum InsuranceBondAvailability {
	    NOT_AVAILABLE(0),
	    AVAILABLE(1);
	    private int value;
	    private static Map map = new HashMap<>();

	    private InsuranceBondAvailability(int value) {
	        this.value = value;
	    }

	    static {
	        for (InsuranceBondAvailability pageType : InsuranceBondAvailability.values()) {
	            map.put(pageType.value, pageType);
	        }
	    }

	    public static InsuranceBondAvailability valueOf(int pageType) {
	        return (InsuranceBondAvailability) map.get(pageType);
	    }

	    public int getValue() {
	        return value;
	    }
	}
	
	public enum Containers {
		CLIENT_REGISTRATION_FILES("clientregistrationfiles"),
	    VENDOR_REGISTRATION_FILES("vendorregistrationfiles"),
	    PROJECT_FILES("projectfiles"),
	    BID_FILES("bidfiles"),
	    PROJECT_AWARD_FILES("projectawardfiles"),
	    PROFILE_IMAGES("profileimages"),
	    ORGANISATION_PROFILE_IMAGES("organisationprofileimages"),
	    MESSAGE_INTERNAL_THREAD_FILES("internalthreadfiles"),
		MESSAGE_EXTERNAL_THREAD_FILES("externalthreadfiles"),
		MESSAGE_INTERNAL_COMMENT_FILES("internalcommentfiles"),
		MESSAGE_EXTERNAL_COMMENT_FILES("externalcommentfiles"),
		VENDOR_PORTFOLIO_FILES("vendorportfoliofiles");

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
	
	public enum ThreadType {
	    INTERNAL(1),
	    EXTERNAL(2);

	    private int value;
	    private static Map map = new HashMap<>();

	    private ThreadType(int value) {
	        this.value = value;
	    }

	    static {
	        for (ThreadType pageType : ThreadType.values()) {
	            map.put(pageType.value, pageType);
	        }
	    }

	    public static ThreadType valueOf(int pageType) {
	        return (ThreadType) map.get(pageType);
	    }

	    public int getValue() {
	        return value;
	    }
	}
	
	public enum Invalid {
		ID(0);

	    private int value;
	    private static Map map = new HashMap<>();

	    private Invalid(int value) {
	        this.value = value;
	    }

	    static {
	        for (Invalid pageType : Invalid.values()) {
	            map.put(pageType.value, pageType);
	        }
	    }

	    public static Invalid valueOf(int pageType) {
	        return (Invalid) map.get(pageType);
	    }

	    public int getValue() {
	        return value;
	    }
	}
	
	public enum NotificationType {
		// changes -c, expiry -e, new - n, won -w, lose -l, new bid - b, 
		PROJECT_CREATE(1),
		PROJECT_UPDATE(2),
	    PROJECT_EXPIRY(3),
	    BID_CREATE(4),
	    BID_UPDATE(5),
		BID_WON_LOSE(6),
		REVIEW_CREATE(7),
		REVIEW_COMMENT(8),
		TASK_CREATE(9),
		TASK_UPDATE(10),
		TASK_COMMENT(11),
		INTERNAL_MESSAGE_THREAD_CREATE(12),
		INTERNAL_MESSAGE_THREAD_UPDATE(13),
		INTERNAL_MESSAGE_THREAD_COMMENT(14),
		EXTERNAL_MESSAGE_THREAD_CREATE(15),
		EXTERNAL_MESSAGE_THREAD_UPDATE(16),
		EXTERNAL_MESSAGE_THREAD_COMMENT(17),
		CLIENT_USER_PROFILE_INVITE(18),
		CLIENT_USER_PROFILE_DELETE(19),
		CLIENT_USER_PROFILE_UPDATE(20),
		CLIENT_ORGANISATION_UPDATE(21),
		VENDOR_USER_PROFILE_INVITE(22),
		VENDOR_USER_PROFILE_DELETE(23),
		VENDOR_USER_PROFILE_UPDATE(24),
		VENDOR_ORGANISATION_UPDATE(25),
		PROJECT_BIDDING_EXPIRING(26),
		PROJECT_BIDDING_EXPIRED(27),
		PROJECT_COMPETITOR_BIDDING(28),
		ANNUAL_CONTRACT_EXPIRING(29),
		ANNUAL_CONTRACT_EXPIRED(30),
		PROJECT_QUESTION_CREATE(31),
		PROJECT_QUESTION_ANSWER(32),
		PROJECT_CANCEL(33),
		BID_WITHDRAWN(34),
		REVIEW_UPDATE(35),
		USER_NAME_CHANGE(36),
		USER_PHONE_CHANGE(37),
		USER_NAME_PHONE_CHANGE(38),
		USER_PASSWORD_CHANGE(39),
		USER_IMAGE_CHANGE(40);

	    private int value;
	    private static Map map = new HashMap<>();

	    private NotificationType(int value) {
	        this.value = value;
	    }

	    static {
	        for (NotificationType pageType : NotificationType.values()) {
	            map.put(pageType.value, pageType);
	        }
	    }

	    public static NotificationType valueOf(int pageType) {
	        return (NotificationType) map.get(pageType);
	    }

	    public int getValue() {
	        return value;
	    }
	}

}

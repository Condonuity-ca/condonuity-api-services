package tech.torbay.userservice.constants;

import java.util.HashMap;
import java.util.Map;

public class Constants {


	public enum UserType {
	    CLIENT(1),
	    VENDOR(2),
	    SUPPORT_USER(3),
	    MANAGER(11),
	    ASSISTANT_MANAGER(12),
	    BOARD_MEMBER(13);

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
	
	public enum UserRole {
		NOT_AVAILABLE(0),
	    ADMIN(1),
	    USER(2);

	    private int value;
	    private static Map map = new HashMap<>();

	    private UserRole(int value) {
	        this.value = value;
	    }

	    static {
	        for (UserRole pageType : UserRole.values()) {
	            map.put(pageType.value, pageType);
	        }
	    }

	    public static UserRole valueOf(int pageType) {
	        return (UserRole) map.get(pageType);
	    }

	    public int getValue() {
	        return value;
	    }
	}
	// 000 - success
	// 111 - failed
	// 001 - initiated
	// 002 - verified
	// 003 - failed_to_verify
	// 004 - document_verified
	// 005 - document_rejected
	// 006 - bids_won
	// 007 - bids_failed
	// 008 - tag_created_and_submitted_for_approval
	// 009 - tag_approved_by_support_user
	// 010 - tag_rejected_by_support_user
	// 011 - not_subscribed
	// 012 - subscribed
	// 013 - project_created
	// 014 - project_active
	// 015 - project_inactive
	// 016 - task_created
	// 017 - task_assigned/task_opened
	// 018 - task_closed
	// 019 - 
	// 020 - active
	// 021 - inactive
	public enum StatusCode {
		// API Responses
	    REQUEST_SUCCESS(0),
	    REQUEST_FAILED(1),
	    SERVER_ERROR(2),
	    BAD_REQUEST(3),
	    NOT_FOUND(4),
	    AUTHENTICATION_FAILED(5),
	    RESET_PASSWORD(6),
	    
	    //User Registration
	    USER_REGISTER_INPROGRESS(10),// password not set
	    USER_REGISTER_VERIFIED(11),// password created
	    ORGANISATION_REGISTER_INPROGRESS(12), // organisation created and submitted
	    ORGANISATION_REGISTER_VERIFIED(13), // organisation verified
	    DOCUMENT_VERIFICATION_SUBMITTED(14), // document for verification submitted 
	    DOCUMENT_VERIFICATION_VERIFIED(15), // document verified
	    DOCUMENT_VERIFICATION_REJECTED(16), // document rejected
		USER_ACTIVE(20), // User active state
	    USER_INACTIVE(21); // User Inactive state
		
		
//	    DOCUMENT_VERIFICATION_REJECTED(9);

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
	
     // amenities varchar
	 // 1 - GYM
	 // 2 - PARTY ROOM
	 // 3 - POOL
	 // 4 - PARKING
	 
	 // amenities availability
	 // 0 - info not available
	 // 1 - yes
	 // 2 - no
	
	// pool type
	// 1 - indoor
	// 2 - outdoor
	
	// parking type
	// 1 - Underground
	// 2 - Ground Level
	
	public enum Amenities {
	    GYM("Gym"),
	    PARTY_ROOM("Party Room"),
	    POOL("Swim pool"),
	    PARKING("Parking");

	    private String value;
	    private static Map map = new HashMap<>();

	    private Amenities(String value) {
	        this.value = value;
	    }

	    static {
	        for (Amenities pageType : Amenities.values()) {
	            map.put(pageType.value, pageType);
	        }
	    }

	    public static Amenities valueOf(int pageType) {
	        return (Amenities) map.get(pageType);
	    }

	    public String getValue() {
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
	
	public enum PoolType {
	    Indoor("Indoor"),
	    Outdoor("Outdoor");

	    private String value;
	    private static Map map = new HashMap<>();

	    private PoolType(String value) {
	        this.value = value;
	    }

	    static {
	        for (PoolType pageType : PoolType.values()) {
	            map.put(pageType.value, pageType);
	        }
	    }

	    public static PoolType valueOf(int pageType) {
	        return (PoolType) map.get(pageType);
	    }

	    public String getValue() {
	        return value;
	    }
	}
	
	public enum ParkingType {
	    UnderGround("Under Ground"),
	    GroundLevel("Ground Level");

	    private String value;
	    private static Map map = new HashMap<>();

	    private ParkingType(String value) {
	        this.value = value;
	    }

	    static {
	        for (ParkingType pageType : ParkingType.values()) {
	            map.put(pageType.value, pageType);
	        }
	    }

	    public static ParkingType valueOf(int pageType) {
	        return (ParkingType) map.get(pageType);
	    }

	    public String getValue() {
	        return value;
	    }
	}
}

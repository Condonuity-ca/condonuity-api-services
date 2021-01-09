package tech.torbay.securityservice.constants;


import java.util.HashMap;
import java.util.Map;

public class Constants {
	
	public static final Integer MAX_USER_COUNT = 12;
	public static final Integer MAX_INCORRECT_LOGIN_ATTEMPT_COUNT = 10;
	public static final Integer RESET_LOGIN_ATTEMPT_COUNT = 0;
	public static final String TIME_ZONE = "UTC";
	public static final long EXPIRY_DURATION_IN_DAYS = 7;

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
	
	public enum ClientUserType {
		MANAGER(1),
	    ASSISTANT_MANAGER(2),
	    BOARD_MEMBER(3);
		
		private int value;
		private static Map map = new HashMap<>();
		
		private ClientUserType(int value) {
			this.value = value;
		}
		
		static {
			for (ClientUserType pageType : ClientUserType.values()) {
				map.put(pageType.value, pageType);
			}
		}
		
		public static ClientUserType valueOf(int pageType) {
			return (ClientUserType) map.get(pageType);
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
	
	/*RESET_PASSWORD(6),
	    
	    //User Registration
	    USER_REGISTER_INPROGRESS(10),// password not set
	    USER_REGISTER_VERIFIED(11),// password created
	    ORGANISATION_REGISTER_INPROGRESS(12), // organisation created and submitted
	    ORGANISATION_REGISTER_VERIFIED(13), // organisation verified
	    DOCUMENT_VERIFICATION_SUBMITTED(14), // document for verification submitted 
	    DOCUMENT_VERIFICATION_VERIFIED(15), // document verified
	    DOCUMENT_VERIFICATION_REJECTED(16), // document rejected
		USER_ACTIVE(20), // User active state
	    USER_INACTIVE(21); // User Inactive state*/
	
	public enum APIStatusCode {
		// API Responses
	    REQUEST_SUCCESS(0),
	    REQUEST_FAILED(1),
	    SERVER_ERROR(2),
	    BAD_REQUEST(3),
	    NOT_FOUND(4),
	    AUTHENTICATION_FAILED(5),
	    CONFLICT(6),
		INACTIVE_USER(7),
		ORGANISATION_NOT_FOUND(8),
		NO_ACTIVE_ORGANISATION_FOUND(9), 
		REGISTRATION_UNDER_REVIEW(10), 
		MAX_USERS_COUNT_ERROR(7),
		RESET_PASSWORD(5),
		LINK_EXPIRED(5),
		DUPLICATE_CORPORATION_NUMBER(7),
		FIELD_VALIDATION_ERROR(11),
		USER_ACCOUNT_DELETED(12),
		CLIENT_USER_RECORD_EXISTS(13),
		VENDOR_USER_RECORD_EXISTS(14),
		INVITE_RESEND(15),		
		ACCOUNT_BLOCKED_BY_MAX_INCORRECT_LOGIN_ATTEMPT(16);		
	    
	    private int value;
	    private static Map map = new HashMap<>();

	    private APIStatusCode(int value) {
	        this.value = value;
	    }

	    static {
	        for (APIStatusCode pageType : APIStatusCode.values()) {
	            map.put(pageType.value, pageType);
	        }
	    }

	    public static APIStatusCode valueOf(int pageType) {
	        return (APIStatusCode) map.get(pageType);
	    }

	    public int getValue() {
	        return value;
	    }
	}
	
	public enum UserAccountStatus {
	    INVITED(0),
	    ACTIVE(1),
	    INACTIVE(2);

	    private int value;
	    private static Map map = new HashMap<>();

	    private UserAccountStatus(int value) {
	        this.value = value;
	    }

	    static {
	        for (UserAccountStatus pageType : UserAccountStatus.values()) {
	            map.put(pageType.value, pageType);
	        }
	    }

	    public static UserAccountStatus valueOf(int pageType) {
	        return (UserAccountStatus) map.get(pageType);
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
	
	public enum OrganisationAccountStatus {
	    REGISTERED(0),
	    ACTIVE(1),
	    INACTIVE(2);

	    private int value;
	    private static Map map = new HashMap<>();

	    private OrganisationAccountStatus(int value) {
	        this.value = value;
	    }

	    static {
	        for (OrganisationAccountStatus pageType : OrganisationAccountStatus.values()) {
	            map.put(pageType.value, pageType);
	        }
	    }

	    public static OrganisationAccountStatus valueOf(int pageType) {
	        return (OrganisationAccountStatus) map.get(pageType);
	    }

	    public int getValue() {
	        return value;
	    }
	}
	
	public enum VerificationStatus {
		NOT_VERIFIED(0),
	    VERIFIED(1),
		REJECTED(2);

	    private int value;
	    private static Map map = new HashMap<>();

	    private VerificationStatus(int value) {
	        this.value = value;
	    }

	    static {
	        for (VerificationStatus pageType : VerificationStatus.values()) {
	            map.put(pageType.value, pageType);
	        }
	    }

	    public static VerificationStatus valueOf(int pageType) {
	        return (VerificationStatus) map.get(pageType);
	    }

	    public int getValue() {
	        return value;
	    }
	}

	 // 0 - info not available
	 // 1 - yes
	 // 2 - no
	
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
	
	public enum AmenitiesType { // for Reference
		/*
		 * YES(1), NO(2), INDOOR(3), OUTDOOR(4), GROUND_LEVEL(5), UNDER_GROUND(6);
		 */
		
		INDOOR_POOL(1), OUTDOOR_POOL(2), UNDER_GROUND(3), GROUND_LEVEL(4);
		
		private int value;
		private static Map map = new HashMap<>();
		
		private AmenitiesType(int value) {
			this.value = value;
		}
		
		static {
			for (AmenitiesType pageType : AmenitiesType.values()) {
				map.put(pageType.value, pageType);
			}
		}
		
		public static AmenitiesType valueOf(int pageType) {
			return (AmenitiesType) map.get(pageType);
		}
		
		public int getValue() {
			return value;
		}
	}
	
	public enum AllocationStatus {
		NOT_ALLOCATED(0),
		ALLOCATED(1);

	    private int value;
	    private static Map map = new HashMap<>();

	    private AllocationStatus(int value) {
	        this.value = value;
	    }

	    static {
	        for (AllocationStatus pageType : AllocationStatus.values()) {
	            map.put(pageType.value, pageType);
	        }
	    }

	    public static AllocationStatus valueOf(int pageType) {
	        return (AllocationStatus) map.get(pageType);
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

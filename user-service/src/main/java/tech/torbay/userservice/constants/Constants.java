package tech.torbay.userservice.constants;

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
	
	public enum ClientUserType {
		MANAGER(11),
	    ASSISTANT_MANAGER(12),
	    BOARD_MEMBER(13);
		
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
	
	public enum APIStatusCode {
		// API Responses
	    REQUEST_SUCCESS(0),
	    REQUEST_FAILED(1),
	    SERVER_ERROR(2),
	    BAD_REQUEST(3),
	    NOT_FOUND(4),
	    AUTHENTICATION_FAILED(5);
	    
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
	
	public enum VerificationStatus {
		NOT_VERIFIED(1),
	    VERIFIED(2),
		REJECTED(3);

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
	
	public enum PortfolioSortBy {
	    ASC(1),
	    DESC(2),
	    DATE(3),
	    COST(4),
	    DURATION(5);

	    private int value;
	    private static Map map = new HashMap<>();

	    private PortfolioSortBy(int value) {
	        this.value = value;
	    }

	    static {
	        for (PortfolioSortBy pageType : PortfolioSortBy.values()) {
	            map.put(pageType.value, pageType);
	        }
	    }

	    public static PortfolioSortBy valueOf(int pageType) {
	        return (PortfolioSortBy) map.get(pageType);
	    }

	    public int getValue() {
	        return value;
	    }
	}
	
	public enum VendorSortBy {
	    ASC(1),
	    DESC(2),
	    NEAREST(3),
	    HIGHEST_RATING(4),
	    PREFERRED(5);
		
		// ADD Keyword, location for filter

	    private int value;
	    private static Map map = new HashMap<>();

	    private VendorSortBy(int value) {
	        this.value = value;
	    }

	    static {
	        for (VendorSortBy pageType : VendorSortBy.values()) {
	            map.put(pageType.value, pageType);
	        }
	    }

	    public static VendorSortBy valueOf(int pageType) {
	        return (VendorSortBy) map.get(pageType);
	    }

	    public int getValue() {
	        return value;
	    }
	}
	
	public enum ClientSortBy {
	    ASC(1),
	    DESC(2),
	    NEAREST(3),
	    SIZE_OF_UNITS(4),
	    DATE_OF_REGISTRATION(5);
		
		// ADD keyword, Location, no.of.units(from - to) for filter

	    private int value;
	    private static Map map = new HashMap<>();

	    private ClientSortBy(int value) {
	        this.value = value;
	    }

	    static {
	        for (ClientSortBy pageType : ClientSortBy.values()) {
	            map.put(pageType.value, pageType);
	        }
	    }

	    public static ClientSortBy valueOf(int pageType) {
	        return (ClientSortBy) map.get(pageType);
	    }

	    public int getValue() {
	        return value;
	    }
	}
	
	public enum ProjectSortBy {
	    ASC(1),
	    DESC(2);
		
		// ADD project_id, project_name, contract_type, date_created, bid_closed_date, tags, status, location, city, completion_date, favourite, no.of.bids, no.of.interests

	    private int value;
	    private static Map map = new HashMap<>();

	    private ProjectSortBy(int value) {
	        this.value = value;
	    }

	    static {
	        for (ProjectSortBy pageType : ProjectSortBy.values()) {
	            map.put(pageType.value, pageType);
	        }
	    }

	    public static ProjectSortBy valueOf(int pageType) {
	        return (ProjectSortBy) map.get(pageType);
	    }

	    public int getValue() {
	        return value;
	    }
	}
	
	public enum VendorRatingCategory {
	    RESPONSIVENESS(1),
	    PROFESSIONALISM(2),
		ACCURACY(3),
		QUALITY(4);
		
	    private int value;
	    private static Map map = new HashMap<>();

	    private VendorRatingCategory(int value) {
	        this.value = value;
	    }

	    static {
	        for (VendorRatingCategory pageType : VendorRatingCategory.values()) {
	            map.put(pageType.value, pageType);
	        }
	    }

	    public static VendorRatingCategory valueOf(int pageType) {
	        return (VendorRatingCategory) map.get(pageType);
	    }

	    public int getValue() {
	        return value;
	    }
	}

	public enum VendorRatingCategoryPercentage {
	    RESPONSIVENESS(10),
	    PROFESSIONALISM(30),
		ACCURACY(30),
		QUALITY(30);
		
	    private int value;
	    private static Map map = new HashMap<>();

	    private VendorRatingCategoryPercentage(int value) {
	        this.value = value;
	    }

	    static {
	        for (VendorRatingCategoryPercentage pageType : VendorRatingCategoryPercentage.values()) {
	            map.put(pageType.value, pageType);
	        }
	    }

	    public static VendorRatingCategoryPercentage valueOf(int pageType) {
	        return (VendorRatingCategoryPercentage) map.get(pageType);
	    }

	    public int getValue() {
	        return value;
	    }
	}

}

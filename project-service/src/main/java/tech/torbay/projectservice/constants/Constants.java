package tech.torbay.projectservice.constants;

import java.util.HashMap;
import java.util.Map;

public class Constants {

	public static final String TIME_ZONE = "UTC";
	public static final String TIME_ZONE_EST = "UTC-5";
	public static final int END_DATE_PROJECTS_CONSTANT = 30;
	public static final int HOURS_24 = 24;
	public static final int DAY_0 = 0;
	public static final int DAY_1 = 1;
	public static final int DAY_2 = 2;
	public static final int HOURS_48 = 48;

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
	public enum StatusCode {
		// API Responses
	    REQUEST_SUCCESS(0),
	    REQUEST_FAILED(1),
	    SERVER_ERROR(2),
	    BAD_REQUEST(3),
	    NOT_FOUND(4),
	    AUTHENTICATION_FAILED(5),
		CONFLICT(6),
		PROJECT_BID_END_DATE_CROSSED(7);
		
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
	//SET SQL_SAFE_UPDATES = 0;
	public enum ProjectPostType {
		UNPUBLISHED(1),
		PUBLISHED(2),
		COMPLETED(3),
		CANCELLED(4);// Cancelled or Terminated

	    private int value;
	    private static Map map = new HashMap<>();

	    private ProjectPostType(int value) {
	        this.value = value;
	    }

	    static {
	        for (ProjectPostType pageType : ProjectPostType.values()) {
	            map.put(pageType.value, pageType);
	        }
	    }

	    public static ProjectPostType valueOf(int pageType) {
	        return (ProjectPostType) map.get(pageType);
	    }

	    public int getValue() {
	        return value;
	    }
	}
	
	public enum ProjectPostTo {
		ALL(1),
		MARKED(2);

	    private int value;
	    private static Map map = new HashMap<>();

	    private ProjectPostTo(int value) {
	        this.value = value;
	    }

	    static {
	        for (ProjectPostTo pageType : ProjectPostTo.values()) {
	            map.put(pageType.value, pageType);
	        }
	    }

	    public static ProjectPostTo valueOf(int pageType) {
	        return (ProjectPostTo) map.get(pageType);
	    }

	    public int getValue() {
	        return value;
	    }
	}
	
	public enum BidPostType {
		UNPUBLISHED(1),
		PUBLISHED(2),
		AWARDED(3),
		PULLED(4);

	    private int value;
	    private static Map map = new HashMap<>();

	    private BidPostType(int value) {
	        this.value = value;
	    }

	    static {
	        for (BidPostType pageType : BidPostType.values()) {
	            map.put(pageType.value, pageType);
	        }
	    }

	    public static BidPostType valueOf(int pageType) {
	        return (BidPostType) map.get(pageType);
	    }

	    public int getValue() {
	        return value;
	    }
	}
	
	public enum ContractType {
		Fixed_cost(1),
		Time_and_Material(2),
		Annual_contract(3);

	    private int value;
	    private static Map map = new HashMap<>();

	    private ContractType(int value) {
	        this.value = value;
	    }

	    static {
	        for (ContractType pageType : ContractType.values()) {
	            map.put(pageType.value, pageType);
	        }
	    }

	    public static ContractType valueOf(int pageType) {
	        return (ContractType) map.get(pageType);
	    }

	    public int getValue() {
	        return value;
	    }
	}
	
	public enum ProjectSortBy {
		All(0),
		Current(1),
		Past(2);

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
	
	public enum ProjectProductType {
		PRODUCT_MATERIAL(1),
		EMPLOYEE(2);

	    private int value;
	    private static Map map = new HashMap<>();

	    private ProjectProductType(int value) {
	        this.value = value;
	    }

	    static {
	        for (ProjectProductType pageType : ProjectProductType.values()) {
	            map.put(pageType.value, pageType);
	        }
	    }

	    public static ProjectProductType valueOf(int pageType) {
	        return (ProjectProductType) map.get(pageType);
	    }

	    public int getValue() {
	        return value;
	    }
	}
	
	public enum ProjectInterestStatus {
		UN_LIKE(0),
		LIKE(1);

	    private int value;
	    private static Map map = new HashMap<>();

	    private ProjectInterestStatus(int value) {
	        this.value = value;
	    }

	    static {
	        for (ProjectInterestStatus pageType : ProjectInterestStatus.values()) {
	            map.put(pageType.value, pageType);
	        }
	    }

	    public static ProjectInterestStatus valueOf(int pageType) {
	        return (ProjectInterestStatus) map.get(pageType);
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
}

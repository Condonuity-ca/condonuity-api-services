package tech.torbay.userservice.constants;

import java.util.HashMap;
import java.util.Map;

public class Constants {

	public static final Integer MAX_USER_COUNT = 12;
	public static final String TIME_ZONE = "Europe/London";
	public static final String USER_ACCOUNT_REMOVE_ALERT = "User Account Deleted From the System By Admin";
	public static final String USER_ACCOUNT_ACTIVE_ALERT = "User Account Status Changed to Active By Admin";
	public static final String ORGANISATION_ACCOUNT_REMOVE_ALERT = "Organisation Account Deleted From the System By Admin";
	public static final String ORGANISATION_ACCOUNT_ACTIVE_ALERT = "Organisation Account Status Changed to Active By Admin";
	public static final long EXPIRY_DURATION = 24;

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
		MAX_USERS_COUNT_ERROR(7);
	    
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
	
	public enum TaskUsers {
	    OTHER_USER_ONLY(1),
	    CLIENT_USER_ONLY(2),
	    BOTH_USER_AND_NONUSER(3);

	    private int value;
	    private static Map map = new HashMap<>();

	    private TaskUsers(int value) {
	        this.value = value;
	    }

	    static {
	        for (TaskUsers pageType : TaskUsers.values()) {
	            map.put(pageType.value, pageType);
	        }
	    }

	    public static TaskUsers valueOf(int pageType) {
	        return (TaskUsers) map.get(pageType);
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
	
//	1. yes
//	2. no
//	3. indoor
//	4. outdoor
//	5. ground level
//	6. underground
	
	public enum TermUnits {
		DAYS(1),
	    MONTHS(2),
	    YEARS(3);
		
		private int value;
		private static Map map = new HashMap<>();
		
		private TermUnits(int value) {
			this.value = value;
		}
		
		static {
			for (TermUnits pageType : TermUnits.values()) {
				map.put(pageType.value, pageType);
			}
		}
		
		public static TermUnits valueOf(int pageType) {
			return (TermUnits) map.get(pageType);
		}
		
		public int getValue() {
			return value;
		}
	}
	
	public enum CostTermUnits {
		WEEKLY(1),
	    MONTHLY(2),
	    YEARLY(3);
		
		private int value;
		private static Map map = new HashMap<>();
		
		private CostTermUnits(int value) {
			this.value = value;
		}
		
		static {
			for (CostTermUnits pageType : CostTermUnits.values()) {
				map.put(pageType.value, pageType);
			}
		}
		
		public static CostTermUnits valueOf(int pageType) {
			return (CostTermUnits) map.get(pageType);
		}
		
		public int getValue() {
			return value;
		}
	}
	
	public enum RenewalType {
		AUTO(1),
	    MANUAL(2);
		
		private int value;
		private static Map map = new HashMap<>();
		
		private RenewalType(int value) {
			this.value = value;
		}
		
		static {
			for (RenewalType pageType : RenewalType.values()) {
				map.put(pageType.value, pageType);
			}
		}
		
		public static RenewalType valueOf(int pageType) {
			return (RenewalType) map.get(pageType);
		}
		
		public int getValue() {
			return value;
		}
	}
	
	public enum Priority {
		HIGHEST(1),
	    HIGH(2),
		MEDIUM(3),
		LOW(4);
		
		private int value;
		private static Map map = new HashMap<>();
		
		private Priority(int value) {
			this.value = value;
		}
		
		static {
			for (Priority pageType : Priority.values()) {
				map.put(pageType.value, pageType);
			}
		}
		
		public static Priority valueOf(int pageType) {
			return (Priority) map.get(pageType);
		}
		
		public int getValue() {
			return value;
		}
	}
	
	public enum TaskPriority {
		HIGHEST(1),
	    HIGH(2),
		MEDIUM(3),
		LOW(4);
		
		private int value;
		private static Map map = new HashMap<>();
		
		private TaskPriority(int value) {
			this.value = value;
		}
		
		static {
			for (TaskPriority pageType : TaskPriority.values()) {
				map.put(pageType.value, pageType);
			}
		}
		
		public static TaskPriority valueOf(int pageType) {
			return (TaskPriority) map.get(pageType);
		}
		
		public int getValue() {
			return value;
		}
	}

	public enum TaskStatus {
		OPEN(1),
	    DEFERRED(2),
		ON_HOLD(3),
		IN_PROGRESS(4),
		CLOSED(5),
		RE_OPENED(6);
		
		private int value;
		private static Map map = new HashMap<>();
		
		private TaskStatus(int value) {
			this.value = value;
		}
		
		static {
			for (TaskStatus pageType : TaskStatus.values()) {
				map.put(pageType.value, pageType);
			}
		}
		
		public static TaskStatus valueOf(int pageType) {
			return (TaskStatus) map.get(pageType);
		}
		
		public int getValue() {
			return value;
		}
	}
	
	public enum UnitType {
		LOCKER(1),
	    RESIDENTIAL_UNIT(2),
		PARKING_UNIT(3),
		COMMERCIAL_UNIT(4),
		COMMON_ELEMENT(5),
		OTHER(6);
		
		private int value;
		private static Map map = new HashMap<>();
		
		private UnitType(int value) {
			this.value = value;
		}
		
		static {
			for (UnitType pageType : UnitType.values()) {
				map.put(pageType.value, pageType);
			}
		}
		
		public static UnitType valueOf(int pageType) {
			return (UnitType) map.get(pageType);
		}
		
		public int getValue() {
			return value;
		}
	}
	
	public enum TenantStatus {
		OWNER_OCCUPIED(1),
	    LEASED(2),
		VACANT(3),
		OTHER(4);
		
		private int value;
		private static Map map = new HashMap<>();
		
		private TenantStatus(int value) {
			this.value = value;
		}
		
		static {
			for (TenantStatus pageType : TenantStatus.values()) {
				map.put(pageType.value, pageType);
			}
		}
		
		public static TenantStatus valueOf(int pageType) {
			return (TenantStatus) map.get(pageType);
		}
		
		public int getValue() {
			return value;
		}
	}
	
	public enum PersonTenantType {
		OWNER(1),
	    OCCUPANT(2),
		TENANT(3),
		OTHER(4);
		
		private int value;
		private static Map map = new HashMap<>();
		
		private PersonTenantType(int value) {
			this.value = value;
		}
		
		static {
			for (PersonTenantType pageType : PersonTenantType.values()) {
				map.put(pageType.value, pageType);
			}
		}
		
		public static PersonTenantType valueOf(int pageType) {
			return (PersonTenantType) map.get(pageType);
		}
		
		public int getValue() {
			return value;
		}
	}
	
	public enum LienType {
		YES(1),
	    NO(2),
		OTHER(3);
		
		private int value;
		private static Map map = new HashMap<>();
		
		private LienType(int value) {
			this.value = value;
		}
		
		static {
			for (LienType pageType : LienType.values()) {
				map.put(pageType.value, pageType);
			}
		}
		
		public static LienType valueOf(int pageType) {
			return (LienType) map.get(pageType);
		}
		
		public int getValue() {
			return value;
		}
	}
	
	public enum SearchType {
		CURRENT_PROJECTS(1),
		HISTORY_PROJECTS(2),
		FAVOURITE_PROJECTS(3),
	    MARKETPLACE_PROJECTS(4),
		BROWSE_VENDORS(5),
		BROWSE_CONDOS(6),
		INTERNAL_MESSAGES(7),
		EXTERNAL_MESSAGES(8),
		REVIEWS(9),
		TASKS(10),
		CONTRACTS(11),
		BUILDING_REPOSITORY(12);
		
		private int value;
		private static Map map = new HashMap<>();
		
		private SearchType(int value) {
			this.value = value;
		}
		
		static {
			for (SearchType pageType : SearchType.values()) {
				map.put(pageType.value, pageType);
			}
		}
		
		public static SearchType valueOf(int pageType) {
			return (SearchType) map.get(pageType);
		}
		
		public int getValue() {
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
	
	public enum ProjectPostType {
		UNPUBLISHED(1),
		PUBLISHED(2),
		COMPLETED(3),
		TERMINATED(4);// Cancelled or Terminated

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
		EXTERNAL_MESSAGE_THREAD_COMMENT(17);

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

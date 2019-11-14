package tech.torbay.projectservice.constants;

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
	    AUTHENTICATION_FAILED(5);
		/* RESET_PASSWORD(6) */;
		
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
	
	public enum ProjectPostType {
		UNPUBLISHED(1),
		PUBLISHED(2),
		COMPLETED(3),
		TERMINATED(4);

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
	
	
}

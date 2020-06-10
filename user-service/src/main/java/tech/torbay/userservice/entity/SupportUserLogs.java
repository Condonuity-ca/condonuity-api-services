package tech.torbay.userservice.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name = "support_user_logs")
public class SupportUserLogs {
	
	public SupportUserLogs() {
	
	}
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private int supportUserId;
	private String logCategory;
	private String logCategoryId;
	private int logUserType;
	private String logs;
	
	@Basic(optional = false)
    @Column(name = "created_at", insertable = false, updatable = false)
    private String createdAt;
    
    @Basic(optional = false)
    @Column(name = "modified_date", insertable = false, updatable = false)
    private String modifiedDate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSupportUserId() {
		return supportUserId;
	}

	public void setSupportUserId(int supportUserId) {
		this.supportUserId = supportUserId;
	}

	public String getLogCategory() {
		return logCategory;
	}

	public void setLogCategory(String logCategory) {
		this.logCategory = logCategory;
	}

	public String getLogCategoryId() {
		return logCategoryId;
	}

	public void setLogCategoryId(String logCategoryId) {
		this.logCategoryId = logCategoryId;
	}

	public int getLogUserType() {
		return logUserType;
	}

	public void setLogUserType(int logUserType) {
		this.logUserType = logUserType;
	}

	public String getLogs() {
		return logs;
	}

	public void setLogs(String logs) {
		this.logs = logs;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	@Override
	public String toString() {
		return "SupportUserLogs [id=" + id + ", supportUserId=" + supportUserId + ", logCategory=" + logCategory
				+ ", logCategoryId=" + logCategoryId + ", logUserType=" + logUserType + ", logs=" + logs
				+ ", createdAt=" + createdAt + ", modifiedDate=" + modifiedDate + "]";
	}

}

package tech.torbay.userservice.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name = "client_tasks")
public class ClientTask {
	
	public ClientTask() {
	
	}
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private int clientOrganisationId;
	private String taskDescription;
	private int priority;
	private String closureDate;
	private int createdBy;//clientUserId
	private String assignedTo;//1,2 -clientIds if other false
	private int modifiedBy;//clientUserId
	private int isOther;//1-true, 2-false
	private String othersName;//if other true
	private int taskStatus;
	private int status;
	
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

	public int getClientOrganisationId() {
		return clientOrganisationId;
	}

	public void setClientOrganisationId(int clientOrganisationId) {
		this.clientOrganisationId = clientOrganisationId;
	}

	public String getTaskDescription() {
		return taskDescription;
	}

	public void setTaskDescription(String taskDescription) {
		this.taskDescription = taskDescription;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public String getClosureDate() {
		return closureDate;
	}

	public void setClosureDate(String closureDate) {
		this.closureDate = closureDate;
	}

	public int getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public String getAssignedTo() {
		return assignedTo;
	}

	public void setAssignedTo(String assignedTo) {
		this.assignedTo = assignedTo;
	}

	public int getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(int modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public int getIsOther() {
		return isOther;
	}

	public void setIsOther(int isOther) {
		this.isOther = isOther;
	}

	public String getOthersName() {
		return othersName;
	}

	public void setOthersName(String othersName) {
		this.othersName = othersName;
	}

	public int getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(int taskStatus) {
		this.taskStatus = taskStatus;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
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
		return "ClientTask [id=" + id + ", clientOrganisationId=" + clientOrganisationId + ", taskDescription="
				+ taskDescription + ", priority=" + priority + ", closureDate=" + closureDate + ", createdBy="
				+ createdBy + ", assignedTo=" + assignedTo + ", modifiedBy=" + modifiedBy + ", isOther=" + isOther
				+ ", othersName=" + othersName + ", taskStatus=" + taskStatus + ", status=" + status + ", createdAt="
				+ createdAt + ", modifiedDate=" + modifiedDate + "]";
	}
    
}

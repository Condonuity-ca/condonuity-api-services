package tech.torbay.clientservice.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name = "external_message")
public class ExternalMessage {

    public ExternalMessage() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id = 0;
    private Integer sourceOrganisationId = 0;
    private Integer sourceUserId = 0;
    private Integer sourceUserType = 0;
    private Integer targetOrganisationId = 0;
    private Integer targetUserType = 0;
    private String threadSubject = "";
    private String threadDescription = "";
    
    @Basic(optional = false)
    @Column(name = "created_at", insertable = false, updatable = false)
    private String createdAt;
    
    @Basic(optional = false)
    @Column(name = "modified_date", insertable = false, updatable = false)
    private String modifiedDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSourceOrganisationId() {
		return sourceOrganisationId;
	}

	public void setSourceOrganisationId(Integer sourceOrganisationId) {
		this.sourceOrganisationId = sourceOrganisationId;
	}

	public Integer getSourceUserId() {
		return sourceUserId;
	}

	public void setSourceUserId(Integer sourceUserId) {
		this.sourceUserId = sourceUserId;
	}

	public Integer getSourceUserType() {
		return sourceUserType;
	}

	public void setSourceUserType(Integer sourceUserType) {
		this.sourceUserType = sourceUserType;
	}

	public Integer getTargetOrganisationId() {
		return targetOrganisationId;
	}

	public void setTargetOrganisationId(Integer targetOrganisationId) {
		this.targetOrganisationId = targetOrganisationId;
	}

	public Integer getTargetUserType() {
		return targetUserType;
	}

	public void setTargetUserType(Integer targetUserType) {
		this.targetUserType = targetUserType;
	}

	public String getThreadSubject() {
		return threadSubject;
	}

	public void setThreadSubject(String threadSubject) {
		this.threadSubject = threadSubject;
	}

	public String getThreadDescription() {
		return threadDescription;
	}

	public void setThreadDescription(String threadDescription) {
		this.threadDescription = threadDescription;
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
		return "ExternalMessage [id=" + id + ", sourceOrganisationId=" + sourceOrganisationId + ", sourceUserId="
				+ sourceUserId + ", sourceUserType=" + sourceUserType + ", targetOrganisationId=" + targetOrganisationId
				+ ", targetUserType=" + targetUserType + ", threadSubject=" + threadSubject + ", threadDescription="
				+ threadDescription + ", createdAt=" + createdAt + ", modifiedDate=" + modifiedDate + "]";
	}

}
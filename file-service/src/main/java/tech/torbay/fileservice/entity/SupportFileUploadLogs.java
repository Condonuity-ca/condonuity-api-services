package tech.torbay.fileservice.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "support_file_upload_logs")
public class SupportFileUploadLogs {

    public SupportFileUploadLogs() {

    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id = 0;
    private Integer supportUserId = 0;
    private Integer fileId = 0;
    private Integer organisationId = 0;
    private Integer organisationUserType = 0;
    private String containerName = "";
    private String blobName = "";
    
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

	public Integer getSupportUserId() {
		return supportUserId;
	}

	public void setSupportUserId(Integer supportUserId) {
		this.supportUserId = supportUserId;
	}

	public Integer getFileId() {
		return fileId;
	}

	public void setFileId(Integer fileId) {
		this.fileId = fileId;
	}

	public Integer getOrganisationId() {
		return organisationId;
	}

	public void setOrganisationId(Integer organisationId) {
		this.organisationId = organisationId;
	}

	public Integer getOrganisationUserType() {
		return organisationUserType;
	}

	public void setOrganisationUserType(Integer organisationUserType) {
		this.organisationUserType = organisationUserType;
	}

	public String getContainerName() {
		return containerName;
	}

	public void setContainerName(String containerName) {
		this.containerName = containerName;
	}

	public String getBlobName() {
		return blobName;
	}

	public void setBlobName(String blobName) {
		this.blobName = blobName;
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
		return "SupportFileUploadLogs [id=" + id + ", supportUserId=" + supportUserId + ", fileId=" + fileId
				+ ", organisationId=" + organisationId + ", organisationUserType=" + organisationUserType
				+ ", containerName=" + containerName + ", blobName=" + blobName + ", createdAt=" + createdAt
				+ ", modifiedDate=" + modifiedDate + "]";
	}

}
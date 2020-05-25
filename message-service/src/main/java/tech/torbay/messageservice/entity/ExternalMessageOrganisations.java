package tech.torbay.messageservice.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name = "external_message_organisations")
public class ExternalMessageOrganisations {

    public ExternalMessageOrganisations() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id = 0;
    private Integer externalMessageId = 0;
    private Integer targetOrganisationId = 0;
    private Integer targetUserType = 0;
    
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

	public Integer getExternalMessageId() {
		return externalMessageId;
	}

	public void setExternalMessageId(Integer externalMessageId) {
		this.externalMessageId = externalMessageId;
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
		return "ExternalMessageOrganisations [id=" + id + ", externalMessageId=" + externalMessageId
				+ ", targetOrganisationId=" + targetOrganisationId + ", targetUserType=" + targetUserType
				+ ", createdAt=" + createdAt + ", modifiedDate=" + modifiedDate + "]";
	}

}
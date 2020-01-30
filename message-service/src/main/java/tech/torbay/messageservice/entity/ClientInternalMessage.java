package tech.torbay.messageservice.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name = "client_internal_message")
public class ClientInternalMessage {

    public ClientInternalMessage() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id = 0;
    private Integer clientOrganisationId = 0;
    private Integer clientUserId = 0;
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

	public Integer getClientOrganisationId() {
		return clientOrganisationId;
	}

	public void setClientOrganisationId(Integer clientOrganisationId) {
		this.clientOrganisationId = clientOrganisationId;
	}

	public Integer getClientUserId() {
		return clientUserId;
	}

	public void setClientUserId(Integer clientUserId) {
		this.clientUserId = clientUserId;
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
		return "ClientInternalMessage [id=" + id + ", clientOrganisationId=" + clientOrganisationId + ", clientUserId="
				+ clientUserId + ", threadSubject=" + threadSubject + ", threadDescription=" + threadDescription
				+ ", createdAt=" + createdAt + ", modifiedDate=" + modifiedDate + "]";
	}

}
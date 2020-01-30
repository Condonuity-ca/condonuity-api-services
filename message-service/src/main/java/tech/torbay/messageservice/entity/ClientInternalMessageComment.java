package tech.torbay.messageservice.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name = "client_internal_message_comment")
public class ClientInternalMessageComment {

    public ClientInternalMessageComment() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id = 0;
    private Integer clientUserId = 0;
    private Integer threadId = 0;
    private String comment = "";
    
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

	public Integer getClientUserId() {
		return clientUserId;
	}

	public void setClientUserId(Integer clientUserId) {
		this.clientUserId = clientUserId;
	}

	public Integer getThreadId() {
		return threadId;
	}

	public void setThreadId(Integer threadId) {
		this.threadId = threadId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
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
		return "ClientInternalMessageComment [id=" + id + ", clientUserId=" + clientUserId + ", threadId=" + threadId
				+ ", comment=" + comment + ", createdAt=" + createdAt + ", modifiedDate=" + modifiedDate + "]";
	}
}
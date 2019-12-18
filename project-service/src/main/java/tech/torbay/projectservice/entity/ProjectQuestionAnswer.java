package tech.torbay.projectservice.entity;

import javax.persistence.Table;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
//@JsonInclude(value = Include.NON_NULL)
@Table(name = "project_questions")
public class ProjectQuestionAnswer {

    public ProjectQuestionAnswer() {

    }
 
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer projectqaId = 0;
    private Integer projectId = 0;
    private Integer clientUserId = 0;
    private String question;
    private String answer;
    private Integer vendorUserId = 0;

    @Basic(optional = false)
    @Column(name = "created_at", insertable = false, updatable = false)
    private String createdAt;
    
    @Basic(optional = false)
    @Column(name = "modified_date", insertable = false, updatable = false)
    private String modifiedDate;
    
	public Integer getProjectqaId() {
		return projectqaId;
	}
	public void setProjectqaId(Integer projectqaId) {
		this.projectqaId = projectqaId;
	}
	public Integer getProjectId() {
		return projectId;
	}
	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}
	public Integer getClientUserId() {
		return clientUserId;
	}
	public void setClientUserId(Integer clientUserId) {
		this.clientUserId = clientUserId;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public Integer getVendorUserId() {
		return vendorUserId;
	}
	public void setVendorUserId(Integer vendorUserId) {
		this.vendorUserId = vendorUserId;
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
		return "ProjectQuestionAnswer [projectqaId=" + projectqaId + ", projectId=" + projectId + ", clientUserId="
				+ clientUserId + ", question=" + question + ", answer=" + answer + ", vendorUserId=" + vendorUserId
				+ ", createdAt=" + createdAt + ", modifiedDate=" + modifiedDate + "]";
	}
    
}
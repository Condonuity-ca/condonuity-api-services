package tech.torbay.projectservice.entity;

import javax.persistence.Table;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_NULL)
@Table(name = "project_questions")
public class ProjectQuestionAnswer {

    public ProjectQuestionAnswer() {

    }
 
    @Id
	private Integer projectQAId = 0;
    private Integer projectId = 0;
    private String createdDate;
    private Integer createdUserId = 0;
    private Integer createdUserType = 0;
    private String question;
    private String answer;
    private Integer answeredUserId = 0;
    private Integer answeredUserType = 0;
	public Integer getId() {
		return projectQAId;
	}
	public void setId(Integer id) {
		projectQAId = id;
	}
	public Integer getProjectId() {
		return projectId;
	}
	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public Integer getCreatedUserId() {
		return createdUserId;
	}
	public void setCreatedUserId(Integer createdUserId) {
		this.createdUserId = createdUserId;
	}
	public Integer getCreatedUserType() {
		return createdUserType;
	}
	public void setCreatedUserType(Integer createdUserType) {
		this.createdUserType = createdUserType;
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
	public Integer getAnsweredUserId() {
		return answeredUserId;
	}
	public void setAnsweredUserId(Integer answeredUserId) {
		this.answeredUserId = answeredUserId;
	}
	public Integer getAnsweredUserType() {
		return answeredUserType;
	}
	public void setAnsweredUserType(Integer answeredUserType) {
		this.answeredUserType = answeredUserType;
	}
	
	@Override
	public String toString() {
		return "ProjectQuestionAnswer [Id=" + projectQAId + ", projectId=" + projectId + ", createdDate=" + createdDate
				+ ", createdUserId=" + createdUserId + ", createdUserType=" + createdUserType + ", question=" + question
				+ ", answer=" + answer + ", answeredUserId=" + answeredUserId + ", answeredUserType=" + answeredUserType
				+ "]";
	}
    
}
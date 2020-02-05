package tech.torbay.fileservice.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "comment_files")
public class CommentFiles {

    public CommentFiles() {

    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id = 0;
    private Integer commentId = 0;
    private Integer threadType = 0;
    private String containerName = "";
    private String blobName = "";
    private String fileName = "";
    private String fileType = "";
    private String fileSize = "";
    private String fileUrl = "";
    
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

	public Integer getCommentId() {
		return commentId;
	}

	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}

	public Integer getThreadType() {
		return threadType;
	}

	public void setThreadType(Integer threadType) {
		this.threadType = threadType;
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

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getFileSize() {
		return fileSize;
	}

	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
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
		return "CommentFiles [id=" + id + ", commentId=" + commentId + ", threadType=" + threadType + ", containerName="
				+ containerName + ", blobName=" + blobName + ", fileName=" + fileName + ", fileType=" + fileType
				+ ", fileSize=" + fileSize + ", fileUrl=" + fileUrl + ", createdAt=" + createdAt + ", modifiedDate="
				+ modifiedDate + "]";
	}

}
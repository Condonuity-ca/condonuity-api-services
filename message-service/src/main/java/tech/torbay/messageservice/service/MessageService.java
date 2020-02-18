package tech.torbay.messageservice.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.catalina.manager.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import tech.torbay.messageservice.Utils.Utils;
import tech.torbay.messageservice.constants.Constants.ThreadType;
import tech.torbay.messageservice.entity.CommentFiles;
import tech.torbay.messageservice.entity.ExternalMessage;
import tech.torbay.messageservice.entity.ExternalMessageComment;
import tech.torbay.messageservice.entity.InternalMessage;
import tech.torbay.messageservice.entity.InternalMessageComment;
import tech.torbay.messageservice.entity.ThreadFiles;
import tech.torbay.messageservice.repository.InternalMessageCommentRepository;
import tech.torbay.messageservice.repository.InternalMessageRepository;
import tech.torbay.messageservice.repository.CommentFilesRepository;
import tech.torbay.messageservice.repository.ExternalMessageCommentRepository;
import tech.torbay.messageservice.repository.ExternalMessageRepository;
import tech.torbay.messageservice.repository.ThreadFilesRepository;

@Component
public class MessageService {
	
	@Autowired
	InternalMessageRepository internalMessageRepository;
	@Autowired
	InternalMessageCommentRepository internalMessageCommentRepository;
	@Autowired
	ThreadFilesRepository threadFilesRepository;
	@Autowired
	CommentFilesRepository commentFilesRepository;
	@Autowired
	ExternalMessageRepository externalMessageRepository;
	@Autowired
	ExternalMessageCommentRepository externalMessageCommentRepository;
//	@Autowired
//	ExternalThreadFilesRepository externalThreadFilesRepository;
//	@Autowired
//	ExternalThreadCommentFilesRepository externalThreadcommentFilesRepository;

	public InternalMessage createThread(InternalMessage internalMessage) {
		// TODO Auto-generated method stub
		return internalMessageRepository.save(internalMessage);
	}

	public InternalMessageComment createThreadComment(InternalMessageComment internalMessageComment) {
		// TODO Auto-generated method stub
		return internalMessageCommentRepository.save(internalMessageComment);
	}

	public List<Map<String,Object>> getInternalMessages(Integer organisationId, Integer userType) {
		// TODO Auto-generated method stub

		List<InternalMessage> internalMessages = internalMessageRepository.findAllByOrganisationIdAndUserType(organisationId, userType);
		
		List<Map<String,Object>> allMessages = new ArrayList();
		
		for(InternalMessage internalMessage : internalMessages) {
			
			Map<String,Object> map = new HashMap<>();
			ObjectMapper oMapper = new ObjectMapper();
			map = oMapper.convertValue(internalMessage, Map.class);
			
			List<ThreadFiles> threadFiles = threadFilesRepository.findAllByThreadIdAndThreadType(internalMessage.getId(), ThreadType.INTERNAL.getValue());
			List<Map<String,Object>> allFiles = new ArrayList();
			for(ThreadFiles threadFile : threadFiles) {
				Map<String,Object> file = new HashMap<>();
				file.put("id", threadFile.getId());
				file.put("fileName", threadFile.getFileName());
				file.put("fileType", threadFile.getFileType());
				file.put("fileUrl", threadFile.getFileUrl());
				file.put("createdAt", threadFile.getCreatedAt());
				file.put("modifiedDate", threadFile.getModifiedDate());
				allFiles.add(file);
			}
			
			map.put("files",allFiles);
			map.put("comments",getInternalThreadComments(internalMessage.getId()));
			
			allMessages.add(map);
		}
		
		return allMessages;
	}

	private List<Map<String,Object>> getInternalThreadComments(Integer threadId) {
		// TODO Auto-generated method stub
		List<InternalMessageComment> internalMessageComments = internalMessageCommentRepository.findAllByThreadId(threadId);
		
		List<Map<String,Object>> allComments = new ArrayList();
		
		for(InternalMessageComment internalMessageComment : internalMessageComments) {
			
			Map<String,Object> map = new HashMap<>();
			ObjectMapper oMapper = new ObjectMapper();
			map = oMapper.convertValue(internalMessageComment, Map.class);
			
			List<CommentFiles> commentFiles = commentFilesRepository.findAllByCommentIdAndThreadType(internalMessageComment.getId(), ThreadType.INTERNAL.getValue());
			List<Map<String,Object>> allFiles = new ArrayList();
			for(CommentFiles commentFile : commentFiles) {
				Map<String,Object> file = new HashMap<>();
				file.put("id", commentFile.getId());
				file.put("fileName", commentFile.getFileName());
				file.put("fileType", commentFile.getFileType());
				file.put("fileSize", Utils.formatFileSize(Long.parseLong(commentFile.getFileSize())));
				file.put("blobName", commentFile.getBlobName());
				file.put("containerName", commentFile.getContainerName());
//				file.put("fileUrl", commentFile.getFileUrl());
				file.put("createdAt", commentFile.getCreatedAt());
//				file.put("modifiedDate", commentFile.getModifiedDate());
				allFiles.add(file);
			}
			map.put("files",allFiles);
			allComments.add(map);
		}
		return allComments;
	}
	
	
	public ExternalMessage createExternalThread(ExternalMessage internalMessage) {
		// TODO Auto-generated method stub
		return externalMessageRepository.save(internalMessage);
	}

	public ExternalMessageComment createThreadComment(ExternalMessageComment internalMessageComment) {
		// TODO Auto-generated method stub
		return externalMessageCommentRepository.save(internalMessageComment);
	}

	public List<Map<String,Object>> getExternalMessages(Integer organisationId, Integer userType) {
		// TODO Auto-generated method stub

		List<ExternalMessage> externalMessages = externalMessageRepository.findAllBySourceOrganisationIdAndSourceUserType(organisationId, userType);
		
		List<Map<String,Object>> allMessages = new ArrayList();
		
		for(ExternalMessage externalMessage : externalMessages) {
			
			Map<String,Object> map = new HashMap<>();
			ObjectMapper oMapper = new ObjectMapper();
			map = oMapper.convertValue(externalMessage, Map.class);
			
			List<ThreadFiles> threadFiles = threadFilesRepository.findAllByThreadIdAndThreadType(externalMessage.getId(), ThreadType.EXTERNAL.getValue());
			List<Map<String,Object>> allFiles = new ArrayList();
			for(ThreadFiles threadFile : threadFiles) {
				Map<String,Object> file = new HashMap<>();
				file.put("id", threadFile.getId());
				file.put("fileName", threadFile.getFileName());
				file.put("fileType", threadFile.getFileType());
				file.put("fileSize", Utils.formatFileSize(Long.parseLong(threadFile.getFileSize())));
				file.put("blobName", threadFile.getBlobName());
				file.put("containerName", threadFile.getContainerName());
//				file.put("fileUrl", threadFile.getFileUrl());
				file.put("createdAt", threadFile.getCreatedAt());
//				file.put("modifiedDate", threadFile.getModifiedDate());
				allFiles.add(file);
			}
			
			map.put("files",allFiles);
			map.put("comments",getExternalThreadComments(externalMessage.getId()));
			
			allMessages.add(map);
		}
		
		return allMessages;
	}

	private List<Map<String,Object>> getExternalThreadComments(Integer threadId) {
		// TODO Auto-generated method stub
		List<ExternalMessageComment> externalMessageComments = externalMessageCommentRepository.findAllByThreadId(threadId);
		
		List<Map<String,Object>> allComments = new ArrayList();
		
		for(ExternalMessageComment externalMessageComment : externalMessageComments) {
			
			Map<String,Object> map = new HashMap<>();
			ObjectMapper oMapper = new ObjectMapper();
			map = oMapper.convertValue(externalMessageComment, Map.class);
			
			List<CommentFiles> commentFiles = commentFilesRepository.findAllByCommentIdAndThreadType(externalMessageComment.getId(), ThreadType.EXTERNAL.getValue());
			List<Map<String,Object>> allFiles = new ArrayList();
			for(CommentFiles commentFile : commentFiles) {
				Map<String,Object> file = new HashMap<>();
				file.put("id", commentFile.getId());
				file.put("fileName", commentFile.getFileName());
				file.put("fileType", commentFile.getFileType());
				file.put("fileSize", Utils.formatFileSize(Long.parseLong(commentFile.getFileSize())));
				file.put("blobName", commentFile.getBlobName());
				file.put("containerName", commentFile.getContainerName());
//				file.put("fileUrl", commentFile.getFileUrl());
				file.put("createdAt", commentFile.getCreatedAt());
//				file.put("modifiedDate", commentFile.getModifiedDate());
				allFiles.add(file);
			}
			map.put("files","[]"/*allFiles*/);
			allComments.add(map);
		}
		return allComments;
	}

}

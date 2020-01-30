package tech.torbay.messageservice.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import tech.torbay.messageservice.entity.CommentFiles;
import tech.torbay.messageservice.entity.InternalMessage;
import tech.torbay.messageservice.entity.InternalMessageComment;
import tech.torbay.messageservice.entity.ThreadFiles;
import tech.torbay.messageservice.repository.InternalMessageCommentRepository;
import tech.torbay.messageservice.repository.InternalMessageRepository;
import tech.torbay.messageservice.repository.CommentFilesRepository;
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
			
			List<ThreadFiles> threadFiles = threadFilesRepository.findAllByThreadId(internalMessage.getId());
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
			map.put("comments",getComments(internalMessage.getId()));
			
			allMessages.add(map);
		}
		
		return allMessages;
	}

	private List<Map<String,Object>> getComments(Integer threadId) {
		// TODO Auto-generated method stub
		List<InternalMessageComment> internalMessageComments = internalMessageCommentRepository.findAllByThreadId(threadId);
		
		List<Map<String,Object>> allComments = new ArrayList();
		
		for(InternalMessageComment internalMessageComment : internalMessageComments) {
			
			Map<String,Object> map = new HashMap<>();
			ObjectMapper oMapper = new ObjectMapper();
			map = oMapper.convertValue(internalMessageComment, Map.class);
			
			List<CommentFiles> commentFiles = commentFilesRepository.findAllByCommentId(internalMessageComment.getId());
			List<Map<String,Object>> allFiles = new ArrayList();
			for(CommentFiles commentFile : commentFiles) {
				Map<String,Object> file = new HashMap<>();
				file.put("id", commentFile.getId());
				file.put("fileName", commentFile.getFileName());
				file.put("fileType", commentFile.getFileType());
				file.put("fileUrl", commentFile.getFileUrl());
				file.put("createdAt", commentFile.getCreatedAt());
				file.put("modifiedDate", commentFile.getModifiedDate());
				allFiles.add(file);
			}
			map.put("files",allFiles);
			allComments.add(map);
		}
		return allComments;
	}

}

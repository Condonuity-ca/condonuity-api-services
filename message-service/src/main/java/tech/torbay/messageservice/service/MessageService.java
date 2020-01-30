package tech.torbay.messageservice.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import tech.torbay.messageservice.entity.ClientInternalMessage;
import tech.torbay.messageservice.entity.ClientInternalMessageComment;
import tech.torbay.messageservice.repository.ClientInternalMessageCommentRepository;
import tech.torbay.messageservice.repository.ClientInternalMessageRepository;
import tech.torbay.messageservice.repository.CommentFilesRepository;
import tech.torbay.messageservice.repository.ThreadFilesRepository;

@Component
public class MessageService {
	
	@Autowired
	ClientInternalMessageRepository clientInternalMessageRepository;
	@Autowired
	ClientInternalMessageCommentRepository clientInternalMessageCommentRepository;
	@Autowired
	ThreadFilesRepository threadFilesRepository;
	@Autowired
	CommentFilesRepository commentFilesRepository;

	public ClientInternalMessage createThread(ClientInternalMessage clientInternalMessage) {
		// TODO Auto-generated method stub
		return clientInternalMessageRepository.save(clientInternalMessage);
	}

	public ClientInternalMessageComment createThreadComment(ClientInternalMessageComment clientInternalMessageComment) {
		// TODO Auto-generated method stub
		return clientInternalMessageCommentRepository.save(clientInternalMessageComment);
	}

	public List<Map<String,Object>> getClientInternalMessages(Integer id) {
		// TODO Auto-generated method stub

		List<ClientInternalMessage> clientInternalMessages = clientInternalMessageRepository.findAllByClientOrganisationId(id);
		
		List<Map<String,Object>> allMessages = new ArrayList();
		
		for(ClientInternalMessage clientInternalMessage : clientInternalMessages) {
			
			Map<String,Object> map = new HashMap<>();
			ObjectMapper oMapper = new ObjectMapper();
			map = oMapper.convertValue(clientInternalMessage, Map.class);
			
			map.put("files",threadFilesRepository.findAllByThreadId(clientInternalMessage.getId()));
			map.put("comments",getComments(clientInternalMessage.getId()));
			
			allMessages.add(map);
		}
		
		return allMessages;
	}

	private List<Map<String,Object>> getComments(Integer threadId) {
		// TODO Auto-generated method stub
		List<ClientInternalMessageComment> clientInternalMessageComments = clientInternalMessageCommentRepository.findAllByThreadId(threadId);
		
		List<Map<String,Object>> allComments = new ArrayList();
		
		for(ClientInternalMessageComment clientInternalMessageComment : clientInternalMessageComments) {
			
			Map<String,Object> map = new HashMap<>();
			ObjectMapper oMapper = new ObjectMapper();
			map = oMapper.convertValue(clientInternalMessageComment, Map.class);
			
			map.put("files",commentFilesRepository.findAllByCommentId(clientInternalMessageComment.getId()));
			
			allComments.add(map);
		}
		
		return allComments;
	}

}

package tech.torbay.messageservice.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import tech.torbay.messageservice.Utils.Utils;
import tech.torbay.messageservice.constants.Constants;
import tech.torbay.messageservice.constants.Constants.Availability;
import tech.torbay.messageservice.constants.Constants.DeleteStatus;
import tech.torbay.messageservice.constants.Constants.ThreadType;
import tech.torbay.messageservice.entity.ClientOrganisation;
import tech.torbay.messageservice.entity.ClientOrganisationProfileImages;
import tech.torbay.messageservice.entity.ClientUser;
import tech.torbay.messageservice.entity.CommentFiles;
import tech.torbay.messageservice.entity.ExternalMessage;
import tech.torbay.messageservice.entity.ExternalMessageComment;
import tech.torbay.messageservice.entity.ExternalMessageOrganisations;
import tech.torbay.messageservice.entity.InternalMessage;
import tech.torbay.messageservice.entity.InternalMessageComment;
import tech.torbay.messageservice.entity.ThreadFiles;
import tech.torbay.messageservice.entity.UserLevelNotification;
import tech.torbay.messageservice.entity.UserProfileImages;
import tech.torbay.messageservice.entity.VendorOrganisation;
import tech.torbay.messageservice.entity.VendorOrganisationProfileImages;
import tech.torbay.messageservice.entity.VendorUser;
import tech.torbay.messageservice.repository.ClientOrganisationProfileImagesRepository;
import tech.torbay.messageservice.repository.ClientOrganisationRepository;
import tech.torbay.messageservice.repository.ClientUserRepository;
import tech.torbay.messageservice.repository.CommentFilesRepository;
import tech.torbay.messageservice.repository.ExternalMessageCommentRepository;
import tech.torbay.messageservice.repository.ExternalMessageOrganisationsRepository;
import tech.torbay.messageservice.repository.ExternalMessageRepository;
import tech.torbay.messageservice.repository.InternalMessageCommentRepository;
import tech.torbay.messageservice.repository.InternalMessageRepository;
import tech.torbay.messageservice.repository.ThreadFilesRepository;
import tech.torbay.messageservice.repository.UserLevelNotificationRepository;
import tech.torbay.messageservice.repository.UserProfileImagesRepository;
import tech.torbay.messageservice.repository.VendorOrganisationProfileImagesRepository;
import tech.torbay.messageservice.repository.VendorOrganisationRepository;
import tech.torbay.messageservice.repository.VendorUserRepository;

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
	ExternalMessageOrganisationsRepository externalMessageOrganisationsRepository;
	@Autowired
	ExternalMessageCommentRepository externalMessageCommentRepository;
//	@Autowired
//	ExternalThreadFilesRepository externalThreadFilesRepository;
//	@Autowired
//	ExternalThreadCommentFilesRepository externalThreadcommentFilesRepository;
	@Autowired
	UserLevelNotificationRepository userLevelNotificationRepository;
	@Autowired
	ClientUserRepository clientUserRepository;
	@Autowired
	VendorUserRepository vendorUserRepository;
	@Autowired
	ClientOrganisationRepository clientOrganisationRepository;
	@Autowired
	VendorOrganisationRepository vendorOrganisationRepository;
	@Autowired
	ClientOrganisationProfileImagesRepository clientOrganisationProfileImageRepository;
	@Autowired
	VendorOrganisationProfileImagesRepository vendorOrganisationProfileImagesRepository;
	@Autowired
	UserProfileImagesRepository userProfileImagesRepository;
	
	public InternalMessage createThread(InternalMessage internalMessage) {
		// TODO Auto-generated method stub
		return internalMessageRepository.save(internalMessage);
	}

	public InternalMessageComment createThreadComment(InternalMessageComment internalMessageComment) {
		// TODO Auto-generated method stub
		return internalMessageCommentRepository.save(internalMessageComment);
	}

	public List<Map<String,Object>> getInternalMessages(Integer organisationId, Integer userType, Integer userId) {
		// TODO Auto-generated method stub

		List<InternalMessage> internalMessages = internalMessageRepository.findAllByOrganisationIdAndUserTypeAndDeleteStatus(organisationId, userType, DeleteStatus.ACTIVE.getValue());
		
		List<Map<String,Object>> allMessages = new ArrayList<>();
		
		for(InternalMessage internalMessage : internalMessages) {
			
			Map<String,Object> map = new HashMap<>();
			ObjectMapper oMapper = new ObjectMapper();
			map = oMapper.convertValue(internalMessage, Map.class);
			map.remove("userId");	
			map.remove("organisationId");	
			
			Map<String,Object> user = new HashMap<>();
			Map<String,Object> organisation = new HashMap<>();
			
			UserProfileImages userProfileImage = userProfileImagesRepository.findByUserIdAndUserType(internalMessage.getUserId(), internalMessage.getUserType());
			
			if(internalMessage.getUserType() == Constants.UserType.CLIENT.getValue()) {
				
				ClientUser clientUser = clientUserRepository.findByClientId(internalMessage.getUserId());
				user.put("userId",clientUser.getClientId());
				user.put("firstName",clientUser.getFirstName());
				user.put("lastName",clientUser.getLastName());
				if(userProfileImage != null)
					user.put("profileImageURL",userProfileImage.getFileUrl());
				else
					user.put("profileImageURL","");
				
				ClientOrganisation clientOrganisation = clientOrganisationRepository.findByClientOrganisationId(internalMessage.getOrganisationId());
				organisation.put("organisationId",clientOrganisation.getClientOrganisationId());
				organisation.put("managementCompany",clientOrganisation.getManagementCompany());
				organisation.put("corporateNumber",clientOrganisation.getCorporateNumber());
				organisation.put("organisationName",clientOrganisation.getOrganisationName());
				organisation.put("organisationLogo",getClientOrganisationLogo(clientOrganisation.getClientOrganisationId()));
				
			} else if(internalMessage.getUserType() == Constants.UserType.VENDOR.getValue()) {
				
				VendorUser vendorUser = vendorUserRepository.findByUserId(internalMessage.getUserId());
				user.put("userId",vendorUser.getUserId());
				user.put("firstName",vendorUser.getFirstName());
				user.put("lastName",vendorUser.getLastName());
				if(userProfileImage != null)
					user.put("profileImageURL",userProfileImage.getFileUrl());
				else
					user.put("profileImageURL","");
				
				VendorOrganisation vendorOrganisation = vendorOrganisationRepository.findByVendorOrganisationId(internalMessage.getOrganisationId());
				organisation.put("organisationId",vendorOrganisation.getVendorOrganisationId());
				organisation.put("legalName",vendorOrganisation.getLegalName());
				organisation.put("organisationName",vendorOrganisation.getCompanyName());
				organisation.put("organisationLogoName",vendorOrganisation.getLogoName());
				organisation.put("organisationLogo",getVendorOrganisationLogo(vendorOrganisation.getVendorOrganisationId()));
			}
			
			
			map.put("user",user);
			map.put("organisation",organisation);
			
			
			List<ThreadFiles> threadFiles = threadFilesRepository.findAllByThreadIdAndThreadType(internalMessage.getId(), ThreadType.INTERNAL.getValue());
			List<Map<String,Object>> allFiles = new ArrayList();
			for(ThreadFiles threadFile : threadFiles) {
				Map<String,Object> file = new HashMap<>();
				
				file.put("id", threadFile.getId());
				file.put("fileName", threadFile.getFileName());
				file.put("fileType", threadFile.getFileType());
				file.put("fileSize", Utils.formatFileSize(Long.parseLong(threadFile.getFileSize())));
				file.put("blobName", threadFile.getBlobName());
				file.put("containerName", threadFile.getContainerName());
//				file.put("fileUrl", commentFile.getFileUrl());
				file.put("createdAt", threadFile.getCreatedAt());
//				file.put("modifiedDate", commentFile.getModifiedDate());
				allFiles.add(file);
			}
			
			map.put("files",allFiles);
			map.put("comments",getInternalThreadComments(internalMessage.getId(), userType, userId));
			
			allMessages.add(map);
		}
		
		return allMessages;
	}
	
	public String getClientOrganisationLogo(Integer id) {
		// TODO Auto-generated method stub
		ClientOrganisationProfileImages clientOrganisationProfileImages = clientOrganisationProfileImageRepository.findByClientOrganisationId(id);
		
        if(clientOrganisationProfileImages != null)
        	return clientOrganisationProfileImages.getFileUrl();
        else
        	return null;
	}
	
	public String getVendorOrganisationLogo(Integer vendorOrganisationId) {
		// TODO Auto-generated method stub
		VendorOrganisationProfileImages vendorOrgProfileImage =  vendorOrganisationProfileImagesRepository.findByVendorOrganisationId(vendorOrganisationId);
		
        if(vendorOrgProfileImage != null)
        	return vendorOrgProfileImage.getFileUrl();
        else
        	return null;
	} 
	
	private List<Map<String,Object>> getInternalThreadComments(Integer threadId, Integer userType, Integer userId) {
		// TODO Auto-generated method stub
		List<InternalMessageComment> internalMessageComments = internalMessageCommentRepository.findAllByThreadId(threadId);
		
		List<Map<String,Object>> allComments = new ArrayList();
		
		for(InternalMessageComment internalMessageComment : internalMessageComments) {
			
			Map<String,Object> map = new HashMap<>();
			ObjectMapper oMapper = new ObjectMapper();
			map = oMapper.convertValue(internalMessageComment, Map.class);
			map.remove("userId");	
			
			Map<String,Object> user = new HashMap<>();
//			Map<String,Object> organisation = new HashMap<>();
			
			UserProfileImages userProfileImage = userProfileImagesRepository.findByUserIdAndUserType(internalMessageComment.getUserId(), internalMessageComment.getUserType());
			
			if(internalMessageComment.getUserType() == Constants.UserType.CLIENT.getValue()) {
				
				ClientUser clientUser = clientUserRepository.findByClientId(internalMessageComment.getUserId());
				if(userId == clientUser.getClientId() && userType == internalMessageComment.getUserType())
					user.put("isCommented",true);
				else
					user.put("isCommented", false);
				
				user.put("userId",clientUser.getClientId());
				user.put("firstName",clientUser.getFirstName());
				user.put("lastName",clientUser.getLastName());
				if(userProfileImage != null)
					user.put("profileImageURL",userProfileImage.getFileUrl());
				else
					user.put("profileImageURL","");
				
			} else if(internalMessageComment.getUserType() == Constants.UserType.VENDOR.getValue()) {
				
				VendorUser vendorUser = vendorUserRepository.findByUserId(internalMessageComment.getUserId());
				if(userId == vendorUser.getUserId() && userType == internalMessageComment.getUserType())
					user.put("isCommented",true);
				else
					user.put("isCommented", false);
				
				user.put("userId",vendorUser.getUserId());
				user.put("firstName",vendorUser.getFirstName());
				user.put("lastName",vendorUser.getLastName());
				if(userProfileImage != null)
					user.put("profileImageURL",userProfileImage.getFileUrl());
				else
					user.put("profileImageURL","");
				
			}
			
			
			map.put("user",user);
//			map.put("organisation",organisation);
			
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
	
	public ExternalMessage createExternalThread(Map<String, Object> externalMessageData) {
		// TODO Auto-generated method stub
//		return externalMessageRepository.save(internalMessage);
//		Integer sourceOrganisationId = Integer.parseInt(String.valueOf(externalMessageData.get("sourceOrganisationId")));
//		Integer sourceUserId = Integer.parseInt(String.valueOf(externalMessageData.get("sourceUserId")));
//		Integer sourceUserType = Integer.parseInt(String.valueOf(externalMessageData.get("sourceUserType")));
//		Integer targetOrganisationId = Integer.parseInt(String.valueOf(externalMessageData.get("sourceOrganisationId")));
//		Integer targetUserType = Integer.parseInt(String.valueOf(externalMessageData.get("sourceOrganisationId")));
//		String threadDescription = String.valueOf(externalMessageData.get("threadDescription"));
//		String threadSubject = String.valueOf(externalMessageData.get("threadSubject"));
//		String threadSubject = String.valueOf(externalMessageData.get("threadSubject"));
//		
//		ExternalMessage externalMessage = new ExternalMessage();
//		externalMessage
//		
//		ExternalMessage externalMessageObj = externalMessageRepository.save(externalMessage)
		final ObjectMapper mapper = new ObjectMapper();
		ExternalMessage externalMessage = mapper.convertValue(externalMessageData.get("externalMessage"), ExternalMessage.class);
		List<HashMap<String,Object>> externalMessageOrganisations = mapper.convertValue(externalMessageData.get("targetOrganisations"), List.class);
		
		ExternalMessage externalMessageObj = externalMessageRepository.save(externalMessage);
		
		List<String> targetOrgs = new ArrayList();
		List<String> targetUserTypes = new ArrayList();
		
		for(HashMap<String,Object> externalMessageOrgObj : externalMessageOrganisations) {
			
			ExternalMessageOrganisations externalMessageOrg = mapper.convertValue(externalMessageOrgObj, ExternalMessageOrganisations.class);
			
			externalMessageOrg.setExternalMessageId(externalMessageObj.getId());
			
			externalMessageOrganisationsRepository.save(externalMessageOrg);
			
			targetOrgs.add(String.valueOf(externalMessageOrg.getTargetOrganisationId()));
			targetUserTypes.add(String.valueOf(externalMessageOrg.getTargetUserType()));
		}
		externalMessageObj.setTargetOrganisationId(String.join(",", targetOrgs));
		externalMessageObj.setTargetUserType(String.join(",", new HashSet(targetUserTypes)));
		externalMessageRepository.save(externalMessageObj);
		return externalMessageObj;
	}

	public ExternalMessageComment createThreadComment(ExternalMessageComment externalMessageComment) {
		// TODO Auto-generated method stub
		return externalMessageCommentRepository.save(externalMessageComment);
	}

	public List<Map<String,Object>> getExternalMessages(Integer organisationId, Integer userType, Integer userId) {
		// TODO Auto-generated method stub

		List<ExternalMessage> externalMessages = externalMessageRepository.findAllByOrganisationIdAndUserTypeAndDeleteStatus(organisationId, userType, DeleteStatus.ACTIVE.getValue());
		
		HashSet<ExternalMessage> resultSet = new HashSet(externalMessages);
		externalMessages.clear();
		externalMessages.addAll(resultSet);
		
		List<Map<String,Object>> allMessages = new ArrayList();
		
		for(ExternalMessage externalMessage : externalMessages) {
			
			Map<String,Object> map = new HashMap<>();
			ObjectMapper oMapper = new ObjectMapper();
			map = oMapper.convertValue(externalMessage, Map.class);
			map.remove("sourceOrganisationId");	
			map.remove("sourceUserId");	
			map.remove("targetOrganisationId");
			map.remove("targetUserType");
			
			Map<String,Object> user = new HashMap<>();
			Map<String,Object> organisation = new HashMap<>();
			
			UserProfileImages userProfileImage = userProfileImagesRepository.findByUserIdAndUserType(externalMessage.getSourceUserId(), externalMessage.getSourceUserType());
			
			if(externalMessage.getSourceUserType() == Constants.UserType.CLIENT.getValue()) {
				
				ClientUser clientUser = clientUserRepository.findByClientId(externalMessage.getSourceUserId());
				user.put("userId",clientUser.getClientId());
				user.put("firstName",clientUser.getFirstName());
				user.put("lastName",clientUser.getLastName());
				if(userProfileImage != null)
					user.put("profileImageURL",userProfileImage.getFileUrl());
				else
					user.put("profileImageURL","");
				
				ClientOrganisation clientOrganisation = clientOrganisationRepository.findByClientOrganisationId(externalMessage.getSourceOrganisationId());
				organisation.put("organisationId",clientOrganisation.getClientOrganisationId());
				organisation.put("managementCompany",clientOrganisation.getManagementCompany());
				organisation.put("corporateNumber",clientOrganisation.getCorporateNumber());
				organisation.put("organisationName",clientOrganisation.getOrganisationName());
				organisation.put("organisationLogo",getClientOrganisationLogo(clientOrganisation.getClientOrganisationId()));
				
			} else if(externalMessage.getSourceUserType() == Constants.UserType.VENDOR.getValue()) {
				
				VendorUser vendorUser = vendorUserRepository.findByUserId(externalMessage.getSourceUserId());
				user.put("userId",vendorUser.getUserId());
				user.put("firstName",vendorUser.getFirstName());
				user.put("lastName",vendorUser.getLastName());
				if(userProfileImage != null)
					user.put("profileImageURL",userProfileImage.getFileUrl());
				else
					user.put("profileImageURL","");
				
				VendorOrganisation vendorOrganisation = vendorOrganisationRepository.findByVendorOrganisationId(externalMessage.getSourceOrganisationId());
				organisation.put("organisationId",vendorOrganisation.getVendorOrganisationId());
				organisation.put("legalName",vendorOrganisation.getLegalName());
				organisation.put("organisationName",vendorOrganisation.getCompanyName());
				organisation.put("organisationLogoName",vendorOrganisation.getLogoName());
				organisation.put("organisationLogo",getVendorOrganisationLogo(vendorOrganisation.getVendorOrganisationId()));
			}
			
			
			map.put("fromUser",user);
			map.put("fromOrganisation",organisation);
			
			map.put("targetOrganisations",getExternalMessageTargetOrganisations(externalMessage.getId()));
			
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
			map.put("comments",getExternalThreadComments(externalMessage.getId(), userType, userId));
			
			allMessages.add(map);
		}
		
		return allMessages;
	}
	
	private List<Map<String,Object>> getExternalMessageTargetOrganisations(Integer externalMessageId) {
		// TODO Auto-generated method stub
		List<ExternalMessageOrganisations> TargetOrgs = externalMessageOrganisationsRepository.findAllByExternalMessageId(externalMessageId);
		
		List<Map<String,Object>> targetOrganisations = new ArrayList<>();
		
		for(ExternalMessageOrganisations extTargetOrg : TargetOrgs) {
			
			Map<String,Object> targetOrg = new HashMap<>();
			
			targetOrg.put("userType",extTargetOrg.getTargetUserType());
			
			if(extTargetOrg.getTargetUserType() == Constants.UserType.CLIENT.getValue()) {
				
				ClientOrganisation clientOrganisation = clientOrganisationRepository.findByClientOrganisationId(extTargetOrg.getTargetOrganisationId());
				targetOrg.put("organisationId",clientOrganisation.getClientOrganisationId());
				targetOrg.put("managementCompany",clientOrganisation.getManagementCompany());
				targetOrg.put("corporateNumber",clientOrganisation.getCorporateNumber());
				targetOrg.put("organisationName",clientOrganisation.getOrganisationName());
				targetOrg.put("organisationLogo",getClientOrganisationLogo(clientOrganisation.getClientOrganisationId()));
				
			} else if(extTargetOrg.getTargetUserType() == Constants.UserType.VENDOR.getValue()) {
				
				VendorOrganisation vendorOrganisation = vendorOrganisationRepository.findByVendorOrganisationId(extTargetOrg.getTargetOrganisationId());
				targetOrg.put("organisationId",vendorOrganisation.getVendorOrganisationId());
				targetOrg.put("legalName",vendorOrganisation.getLegalName());
				targetOrg.put("organisationName",vendorOrganisation.getCompanyName());
				targetOrg.put("organisationLogoName",vendorOrganisation.getLogoName());
				targetOrg.put("organisationLogo",getVendorOrganisationLogo(vendorOrganisation.getVendorOrganisationId()));
			}
			
			targetOrganisations.add(targetOrg);
		}
		
		return targetOrganisations;
	}

	private List<Map<String,Object>> getExternalThreadComments(Integer threadId, Integer userType, Integer userId) {
		// TODO Auto-generated method stub
		List<ExternalMessageComment> externalMessageComments = externalMessageCommentRepository.findAllByThreadIdAndDeleteStatus(threadId, DeleteStatus.ACTIVE.getValue());
		
		List<Map<String,Object>> allComments = new ArrayList();
		
		for(ExternalMessageComment externalMessageComment : externalMessageComments) {
			
			Map<String,Object> map = new HashMap<>();
			ObjectMapper oMapper = new ObjectMapper();
			map = oMapper.convertValue(externalMessageComment, Map.class);
			map.remove("userId");	
			
			Map<String,Object> user = new HashMap<>();
			
			UserProfileImages userProfileImage = userProfileImagesRepository.findByUserIdAndUserType(externalMessageComment.getUserId(), externalMessageComment.getUserType());
			
			if(externalMessageComment.getUserType() == Constants.UserType.CLIENT.getValue()) {
				
				ClientUser clientUser = clientUserRepository.findByClientId(externalMessageComment.getUserId());
				if(userId == clientUser.getClientId() && userType == externalMessageComment.getUserType())
					user.put("isCommented",true);
				else
					user.put("isCommented", false);
				
				user.put("userId",clientUser.getClientId());
				user.put("firstName",clientUser.getFirstName());
				user.put("lastName",clientUser.getLastName());
				if(userProfileImage != null)
					user.put("profileImageURL",userProfileImage.getFileUrl());
				else
					user.put("profileImageURL","");
				
				ClientOrganisation clientOrganisation = clientOrganisationRepository.findByClientOrganisationId(externalMessageComment.getOrganisationId());
				user.put("organisationId",clientOrganisation.getClientOrganisationId());
				user.put("organisationName",clientOrganisation.getOrganisationName());
				
				
			} else if(externalMessageComment.getUserType() == Constants.UserType.VENDOR.getValue()) {
				
				VendorUser vendorUser = vendorUserRepository.findByUserId(externalMessageComment.getUserId());
				if(userId == vendorUser.getUserId() && userType == externalMessageComment.getUserType())
					user.put("isCommented",true);
				else
					user.put("isCommented", false);
				
				user.put("userId",vendorUser.getUserId());
				user.put("firstName",vendorUser.getFirstName());
				user.put("lastName",vendorUser.getLastName());
				if(userProfileImage != null)
					user.put("profileImageURL",userProfileImage.getFileUrl());
				else
					user.put("profileImageURL","");
				
				VendorOrganisation vendorOrganisation = vendorOrganisationRepository.findByVendorOrganisationId(externalMessageComment.getOrganisationId());
				user.put("organisationId",vendorOrganisation.getVendorOrganisationId());
				user.put("organisationName",vendorOrganisation.getCompanyName());
				
			}
			map.put("user",user);
			
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
			map.put("files",allFiles);
			allComments.add(map);
		}
		return allComments;
	}

	public void sendInternalMessageNotification(InternalMessage internalMessage, int notificationType) {
		// TODO Auto-generated method stub
		UserLevelNotification notification = new UserLevelNotification();
		String message = "New Internal Message";
		String subContent = " received message in thread";
		switch(notificationType) {
			case 12 :{
				message = "New Internal Message";
//				subContent = "New Message received - \""+internalMessage.getThreadSubject()+"\"";
				subContent = "\""+internalMessage.getThreadSubject()+"\"";
				break;
			}
			case 13	 :{
				message = "Internal Message Updated";
//				subContent = "Message has updated";
				subContent = "\""+internalMessage.getThreadSubject()+"\"";
				break;
			}
		}
		
		notification.setNotificationCategoryType(notificationType);
		notification.setNotificationCategoryId(internalMessage.getId());
		notification.setTitle(message);
		notification.setDescription(message+" - "+subContent);
		notification.setStatus(Constants.UserAccountStatus.ACTIVE.getValue());;
		
		notification.setFromUserId(internalMessage.getUserId());
		notification.setFromUserType(internalMessage.getUserType());
		notification.setFromOrganisationId(internalMessage.getOrganisationId());
		notification.setToUserId(internalMessage.getUserId());
		notification.setToUserType(internalMessage.getUserType());
		notification.setToOrganisationId(internalMessage.getOrganisationId());
		
		userLevelNotificationRepository.save(notification);
	}
	
	public void sendInternalMessageCommentNotification(InternalMessageComment internalMessageComment, int notificationType) {
		// TODO Auto-generated method stub
		UserLevelNotification notification = new UserLevelNotification();
		String message = "New Comment";
		String subContent = " received message comment in thread";
		switch(notificationType) {
			case 14	 :{
				message = "New Internal Message Comment";
//				subContent = "New comments on internal message thread - \""+ internalMessageComment.getComment() +"\"";
				subContent = "\""+ internalMessageComment.getComment() +"\"";
				break;
			}
		}
		
		notification.setNotificationCategoryType(notificationType);
		notification.setNotificationCategoryId(internalMessageComment.getId());
		
		notification.setTitle(message);
		notification.setDescription(message+" - "+subContent);
		notification.setStatus(Constants.UserAccountStatus.ACTIVE.getValue());
		
		notification.setFromUserId(internalMessageComment.getUserId());
		notification.setFromUserType(internalMessageComment.getUserType());
		int organisationId = internalMessageRepository.findOneById(internalMessageComment.getThreadId()).getOrganisationId();
		notification.setFromOrganisationId(organisationId);
		notification.setToUserId(internalMessageComment.getUserId());
		notification.setToUserType(internalMessageComment.getUserType());
		notification.setToOrganisationId(organisationId);
		
		userLevelNotificationRepository.save(notification);
	}

	public void sendExternalMessageNotification(ExternalMessage externalMessage, int notificationType) {
		// TODO Auto-generated method stub
		UserLevelNotification notification = new UserLevelNotification();
		String message = "New Message";
		String subContent = " received message in thread";
		switch(notificationType) {
			case 15 :{
				message = "New External Message";
//				subContent = "New Message received - \""+externalMessage.getThreadSubject()+"\"";
				subContent = "\""+externalMessage.getThreadSubject()+"\"";
				break;
			}
			case 16	 :{
				message = "External Message Updated";
//				subContent = "Message has updated";
				subContent =  "\""+externalMessage.getThreadSubject()+"\"";
				break;
			}
		}
		
		notification.setNotificationCategoryType(notificationType);
		notification.setNotificationCategoryId(externalMessage.getId());
		notification.setTitle(message);
		notification.setDescription(message+" - "+subContent);
		notification.setStatus(Constants.UserAccountStatus.ACTIVE.getValue());;
		
		notification.setFromUserId(externalMessage.getSourceUserId());
		notification.setFromUserType(externalMessage.getSourceUserType());
		notification.setFromOrganisationId(externalMessage.getSourceOrganisationId());
//		notification.setToUserId(Constants.ZERO);
//		notification.setToUserType(externalMessage.getTargetUserType());
//		notification.setToOrganisationId(externalMessage.getTargetOrganisationId());
		notification.setToOrganisationId(0);// to all target audience of Organisastions so Zero("0")
		
		userLevelNotificationRepository.save(notification);
	}

	public void sendExternalMessageCommentNotification(ExternalMessageComment externalMessageComment, int notificationType) {
		// TODO Auto-generated method stub
		UserLevelNotification notification = new UserLevelNotification();
		String message = "New Comment";
		String subContent = " received message comment in thread";
		switch(notificationType) {
			case 17	 :{
				message = "New External Message Comment";
//				subContent = "New comments on external message thread - \""+externalMessageComment.getComment()+"\"";
				subContent = "\""+externalMessageComment.getComment()+"\"";
				break;
			}
		}
		
		notification.setNotificationCategoryType(notificationType);
		notification.setNotificationCategoryId(externalMessageComment.getId());
		
		notification.setTitle(message);
		notification.setDescription(message+" - "+subContent);
		notification.setStatus(Constants.UserAccountStatus.ACTIVE.getValue());
		
		notification.setFromUserId(externalMessageComment.getUserId());
		notification.setFromUserType(externalMessageComment.getUserType());
		ExternalMessage externalMessage = externalMessageRepository.findOneById(externalMessageComment.getThreadId());
		int sourceOrganisationId = externalMessage.getSourceOrganisationId();
		String targetOrganisationId = externalMessage.getTargetOrganisationId();
		notification.setFromOrganisationId(sourceOrganisationId);
//		notification.setToUserId(externalMessageComment.getUserId());
//		notification.setToUserType(externalMessageComment.getUserType());
		notification.setToOrganisationId(0);// to all target audience of Organisastions so Zero("0")
		
		userLevelNotificationRepository.save(notification);

	}

	public List<Map<String,Object>> getInternalMessagesForSupportUser(Integer organisationId, Integer userType) {
		// TODO Auto-generated method stub

		List<InternalMessage> internalMessages = internalMessageRepository.findAllByOrganisationIdAndUserType(organisationId, userType);
		
		List<Map<String,Object>> allMessages = new ArrayList();
		
		for(InternalMessage internalMessage : internalMessages) {
			
			Map<String,Object> map = new HashMap<>();
			ObjectMapper oMapper = new ObjectMapper();
			map = oMapper.convertValue(internalMessage, Map.class);
			map.remove("userId");	
			map.remove("organisationId");	
			
			Map<String,Object> user = new HashMap<>();
			Map<String,Object> organisation = new HashMap<>();
			
			UserProfileImages userProfileImage = userProfileImagesRepository.findByUserIdAndUserType(internalMessage.getUserId(), internalMessage.getUserType());
			
			if(internalMessage.getUserType() == Constants.UserType.CLIENT.getValue()) {
				
				ClientUser clientUser = clientUserRepository.findByClientId(internalMessage.getUserId());
				user.put("userId",clientUser.getClientId());
				user.put("firstName",clientUser.getFirstName());
				user.put("lastName",clientUser.getLastName());
				if(userProfileImage != null)
					user.put("profileImageURL",userProfileImage.getFileUrl());
				else
					user.put("profileImageURL","");
				
				ClientOrganisation clientOrganisation = clientOrganisationRepository.findByClientOrganisationId(internalMessage.getOrganisationId());
				organisation.put("organisationId",clientOrganisation.getClientOrganisationId());
				organisation.put("managementCompany",clientOrganisation.getManagementCompany());
				organisation.put("corporateNumber",clientOrganisation.getCorporateNumber());
				organisation.put("organisationName",clientOrganisation.getOrganisationName());
				organisation.put("organisationLogo",getClientOrganisationLogo(clientOrganisation.getClientOrganisationId()));
				
			} else if(internalMessage.getUserType() == Constants.UserType.VENDOR.getValue()) {
				
				VendorUser vendorUser = vendorUserRepository.findByUserId(internalMessage.getUserId());
				user.put("userId",vendorUser.getUserId());
				user.put("firstName",vendorUser.getFirstName());
				user.put("lastName",vendorUser.getLastName());
				if(userProfileImage != null)
					user.put("profileImageURL",userProfileImage.getFileUrl());
				else
					user.put("profileImageURL","");
				
				VendorOrganisation vendorOrganisation = vendorOrganisationRepository.findByVendorOrganisationId(internalMessage.getOrganisationId());
				organisation.put("organisationId",vendorOrganisation.getVendorOrganisationId());
				organisation.put("legalName",vendorOrganisation.getLegalName());
				organisation.put("organisationName",vendorOrganisation.getCompanyName());
				organisation.put("organisationLogoName",vendorOrganisation.getLogoName());
				organisation.put("organisationLogo",getVendorOrganisationLogo(vendorOrganisation.getVendorOrganisationId()));
			}
			
			
			map.put("user",user);
			map.put("organisation",organisation);
			
			
			List<ThreadFiles> threadFiles = threadFilesRepository.findAllByThreadIdAndThreadType(internalMessage.getId(), ThreadType.INTERNAL.getValue());
			List<Map<String,Object>> allFiles = new ArrayList();
			for(ThreadFiles threadFile : threadFiles) {
				Map<String,Object> file = new HashMap<>();
				
				file.put("id", threadFile.getId());
				file.put("fileName", threadFile.getFileName());
				file.put("fileType", threadFile.getFileType());
				file.put("fileSize", Utils.formatFileSize(Long.parseLong(threadFile.getFileSize())));
				file.put("blobName", threadFile.getBlobName());
				file.put("containerName", threadFile.getContainerName());
//				file.put("fileUrl", commentFile.getFileUrl());
				file.put("createdAt", threadFile.getCreatedAt());
//				file.put("modifiedDate", commentFile.getModifiedDate());
				allFiles.add(file);
			}
			
			map.put("files",allFiles);
			map.put("comments",getInternalThreadComments(internalMessage.getId(), userType, Availability.INFO_NOT_AVAILABLE.getValue()));
			
			allMessages.add(map);
		}
		
		return allMessages;
	}
	
	public List<Map<String,Object>> getExternalMessagesForSupportUser(Integer organisationId, Integer userType) {
		// TODO Auto-generated method stub

		List<ExternalMessage> externalMessages = externalMessageRepository.findAllByOrganisationIdAndUserType(organisationId, userType);
		
		HashSet<ExternalMessage> resultSet = new HashSet(externalMessages);
		externalMessages.clear();
		externalMessages.addAll(resultSet);
		
		List<Map<String,Object>> allMessages = new ArrayList();
		
		for(ExternalMessage externalMessage : externalMessages) {
			
			Map<String,Object> map = new HashMap<>();
			ObjectMapper oMapper = new ObjectMapper();
			map = oMapper.convertValue(externalMessage, Map.class);
			map.remove("sourceOrganisationId");	
			map.remove("sourceUserId");	
			map.remove("targetOrganisationId");
			map.remove("targetUserType");
			
			Map<String,Object> user = new HashMap<>();
			Map<String,Object> organisation = new HashMap<>();
			
			UserProfileImages userProfileImage = userProfileImagesRepository.findByUserIdAndUserType(externalMessage.getSourceUserId(), externalMessage.getSourceUserType());
			
			if(externalMessage.getSourceUserType() == Constants.UserType.CLIENT.getValue()) {
				
				ClientUser clientUser = clientUserRepository.findByClientId(externalMessage.getSourceUserId());
				user.put("userId",clientUser.getClientId());
				user.put("firstName",clientUser.getFirstName());
				user.put("lastName",clientUser.getLastName());
				if(userProfileImage != null)
					user.put("profileImageURL",userProfileImage.getFileUrl());
				else
					user.put("profileImageURL","");
				
				ClientOrganisation clientOrganisation = clientOrganisationRepository.findByClientOrganisationId(externalMessage.getSourceOrganisationId());
				organisation.put("organisationId",clientOrganisation.getClientOrganisationId());
				organisation.put("managementCompany",clientOrganisation.getManagementCompany());
				organisation.put("corporateNumber",clientOrganisation.getCorporateNumber());
				organisation.put("organisationName",clientOrganisation.getOrganisationName());
				organisation.put("organisationLogo",getClientOrganisationLogo(clientOrganisation.getClientOrganisationId()));
				
			} else if(externalMessage.getSourceUserType() == Constants.UserType.VENDOR.getValue()) {
				
				VendorUser vendorUser = vendorUserRepository.findByUserId(externalMessage.getSourceUserId());
				user.put("userId",vendorUser.getUserId());
				user.put("firstName",vendorUser.getFirstName());
				user.put("lastName",vendorUser.getLastName());
				if(userProfileImage != null)
					user.put("profileImageURL",userProfileImage.getFileUrl());
				else
					user.put("profileImageURL","");
				
				VendorOrganisation vendorOrganisation = vendorOrganisationRepository.findByVendorOrganisationId(externalMessage.getSourceOrganisationId());
				organisation.put("organisationId",vendorOrganisation.getVendorOrganisationId());
				organisation.put("legalName",vendorOrganisation.getLegalName());
				organisation.put("organisationName",vendorOrganisation.getCompanyName());
				organisation.put("organisationLogoName",vendorOrganisation.getLogoName());
				organisation.put("organisationLogo",getVendorOrganisationLogo(vendorOrganisation.getVendorOrganisationId()));
			}
			
			
			map.put("fromUser",user);
			map.put("fromOrganisation",organisation);
			
			map.put("targetOrganisations",getExternalMessageTargetOrganisations(externalMessage.getId()));
			
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
			map.put("comments",getExternalThreadComments(externalMessage.getId(), userType, Availability.INFO_NOT_AVAILABLE.getValue()));
			
			allMessages.add(map);
		}
		
		return allMessages;
	}
	
	
}

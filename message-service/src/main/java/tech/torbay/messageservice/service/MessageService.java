package tech.torbay.messageservice.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import tech.torbay.messageservice.Utils.Utils;
import tech.torbay.messageservice.constants.Constants;
import tech.torbay.messageservice.constants.Constants.ThreadType;
import tech.torbay.messageservice.entity.ClientOrganisation;
import tech.torbay.messageservice.entity.ClientOrganisationProfileImages;
import tech.torbay.messageservice.entity.ClientUser;
import tech.torbay.messageservice.entity.CommentFiles;
import tech.torbay.messageservice.entity.ExternalMessage;
import tech.torbay.messageservice.entity.ExternalMessageComment;
import tech.torbay.messageservice.entity.InternalMessage;
import tech.torbay.messageservice.entity.InternalMessageComment;
import tech.torbay.messageservice.entity.ThreadFiles;
import tech.torbay.messageservice.entity.UserLevelNotification;
import tech.torbay.messageservice.entity.VendorOrganisation;
import tech.torbay.messageservice.entity.VendorOrganisationProfileImages;
import tech.torbay.messageservice.entity.VendorUser;
import tech.torbay.messageservice.repository.ClientOrganisationProfileImagesRepository;
import tech.torbay.messageservice.repository.ClientOrganisationRepository;
import tech.torbay.messageservice.repository.ClientUserRepository;
import tech.torbay.messageservice.repository.CommentFilesRepository;
import tech.torbay.messageservice.repository.ExternalMessageCommentRepository;
import tech.torbay.messageservice.repository.ExternalMessageRepository;
import tech.torbay.messageservice.repository.InternalMessageCommentRepository;
import tech.torbay.messageservice.repository.InternalMessageRepository;
import tech.torbay.messageservice.repository.ThreadFilesRepository;
import tech.torbay.messageservice.repository.UserLevelNotificationRepository;
import tech.torbay.messageservice.repository.UserProfileImagesRepository;
import tech.torbay.messageservice.repository.VendorOrganisationProfileImagesRepository;
import tech.torbay.messageservice.repository.VendorOrganisationRepository;
import tech.torbay.messageservice.repository.VendorUserRepository;
import tech.torbay.messageservice.entity.UserProfileImages;

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

	public List<Map<String,Object>> getInternalMessages(Integer organisationId, Integer userType) {
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
				user.put("profileImageURL",userProfileImage.getFileUrl());
				
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
				user.put("profileImageURL",userProfileImage.getFileUrl());
				
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
	
	private List<Map<String,Object>> getInternalThreadComments(Integer threadId) {
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
				user.put("userId",clientUser.getClientId());
				user.put("firstName",clientUser.getFirstName());
				user.put("lastName",clientUser.getLastName());
				user.put("profileImageURL",userProfileImage.getFileUrl());
				
			} else if(internalMessageComment.getUserType() == Constants.UserType.VENDOR.getValue()) {
				
				VendorUser vendorUser = vendorUserRepository.findByUserId(internalMessageComment.getUserId());
				user.put("userId",vendorUser.getUserId());
				user.put("firstName",vendorUser.getFirstName());
				user.put("lastName",vendorUser.getLastName());
				user.put("profileImageURL",userProfileImage.getFileUrl());
				
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

	public void sendInternalMessageNotification(InternalMessage internalMessage, int notificationType) {
		// TODO Auto-generated method stub
		UserLevelNotification notification = new UserLevelNotification();
		String message = "New Internal Message";
		String subContent = " received message in thread";
		switch(notificationType) {
			case 12 :{
				message = "New Internal Message";
				subContent = "New Message received";
				break;
			}
			case 13	 :{
				message = "Internal Message Updated";
				subContent = "Message has updated";
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
				message = "New Comment";
				subContent = "New comments on internal message thread";
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
				subContent = "New Message received";
				break;
			}
			case 16	 :{
				message = "External Message Updated";
				subContent = "Message has updated";
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
		notification.setToUserType(externalMessage.getTargetUserType());
		notification.setToOrganisationId(externalMessage.getTargetOrganisationId());
		
		userLevelNotificationRepository.save(notification);
	}

	public void sendExternalMessageCommentNotification(ExternalMessageComment externalMessageComment, int notificationType) {
		// TODO Auto-generated method stub
		UserLevelNotification notification = new UserLevelNotification();
		String message = "New Comment";
		String subContent = " received message comment in thread";
		switch(notificationType) {
			case 17	 :{
				message = "New Comment";
				subContent = "New comments on external message thread";
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
		int targetOrganisationId = externalMessage.getTargetOrganisationId();
		notification.setFromOrganisationId(sourceOrganisationId);
//		notification.setToUserId(externalMessageComment.getUserId());
//		notification.setToUserType(externalMessageComment.getUserType());
		notification.setToOrganisationId(targetOrganisationId);
		
		userLevelNotificationRepository.save(notification);

	}

}

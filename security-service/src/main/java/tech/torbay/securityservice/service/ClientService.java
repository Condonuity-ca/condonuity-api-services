package tech.torbay.securityservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;

import tech.torbay.securityservice.constants.Constants;
import tech.torbay.securityservice.constants.Constants.DeleteStatus;
import tech.torbay.securityservice.constants.Constants.OrganisationAccountStatus;
import tech.torbay.securityservice.constants.Constants.UserType;
import tech.torbay.securityservice.entity.ClientAssociation;
import tech.torbay.securityservice.entity.ClientOrganisation;
import tech.torbay.securityservice.entity.ClientUser;
import tech.torbay.securityservice.entity.RegistrationLogs;
import tech.torbay.securityservice.entity.User;
import tech.torbay.securityservice.repository.ClientAssociationRepository;
import tech.torbay.securityservice.repository.ClientOrganisationRepository;
import tech.torbay.securityservice.repository.ClientUserRepository;
import tech.torbay.securityservice.repository.RegistrationLogsRepository;
import tech.torbay.securityservice.repository.UserRepository;

@Component
public class ClientService {
	
	@Autowired
	ClientUserRepository clientUserRepository;
	@Autowired
	ClientOrganisationRepository clientOrganisationRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	ClientAssociationRepository clientAssociationRepository;
	@Autowired
	RegistrationLogsRepository registrationLogsRepository;

	public List<ClientUser> getAllClientUsers() {
//		// TODO Auto-generated method stub
		return Lists.newArrayList(clientUserRepository.findAll());
	}

	public ClientUser findByEmail(String email) {
		// TODO Auto-generated method stub
		return clientUserRepository.findByEmail(email);
	}

	public ClientUser addClientOrgAccountAssociation(Integer organisationId, Integer clientUserType, Integer userRole, ClientUser clientUser, Integer userAccountStatus, Integer userVerificationStatus, int deleteStatus) {
		// TODO Auto-generated method stub
		
		//steps
		//1 : Register if account does not exist
		//2 : 
		
		//check if client - organisation association exist or not
		//exist - no new association required
		ClientAssociation clientAssociation = clientAssociationRepository.findByClientIdAndClientOrganisationId(clientUser.getClientId(),organisationId);
		if( clientAssociation != null) {
			//1.already has association, if you want resend invitation send here
			//2.reset association
			//3.deleted user also can re-invite here
			
		} else {
		//else create new association
		
			clientAssociation = new ClientAssociation();
		}
		clientAssociation.setClientOrganisationId(organisationId);
		clientAssociation.setClientId(clientUser.getClientId());
		clientAssociation.setClientUserType(clientUserType);
		clientAssociation.setUserRole(userRole);
		clientAssociation.setAccountVerificationStatus(userVerificationStatus/* Constants.VerificationStatus.NOT_VERIFIED.getValue() */);
		clientAssociation.setUserAccountStatus(userAccountStatus/* Constants.UserAccountStatus.ACTIVE.getValue() */);
		clientAssociation.setDeleteStatus(deleteStatus/* Constants.UserAccountStatus.ACTIVE.getValue() */);
		
		//2.1.3
		if(clientAssociationRepository.save(clientAssociation) != null) {
			
			
			return clientUser;
		}
	
		
		return null;
	}

	public ClientUser addClient(Integer organisationId, Integer clientUserType, Integer userRole, ClientUser clientUser) {
		// TODO Auto-generated method stub
		
		//Steps
		//1. check client email Already exist or not
		//2. if exist 
		//	2.1 - insert client - organization associate record and account activation status as hold
		//		- 2.1.1 - Insert Client if client record not found
		//		- 2.1.2 - Insert User Account for Login
		//		- 2.1.3 - Insert Client - Organization Association
		//	2.2 - send invite accept email - (need to accept terms and condition)
		//	2.3 - POST /api/client/invite/accept - exist - change status to active registration status, verification status when click the link
		//	2.4 - No need to reset password 
		//3. if not
		//	3.1 - insert client - organization associate record and account activation status as hold
		//		- 3.1.1 - Insert Client
		//		- 3.1.2 - Insert User Account fot Login
		//		- 3.1.3 - Insert Client - Organization Association
		//	3.2 - send new invite to register/reset password email - (need to accept terms and condition)
		//	3.3	- POST /api/client/invite/accept - new - change status to active registration status, verification status when click the link
		//	3.4 - POST /api/user/resetPassword - New User need to reset password
		
		if(!clientUserRepository.existsByEmail(clientUser.getEmail())) {
			//2.1.1
			if(clientUserRepository.save(clientUser) != null){
				
				clientUser = clientUserRepository.findByEmail(clientUser.getEmail());
				
				if(clientUser != null) {
					
					User user = new User();
					user.setUserId(clientUser.getClientId());
					user.setUsername(clientUser.getEmail());
					user.setUserType(Constants.UserType.CLIENT.getValue());
					
					//2.1.2
					if(userRepository.save(user) != null) {
						
						if(addClientOrgAccountAssociation(organisationId, clientUserType, userRole, clientUser, Constants.UserAccountStatus.INVITED.getValue(), Constants.VerificationStatus.NOT_VERIFIED.getValue(), Constants.DeleteStatus.ACTIVE.getValue()) != null) {
							return clientUser;
						} else {
//							clientUserRepository.deleteById(clientUser.getClientId());
//							userRepository.deleteByUserIdAndUserType(clientUser.getClientId(),Constants.UserType.CLIENT.getValue());
						}
					} else {
//						clientUserRepository.deleteById(clientUser.getClientId());
						return null;
					}
					
					// Send Email
					// with Data UserId, UserType, User Already Exist, No need to reset password
				} else {
					
				}
			} else {
				return null;
			}
		} else {
			return addClientOrgAccountAssociation(organisationId, clientUserType, userRole, clientUser, Constants.UserAccountStatus.INACTIVE.getValue(), Constants.VerificationStatus.NOT_VERIFIED.getValue(), Constants.DeleteStatus.ACTIVE.getValue());
		}
		
		return clientUser;
	}

	public ClientOrganisation addClientOrganisation(Integer clientId, ClientOrganisation clientOrganisation, boolean isExisting) {
		// TODO Auto-generated method stub
		
		

		try {
			clientOrganisation.setActiveStatus(OrganisationAccountStatus.REGISTERED.getValue());
//			clientOrganisation.setActiveStatus(OrganisationAccountStatus.ACTIVE.getValue());//support user will activate the organisation
			clientOrganisation.setDeleteStatus(DeleteStatus.ACTIVE.getValue());
			ClientOrganisation clientOrganisationObj = clientOrganisationRepository.save(clientOrganisation);
			
			if(clientOrganisationObj != null) {
				ClientUser clientUser = clientUserRepository.findByClientId(clientId);
				
				clientUser.setPrimaryOrgId(clientOrganisationObj.getClientOrganisationId());
				clientUserRepository.save(clientUser);
				int deleteStatus = 0;
				if(isExisting) {
					deleteStatus = Constants.DeleteStatus.ACTIVE.getValue();
				} else {
					deleteStatus = Constants.DeleteStatus.INACTIVE.getValue();
				}
				// Update client User - account activation status verified with account association
				addClientOrgAccountAssociation(clientOrganisationObj.getClientOrganisationId(), 
						Constants.ClientUserType.BOARD_MEMBER.getValue(),
						Constants.UserRole.ADMIN.getValue(), clientUser, 
						Constants.UserAccountStatus.ACTIVE.getValue(), 
						Constants.VerificationStatus.VERIFIED.getValue(), 
						deleteStatus);// login restrict until organisation verification done
				// for client multiple organisation registration , we implement this logs
				RegistrationLogs registrationLogs = new RegistrationLogs();
				registrationLogs.setUserId(clientId);
				registrationLogs.setUserType(UserType.CLIENT.getValue());
				registrationLogs.setOrganisationId(clientOrganisationObj.getClientOrganisationId());
				registrationLogsRepository.save(registrationLogs);
				return clientOrganisationObj;
				
			} else {
				return null;
			}
		
		
		} catch(Exception exp) {
			exp.printStackTrace();
			return null;
		}
		
	}

	public ClientUser findById(Integer userId) {
		// TODO Auto-generated method stub
		return clientUserRepository.findByClientId(userId);
	}

	public ClientUser registerClient(ClientUser clientUser) {
		// TODO Auto-generated method stub
		//New client User Registration
		try {
			if(clientUserRepository.save(clientUser) != null){
				
				clientUser = clientUserRepository.findByEmail(clientUser.getEmail());
				
				
				System.out.println(clientUser.toString());
				
				User user = new User();
				user.setUserId(clientUser.getClientId());
				user.setUsername(clientUser.getEmail());
				user.setUserType(Constants.UserType.CLIENT.getValue());
				
				System.out.println(user.toString());
				//2.1.2
				userRepository.save(user);
				
				return clientUser;
			} else {
				return null;
			}
		} catch(Exception exp) {
			exp.printStackTrace();
		}
		
		return null;
	}

	public List<ClientAssociation> getAllClientUsersInOrganisation(Integer clientOrganisationId) {
		// TODO Auto-generated method stub
		
		List<ClientAssociation> clientAssociations = clientAssociationRepository.findAllByClientOrganisationId(clientOrganisationId);
		
		// check Active clients count
		
		return clientAssociations;
	}

	public ClientAssociation updateClientUserVerificationStatus(Integer clientOrgId, Integer clientId) {
		// TODO Auto-generated method stub
		
		ClientAssociation clientAssociation = clientAssociationRepository.findByClientIdAndClientOrganisationId(clientId, clientOrgId);
		clientAssociation.setAccountVerificationStatus(Constants.VerificationStatus.VERIFIED.getValue());
		clientAssociation.setUserAccountStatus(Constants.UserAccountStatus.ACTIVE.getValue());
		clientAssociation.setDeleteStatus(Constants.DeleteStatus.ACTIVE.getValue());
		
		return clientAssociationRepository.save(clientAssociation);
	}

	public boolean checkClientOrgAssociationFound(Integer clientId, Integer organisationId) {
		// TODO Auto-generated method stub
		ClientAssociation clientAssociation = clientAssociationRepository.findByClientIdAndClientOrganisationId(clientId, organisationId);
		System.out.println(clientAssociation);
		if(clientAssociation == null) {
			return false;
		} else if(clientAssociation.getAccountVerificationStatus() == Constants.VerificationStatus.NOT_VERIFIED.getValue()) {
			return true;
		} else if(clientAssociation.getUserAccountStatus() == Constants.UserAccountStatus.INACTIVE.getValue()) {
			return true;
		} else if(clientAssociation != null){
			return true;
		}
		
		return false;
	}
	
	public Integer getAllOrganisationsForClientUser(Integer clientId) {
		// TODO Auto-generated method stub
		
		List<ClientAssociation> clientAssociations = clientAssociationRepository.findAllByClientId(clientId);
		
		// check Active clients count
		
		return clientAssociations.size();
	}

	public ClientOrganisation getClientOrganisationById(Integer organisationId) {
		// TODO Auto-generated method stub
		return clientOrganisationRepository.findByClientOrganisationId(organisationId);
	}

	public boolean checkIsClientActiveAtlestOneAccount(Integer clientId) {
		// TODO Auto-generated method stub
		
		List<ClientAssociation> clientAssociations = clientAssociationRepository.findAllByClientId(clientId);
		
		for (ClientAssociation clientAssociation : clientAssociations) {
			if(clientAssociation.getUserAccountStatus() == Constants.UserAccountStatus.ACTIVE.getValue() ) {
				return true;
			}
		}
		return false;
	}
	
	public boolean checkIsClientAccountActive(Integer clientId) {
		// TODO Auto-generated method stub
		
		List<ClientAssociation> clientAssociations = clientAssociationRepository.findAllByClientId(clientId);
		
		for (ClientAssociation clientAssociation : clientAssociations) {
			if(clientAssociation.getDeleteStatus() == DeleteStatus.INACTIVE.getValue()) {
				return false;
			}
		}
		return true;
	}

	public boolean checkOrganisationNameAvailable(String organisationName) {
		// TODO Auto-generated method stub
		List<ClientOrganisation> clientOrgs = clientOrganisationRepository.findByOrganisationName(organisationName);
		if( clientOrgs != null && clientOrgs.size() > 0) {
			return true;
		}
		
		return false;
	}

	public List<RegistrationLogs> checkRegistrationLog(Integer clientUserId) {
		// TODO Auto-generated method stub
		return registrationLogsRepository.findByUserIdAndUserType(clientUserId,UserType.CLIENT.getValue());
	}

	public void setPrimaryOrganisationId(ClientUser clientUser, Integer organisationId) {
		// TODO Auto-generated method stub
		
		ClientUser clientUserObj = clientUserRepository.findByClientId(clientUser.getClientId());
		clientUserObj.setPrimaryOrgId(organisationId);
		clientUserRepository.save(clientUserObj);
	}
}


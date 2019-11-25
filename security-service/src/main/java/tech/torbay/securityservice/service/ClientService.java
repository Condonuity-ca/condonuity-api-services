package tech.torbay.securityservice.service;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Id;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties.Admin;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;

import tech.torbay.securityservice.constants.Constants;
import tech.torbay.securityservice.entity.ClientAssociation;
import tech.torbay.securityservice.entity.ClientOrganisation;
import tech.torbay.securityservice.entity.ClientUser;
import tech.torbay.securityservice.entity.User;
import tech.torbay.securityservice.repository.ClientAssociationRepository;
import tech.torbay.securityservice.repository.ClientOrganisationRepository;
import tech.torbay.securityservice.repository.ClientUserRepository;
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

	public List<ClientUser> getAllClientUsers() {
//		// TODO Auto-generated method stub
		return Lists.newArrayList(clientUserRepository.findAll());
	}

	public ClientUser findByEmail(String email) {
		// TODO Auto-generated method stub
		return clientUserRepository.findByEmail(email);
	}

	public ClientUser addClientOrgAccountAssociation(Integer organisationId, Integer clientUserType, Integer userRole, ClientUser clientUser) {
		// TODO Auto-generated method stub
		
		//steps
		//1 : Register if account does not exist
		//2 : 
		
		ClientAssociation clientAssociation = new ClientAssociation();
		clientAssociation.setClientOrganisationId(organisationId);
		clientAssociation.setClientId(clientUser.getClientId());
		clientAssociation.setClientUserType(clientUserType);
		clientAssociation.setUserRole(userRole);
		clientAssociation.setAccountVerificationStatus(Constants.VerificationStatus.NOT_VERIFIED.getValue());
		clientAssociation.setUserAccountStatus(Constants.UserAccountStatus.ACTIVE.getValue());
		
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
				
				if(clientUserRepository.findByEmail(clientUser.getEmail()) != null) {
					
					clientUser = clientUserRepository.findByEmail(clientUser.getEmail());
					
					System.out.println(clientUser.toString());
					
					User user = new User();
					user.setUserId(clientUser.getClientId());
					user.setUsername(clientUser.getEmail());
					user.setUserType(Constants.UserType.CLIENT.getValue());
					
					System.out.println(user.toString());
					//2.1.2
					if(userRepository.save(user) != null) {
						
						if(addClientOrgAccountAssociation(organisationId, clientUserType, userRole, clientUser) != null) {
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
			return addClientOrgAccountAssociation(organisationId, clientUserType, userRole, clientUser);
		}
		
		return clientUser;
	}

	public ClientOrganisation addClientOrganisation(Integer clientId, ClientOrganisation clientOrganisation) {
		// TODO Auto-generated method stub
		
		

		try {
			clientOrganisation = clientOrganisationRepository.save(clientOrganisation);
			
			if(clientOrganisation != null) {
				
				// Update client User - account activation status verified with account association
				addClientOrgAccountAssociation(clientOrganisation.getClientOrganisationId(), 
						Constants.ClientUserType.BOARD_MEMBER.getValue(),
						Constants.UserRole.ADMIN.getValue(), clientUserRepository.findByClientId(clientId));
				
				return clientOrganisation;
				
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
}


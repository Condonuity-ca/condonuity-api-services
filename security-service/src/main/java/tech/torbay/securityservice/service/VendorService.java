package tech.torbay.securityservice.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;

import tech.torbay.securityservice.constants.Constants;
import tech.torbay.securityservice.constants.Constants.DeleteStatus;
import tech.torbay.securityservice.constants.Constants.OrganisationAccountStatus;
import tech.torbay.securityservice.constants.Constants.UserType;
import tech.torbay.securityservice.entity.AvailableVendorProfiles;
import tech.torbay.securityservice.entity.AvailableVendorProfilesRepository;
import tech.torbay.securityservice.entity.RegistrationLogs;
import tech.torbay.securityservice.entity.ServiceCities;
import tech.torbay.securityservice.entity.User;
import tech.torbay.securityservice.entity.UserInviteLogs;
import tech.torbay.securityservice.entity.VendorBrands;
import tech.torbay.securityservice.entity.VendorInsurance;
import tech.torbay.securityservice.entity.VendorInsuranceRepository;
import tech.torbay.securityservice.entity.VendorLicenses;
import tech.torbay.securityservice.entity.VendorMemberships;
import tech.torbay.securityservice.entity.VendorOrganisation;
import tech.torbay.securityservice.entity.VendorProducts;
import tech.torbay.securityservice.entity.VendorServices;
import tech.torbay.securityservice.entity.VendorServicesCities;
import tech.torbay.securityservice.entity.VendorUser;
import tech.torbay.securityservice.repository.RegistrationLogsRepository;
import tech.torbay.securityservice.repository.ServiceCitiesRepository;
import tech.torbay.securityservice.repository.UserInviteLogsRepository;
import tech.torbay.securityservice.repository.UserRepository;
import tech.torbay.securityservice.repository.VendorBrandsRepository;
import tech.torbay.securityservice.repository.VendorLicensesRepository;
import tech.torbay.securityservice.repository.VendorMembershipsRepository;
import tech.torbay.securityservice.repository.VendorOrganisationRepository;
import tech.torbay.securityservice.repository.VendorProductsRepository;
import tech.torbay.securityservice.repository.VendorServicesCitiesRepository;
import tech.torbay.securityservice.repository.VendorServicesRepository;
import tech.torbay.securityservice.repository.VendorUserRepository;

@Component
public class VendorService {
	
	@Autowired
	VendorUserRepository vendorUserRepository;
	@Autowired
	VendorOrganisationRepository vendorOrganisationRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	VendorBrandsRepository vendorBrandsRepository;
	@Autowired
	VendorServicesRepository vendorServicesRepository;
	@Autowired
	VendorServicesCitiesRepository vendorServicesCitiesRepository;
	@Autowired
	VendorProductsRepository vendorProductsRepository;
	@Autowired
	VendorLicensesRepository vendorLicensesRepository;
	@Autowired
	VendorMembershipsRepository vendorMembershipsRepository;
	@Autowired
	VendorInsuranceRepository vendorInsuranceRepository;
	@Autowired
	AvailableVendorProfilesRepository availableVendorProfilesRepository;
	@Autowired
	RegistrationLogsRepository registrationLogsRepository;
	@Autowired
	UserInviteLogsRepository userInviteLogsRepository;
	@Autowired
	ServiceCitiesRepository servicesCitiesRepository;

	public List<VendorUser> findAll() {
//		// TODO Auto-generated method stub
		return Lists.newArrayList(vendorUserRepository.findAll());
	}
	
	public VendorOrganisation findByVendorOrgId(Integer id) {
		// TODO Auto-generated method stub
		return vendorOrganisationRepository.findByVendorOrganisationId(id);
	}

	public VendorUser findByEmail(String email) {
		// TODO Auto-generated method stub
		VendorUser vendor = vendorUserRepository.findByEmail(email);
		return vendor;
	}

	public VendorUser addVendorUser(VendorUser vendorUser) {
		// TODO Auto-generated method stub
		try {
			vendorUser.setUserRole(Constants.UserRole.ADMIN.getValue());
			vendorUser.setDeleteStatus(Constants.DeleteStatus.ACTIVE.getValue());
		
		if(vendorUserRepository.save(vendorUser) != null){
			
			vendorUser = vendorUserRepository.findByEmail(vendorUser.getEmail());
			
			System.out.println(vendorUser.toString());
			
			User user = new User();
			user.setUserId(vendorUser.getUserId());
			user.setUsername(vendorUser.getEmail());
			user.setUserType(Constants.UserType.VENDOR.getValue());
			
			System.out.println(user.toString());
			
			userRepository.save(user);
			
			return vendorUser;
		} else {
			return null;
		}
		} catch (Exception exp) {
			exp.printStackTrace();
			return null;
		}
	}

	public VendorOrganisation addVendorOrgnisation(Integer vendorUserId, Map<String, Object> vendorOrganisationData) {
		// TODO Auto-generated method stub
		
		// Add new vendor
		// update to vendor admin user after org created
		try {
			
			
			final ObjectMapper mapper = new ObjectMapper(); // jackson's objectmapper
			VendorOrganisation vendorOrganisation = mapper.convertValue(vendorOrganisationData.get("organisation"), VendorOrganisation.class);
			vendorOrganisation.setActiveStatus(OrganisationAccountStatus.REGISTERED.getValue());
//			vendorOrganisation.setActiveStatus(OrganisationAccountStatus.ACTIVE.getValue());//support user will activate the organisation
			vendorOrganisation.setDeleteStatus(DeleteStatus.ACTIVE.getValue());
			VendorInsurance vendorInsurance = null;
			try {
			vendorInsurance = mapper.convertValue(vendorOrganisationData.get("insurance"), VendorInsurance.class);
			} catch(Exception exp) {
				exp.printStackTrace();
			}
			
			String serviceCities = String.valueOf(vendorOrganisationData.get("serviceCities")); // ids - 1,2,3
			String services = String.valueOf(vendorOrganisationData.get("services")); // string - abc,abc,acbc
			String licenses = String.valueOf(vendorOrganisationData.get("licenses")); // string - abc,abc,acbc
			String products = String.valueOf(vendorOrganisationData.get("products")); // string - abc,abc,acbc
			String brands = String.valueOf(vendorOrganisationData.get("brands")); // string - abc,abc,acbc
			String memberships = String.valueOf(vendorOrganisationData.get("memberships")); // string - abc,abc,acbc
			
			vendorOrganisation = vendorOrganisationRepository.save(vendorOrganisation);
			
			
			if(vendorOrganisation != null) {
				
				// update vendor user - organisation Id update
				VendorUser vendorUser = vendorUserRepository.findByUserId(vendorUserId);
				vendorUser.setVendorOrganisationId(vendorOrganisation.getVendorOrganisationId());
				vendorUser.setAccountVerificationStatus(Constants.VerificationStatus.VERIFIED.getValue());
				vendorUser.setAccountStatus(Constants.UserAccountStatus.ACTIVE.getValue());
				vendorUser.setDeleteStatus(Constants.DeleteStatus.ACTIVE.getValue());// login restrict until organisation verification done
				
				vendorUserRepository.save(vendorUser);
				
				// 0 add insurance
				// 1 add service cities
				// 2 add services
				// 3 add licenses
				// 4 add products
				// 5 add brands
				// 6 add memberships
				
				if(vendorInsurance != null) {
					vendorInsurance.setVendorOrganisationId(vendorOrganisation.getVendorOrganisationId());
					vendorInsuranceRepository.save(vendorInsurance);
				}
				
				//1
				if(serviceCities.trim().length() > 0) {
					List<String> cities = Arrays.asList(serviceCities.trim().split(","));
				    for(String city: cities) {
				    	vendorServicesCitiesRepository.save(new VendorServicesCities(vendorOrganisation.getVendorOrganisationId(),Integer.parseInt(city)));
				    }
				}
			    
				if(services.trim().length() > 0) {
					List<String> vservices = Arrays.asList(services.trim().split(","));
				    for(String service: vservices) {
				    	vendorServicesRepository.save(new VendorServices(vendorOrganisation.getVendorOrganisationId(),service));
				    }
				}
			    
				if(products.trim().length() > 0) {
					List<String> vproducts = Arrays.asList(products.trim().split(","));
				    for(String product: vproducts) {
				    	vendorProductsRepository.save(new VendorProducts(vendorOrganisation.getVendorOrganisationId(),product));
				    }
				}
			    
				if(brands.trim().length() > 0) {
					List<String> vbrands = Arrays.asList(brands.trim().split(","));
				    for(String brand: vbrands) {
				    	vendorBrandsRepository.save(new VendorBrands(vendorOrganisation.getVendorOrganisationId(),brand));
				    }
				}
			    
				if(licenses.trim().length() > 0) {
					List<String> vlicenses = Arrays.asList(licenses.trim().split(","));
				    for(String license: vlicenses) {
				    	vendorLicensesRepository.save(new VendorLicenses(vendorOrganisation.getVendorOrganisationId(),license,"",""));
				    }
				}
			    
				if(memberships.trim().length() > 0) {
					List<String> vmemberships = Arrays.asList(memberships.trim().split(","));
				    for(String membership: vmemberships) {
				    	vendorMembershipsRepository.save(new VendorMemberships(vendorOrganisation.getVendorOrganisationId(),membership,""));
				    }
				}
			    
			    try {
			    	String vendorProfileId = String.valueOf(vendorOrganisationData.get("vendorProfileId")); // ids - 1,2,3
			    	
			    	if(vendorProfileId != null && !vendorProfileId.equals("null") && !vendorProfileId.equals("0") && vendorProfileId.trim().length() > 0) {
				    	AvailableVendorProfiles availableVendorProfile = availableVendorProfilesRepository.findByVendorProfileId(Integer.parseInt(vendorProfileId));
				    	availableVendorProfile.setAllocatedVendorOrgId(vendorOrganisation.getVendorOrganisationId());
				    	availableVendorProfilesRepository.save(availableVendorProfile);
			    	}
			    	
			    	// for client multiple organisation registration , we implement this logs
					RegistrationLogs registrationLogs = new RegistrationLogs();
					registrationLogs.setUserId(vendorUserId);
					registrationLogs.setUserType(UserType.VENDOR.getValue());
					registrationLogs.setOrganisationId(vendorOrganisation.getVendorOrganisationId());
					registrationLogsRepository.save(registrationLogs);
			    } catch(Exception exp) {
			    	exp.printStackTrace();
			    }
				return vendorOrganisation;
			}
		} catch(Exception exp) {
			exp.printStackTrace();
			return null;
		}
		
		return null;
	}

	public VendorUser findByVendorUserId(Integer userId) {
		// TODO Auto-generated method stub
		return vendorUserRepository.findByUserId(userId);
	}

	public VendorUser saveVendorUser(VendorUser vendorUser, String hash) {
		// TODO Auto-generated method stub
//		check
		List<UserInviteLogs> userInviteLogs = userInviteLogsRepository.findByUserIdAndUserTypeAndOrganisationIdAndHash(vendorUser.getUserId(),UserType.VENDOR.getValue(), vendorUser.getVendorOrganisationId(), hash);
		
		if(userInviteLogs != null && userInviteLogs.size() > 0) {
			return null;
		} else {
			//add log
			vendorUser.setAccountVerificationStatus(Constants.VerificationStatus.VERIFIED.getValue());
			vendorUser.setAccountStatus(Constants.UserAccountStatus.ACTIVE.getValue());
			vendorUser.setDeleteStatus(Constants.DeleteStatus.ACTIVE.getValue());
			
			UserInviteLogs userInviteLog = new UserInviteLogs();
			userInviteLog.setUserId(vendorUser.getUserId());
			userInviteLog.setUserType(UserType.VENDOR.getValue());
			userInviteLog.setOrganisationId(vendorUser.getVendorOrganisationId());
			userInviteLog.setHash(hash);
			userInviteLogsRepository.save(userInviteLog);
			
			return vendorUserRepository.save(vendorUser);
		}
	}

	public VendorUser createVendorUser(VendorUser vendorUser) {
		// TODO Auto-generated method stub
		try {
			
			if(vendorUserRepository.save(vendorUser) != null){
				
				vendorUser = vendorUserRepository.findByEmail(vendorUser.getEmail());
				
				System.out.println(vendorUser.toString());
				
				User user = new User();
				user.setUserId(vendorUser.getUserId());
				user.setUsername(vendorUser.getEmail());
				user.setUserType(Constants.UserType.VENDOR.getValue());
				
				System.out.println(user.toString());
				
				userRepository.save(user);
				
				return vendorUser;
			} else {
				return null;
			}
			} catch (Exception exp) {
				exp.printStackTrace();
				return null;
			}
	}
	
	public VendorUser updateVendorUser(VendorUser vendorUser) {
		// TODO Auto-generated method stub
		try {
			
			if(vendorUserRepository.save(vendorUser) != null){
				
				vendorUser = vendorUserRepository.findByEmail(vendorUser.getEmail());
				
				System.out.println(vendorUser.toString());
				
				return vendorUser;
			} else {
				return null;
			}
			} catch (Exception exp) {
				exp.printStackTrace();
				return null;
			}
	}


	public List<VendorUser> getAllVendorUsersInOrganisation(Integer vendorOrganisationId) {
		// TODO Auto-generated method stub
		return vendorUserRepository.findAllByVendorOrganisationId(vendorOrganisationId);
	}

	public VendorOrganisation getVendorOrganisationById(Integer id) {
		// TODO Auto-generated method stub
		return vendorOrganisationRepository.findByVendorOrganisationId(id);
	}

	public boolean checkOrganisationNameAvailable(Map<String, Object> vendorOrganisationData) {
		// TODO Auto-generated method stub
		final ObjectMapper mapper = new ObjectMapper(); // jackson's objectmapper
		VendorOrganisation vendorOrganisation = mapper.convertValue(vendorOrganisationData.get("organisation"), VendorOrganisation.class);
		
		List<VendorOrganisation> org = vendorOrganisationRepository.findByCompanyName(vendorOrganisation.getCompanyName());
		
		if( org != null && org.size() > 0) {
			return true;
		}
		
		return false;
	}
	
	public boolean checkLegalNameAvailable(Map<String, Object> vendorOrganisationData) {
		// TODO Auto-generated method stub
		final ObjectMapper mapper = new ObjectMapper(); // jackson's objectmapper
		VendorOrganisation vendorOrganisation = mapper.convertValue(vendorOrganisationData.get("organisation"), VendorOrganisation.class);
		
		List<VendorOrganisation> org = vendorOrganisationRepository.findByLegalName(vendorOrganisation.getLegalName());
		
		if( org != null && org.size() > 0) {
			return true;
		}
		
		return false;
	}
	
	public boolean checkOrganisationNameIsEmpty(Map<String, Object> vendorOrganisationData) {
		// TODO Auto-generated method stub
		final ObjectMapper mapper = new ObjectMapper(); // jackson's objectmapper
		VendorOrganisation vendorOrganisation = mapper.convertValue(vendorOrganisationData.get("organisation"), VendorOrganisation.class);
//		System.out.println("vendorOrganisation.getCompanyName() : "+ vendorOrganisation.getCompanyName());
		if(vendorOrganisation.getCompanyName().trim().length() > 0) {
			return false;
		} else {
			return true;
		}
	}
	
	public boolean checkLegalNameIsEmpty(Map<String, Object> vendorOrganisationData) {
		// TODO Auto-generated method stub
		final ObjectMapper mapper = new ObjectMapper(); // jackson's objectmapper
		VendorOrganisation vendorOrganisation = mapper.convertValue(vendorOrganisationData.get("organisation"), VendorOrganisation.class);
//		System.out.println("vendorOrganisation.getCompanyName() : "+ vendorOrganisation.getCompanyName());
		if(vendorOrganisation.getLegalName().trim().length() > 0) {
			return false;
		} else {
			return true;
		}
	}

	public List<Object> getAllVendorProfilesForVendorRegistration() {
		// TODO Auto-generated method stub
				List<AvailableVendorProfiles> vendorOrgsAll = availableVendorProfilesRepository.findAll();
				
				List<Object> vendorOrganisations = new ArrayList();
				
				for(AvailableVendorProfiles vendorOrg : vendorOrgsAll) {
					ObjectMapper oMapper = new ObjectMapper();
			        // object -> Map
			        Map<String, Object> map = oMapper.convertValue(vendorOrg, Map.class);
			        
			        
			        if(vendorOrg.getCity() != null && vendorOrg.getCity().length() > 0) {
			        	try {
			        		Integer city = Integer.parseInt(vendorOrg.getCity());
			        		ServiceCities serviceCity = servicesCitiesRepository.findOneById(city);
			        		map.put("city",serviceCity.getCityName());
			        	} catch(Exception exp) {
			        		map.put("city",vendorOrg.getCity());
			        	}
			        	
			        } else {
			        	map.put("city","");
					}
			        
			        int activeStatus = vendorOrg.getActiveStatus();
//			        if( deleteStatus == UserAccountStatus.ACTIVE.getValue()){
//			        	 if(activeStatus == UserAccountStatus.INVITED.getValue()) {
//			 	        	map.put("accountStatus","Registered");
//			 	        } else if(activeStatus == UserAccountStatus.ACTIVE.getValue()){
//			 	        	map.put("accountStatus","Active");
//			 	        } 
//			        } else if ( deleteStatus == UserAccountStatus.INACTIVE.getValue() || activeStatus == UserAccountStatus.INACTIVE.getValue()) {
//			        	map.put("accountStatus","Deleted");
//			        } else {
//			        	map.put("accountStatus","Deleted");
//			        }
			       
			        vendorOrganisations.add(map);
				}
				
				return vendorOrganisations;
	}
	
}


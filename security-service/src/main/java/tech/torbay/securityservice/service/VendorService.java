package tech.torbay.securityservice.service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;

import tech.torbay.securityservice.constants.Constants;
import tech.torbay.securityservice.entity.User;
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
			VendorInsurance vendorInsurance = mapper.convertValue(vendorOrganisationData.get("insurance"), VendorInsurance.class);
			
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
				
				vendorUserRepository.save(vendorUser);
				
				// 0 add insurance
				// 1 add service cities
				// 2 add services
				// 3 add licenses
				// 4 add products
				// 5 add brands
				// 6 add memberships
				
				vendorInsurance.setVendorOrganisationId(vendorOrganisation.getVendorOrganisationId());
				vendorInsuranceRepository.save(vendorInsurance);
				
				//1
				
			    List<String> cities = Arrays.asList(serviceCities.split(","));
			    for(String city: cities) {
			    	vendorServicesCitiesRepository.save(new VendorServicesCities(vendorOrganisation.getVendorOrganisationId(),Integer.parseInt(city)));
			    }
			    
			    List<String> vservices = Arrays.asList(services.split(","));
			    for(String service: vservices) {
			    	vendorServicesRepository.save(new VendorServices(vendorOrganisation.getVendorOrganisationId(),service));
			    }
			    
			    List<String> vproducts = Arrays.asList(products.split(","));
			    for(String product: vproducts) {
			    	vendorProductsRepository.save(new VendorProducts(vendorOrganisation.getVendorOrganisationId(),product));
			    }
			    
			    List<String> vbrands = Arrays.asList(brands.split(","));
			    for(String brand: vbrands) {
			    	vendorBrandsRepository.save(new VendorBrands(vendorOrganisation.getVendorOrganisationId(),brand));
			    }
			    
			    List<String> vlicenses = Arrays.asList(licenses.split(","));
			    for(String license: vlicenses) {
			    	vendorLicensesRepository.save(new VendorLicenses(vendorOrganisation.getVendorOrganisationId(),license,"",""));
			    }
			    
			    List<String> vmemberships = Arrays.asList(memberships.split(","));
			    for(String membership: vmemberships) {
			    	vendorMembershipsRepository.save(new VendorMemberships(vendorOrganisation.getVendorOrganisationId(),membership,""));
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

	public VendorUser saveVendorUser(VendorUser vendorUser) {
		// TODO Auto-generated method stub
		return vendorUserRepository.save(vendorUser);
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

	public List<VendorUser> getAllVendorUsersInOrganisation(Integer vendorOrganisationId) {
		// TODO Auto-generated method stub
		return vendorUserRepository.findAllByVendorOrganisationId(vendorOrganisationId);
	}

	public VendorOrganisation getVendorOrganisationById(Integer id) {
		// TODO Auto-generated method stub
		return vendorOrganisationRepository.findByVendorOrganisationId(id);
	}
}


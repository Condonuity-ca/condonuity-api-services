package tech.torbay.userservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tech.torbay.userservice.entity.AvailableVendorProfiles;
import tech.torbay.userservice.entity.VendorOrganisation;

@Repository
public interface AvailableVendorProfilesRepository extends JpaRepository<AvailableVendorProfiles, Integer> {

    List<AvailableVendorProfiles> findAll();

    AvailableVendorProfiles save(AvailableVendorProfiles availableVendorProfiles);
	
    AvailableVendorProfiles findByAllocatedVendorOrgId(Integer id);
    
    AvailableVendorProfiles findByVendorProfileId(Integer id);

	@Query(value="Select avp.* FROM available_vendor_profiles avp where ( avp.active_status =(?1) and avp.allocated_vendor_org_id = 0);", nativeQuery = true)
	List<AvailableVendorProfiles> findAllByActiveStatus(int activeStatus);

		@Query(value = "SELECT avp.* FROM condonuityuat.available_vendor_profiles avp where (" + 
			"avp.legal_name LIKE (?1) or " + 
			"avp.company_name LIKE (?1) or " + 
			"avp.contact_person  LIKE (?1) or " + 
			"avp.address  LIKE (?1) or  " + 
			"avp.city  LIKE (?1) or " + 
			"avp.province  LIKE (?1) or  " + 
			"avp.postal_code  LIKE (?1) or " + 
			"avp.expertise_category  LIKE (?1) or  " + 
			"avp.country_code  LIKE (?1) or " + 
			"avp.email LIKE (?1) or " + 
			"avp.phone_number  LIKE (?1) or " + 
			"avp.fax_number  LIKE (?1) or " + 
			"avp.website  LIKE (?1) or " + 
			"avp.logo_name  LIKE (?1) or " + 
			"avp.established_date  LIKE (?1) or " + 
			"avp.annual_revenue  LIKE (?1) or " + 
			"avp.description  LIKE (?1) or " + 
			"avp.contact_person_email LIKE (?1) or " + 
			"avp.contact_person_phone LIKE (?1) or " + 
			"avp.created_at LIKE (?1) or " + 
			"avp.modified_date LIKE (?1) )"
			, nativeQuery = true)
	List<AvailableVendorProfiles> findAllByKeywordVAP(String keyword);

}

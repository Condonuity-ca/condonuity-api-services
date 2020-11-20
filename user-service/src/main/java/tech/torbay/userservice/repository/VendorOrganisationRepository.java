package tech.torbay.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tech.torbay.userservice.entity.ClientUser;
import tech.torbay.userservice.entity.VendorOrganisation;
import tech.torbay.userservice.entity.VendorPortfolio;

import java.util.List;

@Repository
public interface VendorOrganisationRepository extends JpaRepository<VendorOrganisation, Integer> {

    List<VendorOrganisation> findAll();

	VendorOrganisation save(VendorOrganisation vendorOrganisation);
	
	VendorOrganisation findByVendorOrganisationId(Integer id);

	@Query(value = "SELECT vo.* FROM condonuitydev.vendor_organisation vo where" + 
			"	concat (vo.legal_name, '.', vo.company_name, '.', vo.contact_person," + 
			"	'.', vo.address, '.', vo.city, '.', vo.province, '.', vo.postal_code," + 
			"	'.', vo.expertise_category, '.', vo.country_code, '.', vo.email," + 
			"	'.', vo.phone_number, '.', vo.fax_number, '.', vo.website, '.', vo.logo_name," + 
			"   '.', vo.established_date, '.', vo.annual_revenue, '.', vo.description, '.', vo.contact_person_email," + 
			"   '.', vo.contact_person_phone, '.', vo.created_at, '.', vo.modified_date)" + 
			" LIKE (?1) "
			, nativeQuery = true)
	List<VendorOrganisation> findAllByKeyword(String keyword);

	@Query(value = "SELECT vo.* FROM condonuitydev.vendor_organisation vo where vendor_organisation_id IN (?1) and" + 
			"	concat (vo.legal_name, '.', vo.company_name, '.', vo.contact_person," + 
			"	'.', vo.address, '.', vo.city, '.', vo.province, '.', vo.postal_code," + 
			"	'.', vo.expertise_category, '.', vo.country_code, '.', vo.email," + 
			"	'.', vo.phone_number, '.', vo.fax_number, '.', vo.website, '.', vo.logo_name," + 
			"   '.', vo.established_date, '.', vo.annual_revenue, '.', vo.description, '.', vo.contact_person_email," + 
			"   '.', vo.contact_person_phone, '.', vo.created_at, '.', vo.modified_date)" + 
			" LIKE (?2) "
			, nativeQuery = true)
	List<VendorOrganisation> findAllByKeyword(List<Integer> vendorIds, String keyword);

	@Query(value = "SELECT vo.* FROM condonuitydev.vendor_organisation vo where vendor_organisation_id IN (?1)", nativeQuery = true)
	List<VendorOrganisation> findAllByVendorOrganisationId(List<Integer> vendorIds);

	@Query(value="Select vo.* FROM condonuitydev.vendor_organisation vo where vo.active_status =(?1) or vo.active_status = 2;", nativeQuery = true)
	List<VendorOrganisation> findAllByActiveStatus(int activeStatus);
	
	@Query(value = "SELECT vo.* FROM condonuitydev.vendor_organisation vo where ( vo.active_status = 0 or vo.active_status = 2) and" + 
			"	concat (vo.legal_name, '.', vo.company_name, '.', vo.contact_person," + 
			"	'.', vo.address, '.', vo.city, '.', vo.province, '.', vo.postal_code," + 
			"	'.', vo.expertise_category, '.', vo.country_code, '.', vo.email," + 
			"	'.', vo.phone_number, '.', vo.fax_number, '.', vo.website, '.', vo.logo_name," + 
			"   '.', vo.established_date, '.', vo.annual_revenue, '.', vo.description, '.', vo.contact_person_email," + 
			"   '.', vo.contact_person_phone, '.', vo.created_at, '.', vo.modified_date)" + 
			" LIKE (?1) "
			, nativeQuery = true)
	List<VendorOrganisation> findUnApprovedOrganisationsByKeyword(String keyword);

	@Query(value="Select vo.* FROM condonuitydev.vendor_organisation vo where (vo.delete_status = 1 or vo.delete_status = 2) and ( vo.active_status = 1 );", nativeQuery = true)
	List<VendorOrganisation> findAllActiveInActiveOrganisations();

	@Query(value="Select vo.* FROM condonuitydev.vendor_organisation vo where ( vo.company_name LIKE (?1) and (vo.delete_status = 1 or vo.delete_status = 2) and ( vo.active_status = 1 ));", nativeQuery = true)
	List<VendorOrganisation> findAllActiveInActiveOrganisationsByOrganisationName(String searchKeyword);
	
	@Query(value="Select vo.* FROM condonuitydev.vendor_organisation vo where ( vo.company_name LIKE (?1) and ( vo.active_status = 0 or vo.active_status = 2));", nativeQuery = true)
	List<VendorOrganisation> findAllUnApproveRejectOrganisationsByOrganisationName(String searchKeyword);
	
	@Query(value ="select vo.* from condonuitydev.vendor_organisation vo where lower(vo.company_name) = (?1)", nativeQuery = true)
	List<VendorOrganisation> findByCompanyName(String companyName);

	@Query(value ="select vo.* from condonuitydev.vendor_organisation vo where lower(vo.legal_name) = (?1)", nativeQuery = true)
	List<VendorOrganisation> findByLegalName(String legalName);

//	List<VendorOrganisation> findAllOrderByCompanyNameAsc();

//	List<VendorOrganisation> findAllOrderByCompanyNameDesc();
}

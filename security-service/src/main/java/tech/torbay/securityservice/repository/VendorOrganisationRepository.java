package tech.torbay.securityservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tech.torbay.securityservice.entity.ClientUser;
import tech.torbay.securityservice.entity.VendorOrganisation;

import java.util.List;

@Repository
public interface VendorOrganisationRepository extends JpaRepository<VendorOrganisation, Integer> {

    List<VendorOrganisation> findAll();

	@SuppressWarnings("unchecked")
	VendorOrganisation save(VendorOrganisation vendorOrganisation);
	
	VendorOrganisation findByVendorOrganisationId(Integer id);
	
	@Query(value ="select vo.* from vendor_organisation vo where lower(vo.company_name) = (?1)", nativeQuery = true)
	List<VendorOrganisation> findByCompanyName(String companyName);

	@Query(value ="select vo.* from vendor_organisation vo where lower(vo.legal_name) = (?1)", nativeQuery = true)
	List<VendorOrganisation> findByLegalName(String legalName);
}

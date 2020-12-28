package tech.torbay.userservice.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tech.torbay.userservice.entity.VendorInsurance;

@Repository
public interface VendorInsuranceRepository extends JpaRepository<VendorInsurance, Integer> {

    Optional<VendorInsurance> findByInsuranceId(Integer id);

    VendorInsurance save(VendorInsurance vendorUser);

	List<VendorInsurance> findByVendorOrganisationId(Integer vendorId);
	
	@Modifying
	@Transactional
	@Query(value ="DELETE FROM insurance WHERE vendor_organisation_id = (?1)", nativeQuery = true)
	void deleteByVendorOrganisationId(Integer vendorOrganisationId);
}

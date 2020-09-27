package tech.torbay.userservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tech.torbay.userservice.entity.VendorPortfolio;

@Repository
public interface VendorPortfolioRepository extends JpaRepository<VendorPortfolio, Integer> {

	Optional<VendorPortfolio> findById(Integer id);
	
	@Query(value = "select port.* from condonuitydev.vendor_portfolio port where id = (?1)", nativeQuery = true)
    VendorPortfolio findOneById(Integer id);

    VendorPortfolio save(VendorPortfolio vendorPortfolio);

	List<VendorPortfolio> findByVendorOrganisationIdAndStatus(Integer vendorId, Integer activeStatus);

	List<VendorPortfolio> findByVendorOrganisationIdAndStatusOrderByProjectNameAsc(Integer orgId, Integer activeStatus);
	
	List<VendorPortfolio> findByVendorOrganisationIdAndStatusOrderByProjectNameDesc(Integer orgId, Integer activeStatus);
	
	List<VendorPortfolio> findByVendorOrganisationIdAndStatusOrderByCreatedAtAsc(Integer orgId, Integer activeStatus);

	List<VendorPortfolio> findByVendorOrganisationIdAndStatusOrderByCostAsc(Integer orgId, Integer activeStatus);

	List<VendorPortfolio> findByVendorOrganisationIdAndStatusOrderByDurationAsc(Integer orgId, Integer activeStatus);

}

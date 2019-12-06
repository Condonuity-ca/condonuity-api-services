package tech.torbay.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.torbay.userservice.entity.ClientUser;
import tech.torbay.userservice.entity.VendorOrganisation;
import tech.torbay.userservice.entity.VendorPortfolio;
import tech.torbay.userservice.entity.VendorUser;

import java.util.List;
import java.util.Optional;

@Repository
public interface VendorPortfolioRepository extends JpaRepository<VendorPortfolio, Integer> {

    Optional<VendorPortfolio> findById(Integer id);

    VendorPortfolio save(VendorUser vendorUser);

	List<VendorPortfolio> findByVendorOrganisationId(Integer vendorId);
}

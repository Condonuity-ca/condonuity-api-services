package tech.torbay.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tech.torbay.userservice.entity.ClientUser;
import tech.torbay.userservice.entity.VendorBrands;
import tech.torbay.userservice.entity.VendorOrganisation;
import tech.torbay.userservice.entity.VendorProducts;
import tech.torbay.userservice.entity.VendorUser;

import java.util.List;

import javax.transaction.Transactional;

@Repository
public interface VendorProductsRepository extends JpaRepository<VendorProducts, Integer> {

    List<VendorProducts> findAll();
    
    VendorProducts findOneById(Integer id);
    
    VendorProducts save(VendorProducts vendorProducts);

	List<VendorProducts> findByVendorOrganisationId(Integer vendorOrganisationId);
	
	@Query(value = "SELECT product_name FROM vendor_products vp " + 
			"WHERE vp.vendor_organisation_id = (?1)", nativeQuery =true)
	List<String> getVendorProducts(Integer vendorOrganisationId);
	
	@Modifying
	@Transactional
	@Query(value ="DELETE FROM vendor_products WHERE vendor_organisation_id = (?1)", nativeQuery = true)
	void deleteByVendorOrganisationId(Integer vendorOrganisationId);
}

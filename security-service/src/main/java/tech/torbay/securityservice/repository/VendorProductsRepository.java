package tech.torbay.securityservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tech.torbay.securityservice.entity.VendorProducts;

import java.util.List;

@Repository
public interface VendorProductsRepository extends JpaRepository<VendorProducts, Integer> {

    List<VendorProducts> findAll();
    
    VendorProducts findOneById(Integer id);
    
    VendorProducts save(VendorProducts vendorProducts);

	List<VendorProducts> findByVendorOrganisationId(Integer vendorOrganisationId);
	
	@Query(value = "SELECT product_name FROM vendor_products vp " + 
			"WHERE vp.vendor_organisation_id = (?1)", nativeQuery =true)
	List<String> getVendorProducts(Integer vendorOrganisationId);
}

package tech.torbay.userservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tech.torbay.userservice.entity.VendorOrganisationProfileImages;

@Repository
public interface VendorOrganisationProfileImagesRepository extends JpaRepository<VendorOrganisationProfileImages, Integer> {

    List<VendorOrganisationProfileImages> findAll();
    
    VendorOrganisationProfileImages findOneById(Integer Id);

	VendorOrganisationProfileImages findByVendorOrganisationId(Integer organisationId);
	
}

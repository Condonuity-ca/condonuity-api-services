package tech.torbay.userservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tech.torbay.userservice.entity.VendorTags;
import tech.torbay.userservice.entity.VendorUser;

@Repository
public interface VendorTagsRepository extends JpaRepository<VendorTags, Integer> {

    List<VendorTags> findAll();
    
    @Query(value = "SELECT vt.vendor_id from vendor_tags vt where tag_id IN (?1)", nativeQuery = true)
	List<Integer> findAllByTagId(List<Integer> tagIds);
}

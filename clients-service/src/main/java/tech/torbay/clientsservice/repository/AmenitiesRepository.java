package tech.torbay.clientsservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tech.torbay.clientsservice.entity.Amenities;

@Repository
public interface AmenitiesRepository extends JpaRepository<Amenities, Integer> {

    List<Amenities> findAll();

    Amenities findOneById(Integer Id);
    
//    @Query("select tagName from PredefinedTags co where co.tagId IN (?1)")
//   	List<String> findByTagId(List<Integer> ids);
//    
//    @Query(value = "select tag_id, tag_name from predefined_tags co where tag_id IN (?1)", nativeQuery = true)
//   	List<Object[]> findTagsByTagId(List<Integer> ids);
}

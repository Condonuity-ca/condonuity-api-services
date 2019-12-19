package tech.torbay.userservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tech.torbay.userservice.entity.PredefinedTags;

@Repository
public interface PredefinedTagsRepository extends JpaRepository<PredefinedTags, Integer> {

    List<PredefinedTags> findAll();

    PredefinedTags findByTagId(Integer Id);
    
    @Query("select tagName from PredefinedTags co where co.tagId IN (?1)")
   	List<String> findByTagId(List<Integer> ids);
}

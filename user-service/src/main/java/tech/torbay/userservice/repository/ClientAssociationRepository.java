package tech.torbay.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tech.torbay.userservice.entity.ClientAssociation;
import tech.torbay.userservice.entity.ClientOrganisation;
import tech.torbay.userservice.entity.ClientUser;

import java.util.List;

import javax.transaction.Transactional;

@Repository
public interface ClientAssociationRepository extends JpaRepository<ClientAssociation, Integer> {

    List<ClientAssociation> findAll();

    
	List<ClientAssociation> findClientOrganisationIdByClientId(Integer clientUserId);


	List<ClientAssociation> findClientIdByClientOrganisationId(Integer clientOrgId);
	

	ClientAssociation findByClientIdAndClientOrganisationId(Integer clientId, Integer clientOrgId);
    
	@Modifying
	@Transactional
	@Query(value="UPDATE condonuitydev.client_association SET delete_status = (?1) WHERE client_organisation_id=(?2) ", nativeQuery = true)
	int setDeleteStatusByClientOrganisationId(Integer deleteStatus,Integer clientOrgId);
	
	@Modifying
	@Transactional
	@Query(value="UPDATE condonuitydev.client_association SET delete_status = (?1) WHERE client_id=(?2) AND client_organisation_id = (?3)", nativeQuery = true)
	int setDeleteStatusByClientIdAndClientOrganisationId(Integer deleteStatus, Integer clientId, Integer clientOrganisationId);
}

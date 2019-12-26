package tech.torbay.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tech.torbay.userservice.entity.ClientAssociation;
import tech.torbay.userservice.entity.ClientOrganisation;
import tech.torbay.userservice.entity.ClientUser;

import java.util.List;

@Repository
public interface ClientAssociationRepository extends JpaRepository<ClientAssociation, Integer> {

    List<ClientAssociation> findAll();

    
	List<ClientAssociation> findClientOrganisationIdByClientId(Integer clientUserId);


	List<ClientAssociation> findClientIdByClientOrganisationId(Integer clientOrgId);
	

	ClientAssociation findByClientIdAndClientOrganisationId(Integer clientId, Integer clientOrgId);
    
}

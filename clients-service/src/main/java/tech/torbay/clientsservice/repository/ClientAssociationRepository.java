package tech.torbay.clientsservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tech.torbay.clientsservice.entity.ClientAssociation;

@Repository
public interface ClientAssociationRepository extends JpaRepository<ClientAssociation, Integer> {

    List<ClientAssociation> findAll();

    
	List<ClientAssociation> findClientOrganisationIdByClientId(Integer clientUserId);


	List<ClientAssociation> findClientIdByClientOrganisationId(Integer clientOrgId);
	

	ClientAssociation findByClientIdAndClientOrganisationId(Integer clientId, Integer clientOrgId);
    
}
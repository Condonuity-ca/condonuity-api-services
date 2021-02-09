package tech.torbay.clientsservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tech.torbay.clientsservice.entity.ClientAssociation;

@Repository
public interface ClientAssociationRepository extends JpaRepository<ClientAssociation, Integer> {

    List<ClientAssociation> findAll();

    
	List<ClientAssociation> findClientOrganisationIdByClientId(Integer clientUserId);


	List<ClientAssociation> findClientIdByClientOrganisationId(Integer clientOrgId);
	

	ClientAssociation findByClientIdAndClientOrganisationId(Integer clientId, Integer clientOrgId);


	@Query(value = "select ca.* from client_association ca where account_verification_status = 1 and user_account_status = 1 and delete_status = 1 and client_id = (?1)", nativeQuery = true)
	List<ClientAssociation> findAllByClientId(Integer clientId);
    
}

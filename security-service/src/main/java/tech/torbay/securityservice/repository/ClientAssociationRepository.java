package tech.torbay.securityservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tech.torbay.securityservice.entity.ClientAssociation;
import tech.torbay.securityservice.entity.ClientUser;
import tech.torbay.securityservice.entity.User;
import tech.torbay.securityservice.entity.VendorUser;

import java.util.List;

@Repository
public interface ClientAssociationRepository extends JpaRepository<ClientAssociation, Integer> {

    List<ClientAssociation> findAll();

    ClientAssociation save(ClientAssociation clientAssociation);
	
    @Query(value = "select ca.* from condonuitydev.client_association ca where client_organisation_id = (?1)  AND ( user_account_status = 1 OR user_account_status = 0 )", nativeQuery = true)//only active users
    List<ClientAssociation> findAllByClientOrganisationId(Integer clientOrganisationId);
    
    List<ClientAssociation> findAllByClientId(Integer clientId);

	ClientAssociation findByClientIdAndClientOrganisationId(Integer clientId, Integer clientOrganisationId);
}

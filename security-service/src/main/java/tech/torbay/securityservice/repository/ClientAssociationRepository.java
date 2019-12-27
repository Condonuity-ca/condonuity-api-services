package tech.torbay.securityservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
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
	
    List<ClientAssociation> findAllByClientOrganisationId(Integer clientOrganisationId);

	ClientAssociation findByClientIdAndClientOrganisationId(Integer clientId, Integer clientOrganisationId);
}

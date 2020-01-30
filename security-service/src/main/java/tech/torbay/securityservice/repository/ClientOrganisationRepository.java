package tech.torbay.securityservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.torbay.securityservice.entity.ClientUser;
import tech.torbay.securityservice.entity.User;
import tech.torbay.securityservice.entity.ClientOrganisation;

import java.util.List;

@Repository
public interface ClientOrganisationRepository extends JpaRepository<ClientOrganisation, Integer> {

    List<ClientOrganisation> findAll();
    
    ClientOrganisation findByClientOrganisationId(Integer id);
    
    ClientOrganisation findByOrganisationName(String organisationName);
}

package tech.torbay.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tech.torbay.userservice.entity.ClientUser;
import tech.torbay.userservice.entity.ClientOrganisation;

import java.util.List;

@Repository
public interface ClientOrganisationRepository extends JpaRepository<ClientOrganisation, Integer> {

    List<ClientOrganisation> findAll();
    
    ClientOrganisation findByClientOrganisationId(Integer id);

    @Query("select co from ClientOrganisation co where co.clientOrganisationId IN (?1)")
	List<ClientOrganisation> findByClientOrganisationId(List<Integer> ids);

}

package tech.torbay.clientsservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tech.torbay.clientsservice.entity.ClientUser;

@Repository
public interface ClientUserRepository extends JpaRepository<ClientUser, Integer> {

    List<ClientUser> findAll();

    ClientUser findByClientId(Integer Id);
    
    ClientUser findByEmail(String email);

    @Query("select cu from ClientUser cu where cu.clientId IN (?1)")
	List<ClientUser> findByClientId(List<Integer> ids);

    @Query(value = "select cu.* from client_user cu where cu.delete_status = 1", nativeQuery = true)
	List<ClientUser> findAllActiveClientUser();

//	List<ClientOrganisation> findClientOrganisationByClientId();
}

package tech.torbay.userservice.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tech.torbay.userservice.entity.ClientUser;

@Repository
public interface ClientUserRepository extends JpaRepository<ClientUser, Integer> {

    List<ClientUser> findAll();

    ClientUser findByClientId(Integer Id);
    
    ClientUser findByEmail(String email);

    @Query("select cu from ClientUser cu where cu.clientId IN (?1)")
	List<ClientUser> findByClientId(List<Integer> ids);

//	List<ClientOrganisation> findClientOrganisationByClientId();
    
    @Modifying
	@Transactional
	@Query(value="UPDATE condonuitydev.client_user SET first_name = (?1), last_name = (?2) WHERE client_id=(?3)", nativeQuery = true)
	int setFirstNameAndLastNameByClientId(String firstName, String lastName, Integer userId);
    
    boolean existsByEmail(String email);   
    
    @Query(value = "SELECT cu.* FROM condonuitydev.client_user cu where " + 
			"concat (cu.first_name, '.', cu.last_name, '.', cu.email, '.', " +
			"cu.phone, '.', cu.city, '.', " +
			"cu.created_at, '.', cu.modified_date)" + 
			" LIKE (?1) "
			, nativeQuery = true)
    List<ClientUser> findAllByKeyword(String keyword);
}

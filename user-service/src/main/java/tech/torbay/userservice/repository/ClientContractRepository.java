package tech.torbay.userservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tech.torbay.userservice.entity.ClientContract;

@Repository
public interface ClientContractRepository extends JpaRepository<ClientContract, Integer> {

    List<ClientContract> findAll();
	
    @Query(value = "select cc.* from condonuitydev.client_contract cc where client_organisation_id = (?1) and status = 1", nativeQuery = true)	
	List<ClientContract> findAllByClientOrganisationId(Integer clientOrganisationId);
    
    ClientContract findOneById(Integer id);
    
    ClientContract save(ClientContract clientContract);

}

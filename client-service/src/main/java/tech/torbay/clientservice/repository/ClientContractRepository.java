package tech.torbay.clientservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tech.torbay.clientservice.entity.ClientContract;

@Repository
public interface ClientContractRepository extends JpaRepository<ClientContract, Integer> {

    List<ClientContract> findAll();
	
    @Query(value = "select cc.* from condonuitydev.client_contract cc where client_organisation_id = (?1) and status = 1", nativeQuery = true)	
	List<ClientContract> findAllByClientOrganisationId(Integer clientOrganisationId);
    
    ClientContract findOneById(Integer id);
    
    ClientContract save(ClientContract clientContract);

    @Query(value = "SELECT cc.* " + 
    		"FROM condonuitydev.client_contract cc " + 
    		"where ( client_organisation_id = (?1) and status = 1) and " + 
    		"concat (cc.vendor_name, '.', cc.services, '.', cc.signed_date, " + 
    		"'.', cc.expiry_date, '.', cc.cost, '.', cc.expected_increase, " + 
    		"'.', cc.term, '.', cc.cancellation_term, " + 
    		"'.', cc.notes, '.', cc.created_at, '.', cc.modified_date) LIKE (?2)", nativeQuery = true)
	List<ClientContract> findAllByClientOrganisationIdAndKeyword(Integer clientOrganisationId, String keyword);

    List<ClientContract> findAllByClientOrganisationIdAndCancellationUnits(Integer clientOrganisationId, Integer cancellationUnit);
    
    List<ClientContract> findAllByClientOrganisationIdAndGstAvailablity(Integer clientOrganisationId, Integer gstAvailability);
    
    List<ClientContract> findAllByClientOrganisationIdAndRenewalType(Integer clientOrganisationId, Integer renewalType);
    
    List<ClientContract> findAllByClientOrganisationIdAndCostTermUnits(Integer clientOrganisationId, Integer costTermUnits);
    
    List<ClientContract> findAllByClientOrganisationIdAndTermUnits(Integer clientOrganisationId, Integer termUnits);
}

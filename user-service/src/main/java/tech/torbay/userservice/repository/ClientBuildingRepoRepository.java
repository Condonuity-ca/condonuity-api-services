package tech.torbay.userservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tech.torbay.userservice.entity.ClientBuildingRepository;
import tech.torbay.userservice.entity.ClientTask;

@Repository
public interface ClientBuildingRepoRepository extends JpaRepository<ClientBuildingRepository, Integer> {

    List<ClientBuildingRepository> findAll();
	
    @Query(value = "select br.* from client_building_repository br where client_organisation_id = (?1) and status = 1", nativeQuery = true)	
	List<ClientBuildingRepository> findAllByClientOrganisationId(Integer clientOrganisationId);
    
    ClientBuildingRepository findOneById(Integer id);
    
    ClientBuildingRepository save(ClientBuildingRepository clientBuildingRepository);

    @Query(value = "SELECT br.* FROM client_building_repository br where client_organisation_id = (?1) and" + 
			"	concat (br.unit_number, '.', br.date_of_lien, '.', br.first_name," + 
			"	'.', br.last_name, '.', br.comments, '.', br.contact_number, '.', br.contact_email," + 
			"	'.', br.vehicle_mode, '.', br.color, '.', br.license_number," + 
			"	'.', br.pet_description, '.', br.pet_name, '.', br.emergency_contact_name, '.', br.emergency_contact_number," + 
			"   '.', br.emergency_contact_email, '.', br.handicap_info, '.', br.created_at, '.', br.modified_date)" + 
			" LIKE (?2) ", nativeQuery = true)
	List<ClientBuildingRepository> findAllByKeyword(Integer clientOrganisationId, String keyword);
    
    
    @Query(value = "SELECT br.*, cu.first_name, cu.last_name, cu.legal_name " + 
    		"FROM client_building_repository br " + 
    		"INNER JOIN client_user cu " + 
    		"ON ( br.created_by = cu.client_id or br.modified_by = cu.client_id) " + 
    		"where client_organisation_id = (?1) and " + 
    		" (br.unit_number LIKE (?2) or "+
    		"br.date_of_lien LIKE (?2) or "+
    		"br.first_name LIKE (?2) or " + 
    		"br.last_name LIKE (?2) or " +
    		"br.comments LIKE (?2) or "+
    		"br.contact_number LIKE (?2) or " + 
    		"br.contact_email LIKE (?2) or " + 
    		"br.vehicle_mode LIKE (?2) or "+
    		"br.color LIKE (?2) or "+
    		"br.license_number LIKE (?2) or " + 
    		"br.pet_description LIKE (?2) or "+
    		"br.pet_name LIKE (?2) or "+
    		"br.emergency_contact_name LIKE (?2) or " + 
    		"br.emergency_contact_number LIKE (?2) or "+
    		"br.emergency_contact_email LIKE (?2) or " + 
    		"br.handicap_info LIKE (?2) or "+
    		"br.created_at LIKE (?2) or " +
    		"br.modified_date LIKE (?2) or " + 
    		"cu.first_name LIKE (?2) or " +
    		"cu.last_name LIKE (?2) " +
    		")", nativeQuery = true)
	List<ClientBuildingRepository> findAllWithInnerJoinByKeyword(Integer clientOrganisationId, String keyword);

	List<ClientBuildingRepository> findAllByClientOrganisationIdAndTenantStatus(Integer clientOrganisationId, int tenantStatus);

	List<ClientBuildingRepository> findAllByClientOrganisationIdAndUnitType(Integer clientOrganisationId, int unitType);

	List<ClientBuildingRepository> findAllByClientOrganisationIdAndLienType(Integer clientOrganisationId, int lienType);

	List<ClientBuildingRepository> findAllByClientOrganisationIdAndPersonTenantType(Integer clientOrganisationId, int personTenantType);

}

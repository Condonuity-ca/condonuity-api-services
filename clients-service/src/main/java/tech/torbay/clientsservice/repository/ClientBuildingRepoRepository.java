package tech.torbay.clientsservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tech.torbay.clientsservice.entity.ClientBuildingRepository;

@Repository
public interface ClientBuildingRepoRepository extends JpaRepository<ClientBuildingRepository, Integer> {

    List<ClientBuildingRepository> findAll();
	
    @Query(value = "select br.* from condonuitydev.client_building_repository br where client_organisation_id = (?1) and status = 1", nativeQuery = true)	
	List<ClientBuildingRepository> findAllByClientOrganisationId(Integer clientOrganisationId);
    
    ClientBuildingRepository findOneById(Integer id);
    
    ClientBuildingRepository save(ClientBuildingRepository clientBuildingRepository);

    @Query(value = "SELECT br.* FROM condonuitydev.client_building_repository br where client_organisation_id = (?1) and" + 
			"	concat (br.unit_number, '.', br.date_of_lien, '.', br.first_name," + 
			"	'.', br.last_name, '.', br.comments, '.', br.contact_number, '.', br.contact_email," + 
			"	'.', br.vehicle_mode, '.', br.color, '.', br.license_number," + 
			"	'.', br.pet_description, '.', br.pet_name, '.', br.emergency_contact_name, '.', br.emergency_contact_number," + 
			"   '.', br.emergency_contact_email, '.', br.handicap_info, '.', br.created_at, '.', br.modified_date)" + 
			" LIKE (?2) ", nativeQuery = true)
	List<ClientBuildingRepository> findAllByKeyword(Integer clientOrganisationId, String keyword);
    
    
    @Query(value = "SELECT br.*, cu.first_name, cu.last_name, cu.legal_name \r\n" + 
    		"FROM condonuitydev.client_building_repository br \r\n" + 
    		"INNER JOIN condonuitydev.client_user cu \r\n" + 
    		"ON ( br.created_by = cu.client_id or br.modified_by = cu.client_id)\r\n" + 
    		"where client_organisation_id = (?1) and \r\n" + 
    		"concat (br.unit_number, '.', br.date_of_lien, '.', br.first_name, \r\n" + 
    		"'.', br.last_name, '.', br.comments, '.', br.contact_number, \r\n" + 
    		"'.', br.contact_email, \r\n" + 
    		"'.', br.vehicle_mode, '.', br.color, '.', br.license_number, \r\n" + 
    		"'.', br.pet_description, '.', br.pet_name, '.', br.emergency_contact_name, \r\n" + 
    		"'.', br.emergency_contact_number, '.', br.emergency_contact_email, \r\n" + 
    		"'.', br.handicap_info, '.', br.created_at, '.', br.modified_date, '.', \r\n" + 
    		"cu.first_name, '.', cu.last_name) LIKE (?2)", nativeQuery = true)
	List<ClientBuildingRepository> findAllWithInnerJoinByKeyword(Integer clientOrganisationId, String keyword);

	List<ClientBuildingRepository> findAllByClientOrganisationIdAndTenantStatus(Integer clientOrganisationId, int tenantStatus);

	List<ClientBuildingRepository> findAllByClientOrganisationIdAndUnitType(Integer clientOrganisationId, int unitType);

	List<ClientBuildingRepository> findAllByClientOrganisationIdAndLienType(Integer clientOrganisationId, int lienType);

	List<ClientBuildingRepository> findAllByClientOrganisationIdAndPersonTenantType(Integer clientOrganisationId, int personTenantType);

}

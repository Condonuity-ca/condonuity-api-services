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

}

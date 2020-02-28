package tech.torbay.clientservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tech.torbay.clientservice.entity.ClientOrganisation;

@Repository
public interface ClientOrganisationRepository extends JpaRepository<ClientOrganisation, Integer> {

    List<ClientOrganisation> findAll();
    
    ClientOrganisation findByClientOrganisationId(Integer id);

    @Query("select co from ClientOrganisation co where co.clientOrganisationId IN (?1)")
	List<ClientOrganisation> findByClientOrganisationId(List<Integer> ids);
    
	@Query(value = "SELECT co.* FROM condonuitydev.client_organisation co where " + 
			"concat (co.organisation_name, '.', co.management_company, '.', co.corporate_number, '.', " + 
			"co.registration_date, '.', co.address, '.', co.city, '.', co.province, '.', co.postal_code, '.', " + 
			"co.country_code, '.', co.phone_number, '.', co.fax_number or co.units, '.', " + 
			"co.voting_units, '.', co.manager_name, '.', co.manager_email, '.', co.manager_phone, '.', " + 
			"co.board_email, '.', co.general_email, '.', co.management_email, '.', co.created_at, '.', co.modified_date )" + 
			" LIKE (?1)", nativeQuery = true)
    List<ClientOrganisation> findAllByKeyword(String keyword);

//	@Query("SELECT co.* FROM condonuitydev.client_organisation co where co.organisation_name or co.management_company LIKE '%29%';")
//	@Query("SELECT co.* FROM condonuitydev.client_organisation co where co.client_organisation_id = (?1) and ( co.organisation_name or co.management_company ) LIKE '%(?2)%';")
}

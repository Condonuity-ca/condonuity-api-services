package tech.torbay.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tech.torbay.userservice.entity.ClientUser;
import tech.torbay.userservice.entity.ClientOrganisation;

import java.util.List;

import javax.transaction.Transactional;

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

	@Modifying
	@Transactional
	@Query(value="UPDATE condonuitydev.client_organisation SET organisation_name = (?1) , corporate_number = (?2) WHERE client_organisation_id=(?3);", nativeQuery = true)
	int setOrganisationNameAndCorporateNumberByClientOrganisationId(String corporationName, String corporationNumber, Integer clientOrganisationId);

	@Query(value="Select co.* FROM condonuitydev.client_organisation co where co.active_status =(?1) or co.active_status = 2;", nativeQuery = true)
	List<ClientOrganisation> findAllByActiveStatus(int activeStatus);
	
//	@Query("SELECT co.* FROM condonuitydev.client_organisation co where co.organisation_name or co.management_company LIKE '%29%';")
//	@Query("SELECT co.* FROM condonuitydev.client_organisation co where co.client_organisation_id = (?1) and ( co.organisation_name or co.management_company ) LIKE '%(?2)%';")
	
	@Query(value = "SELECT co.* FROM condonuitydev.client_organisation co where  ( co.active_status = 0 or co.active_status = 2)and " + 
			"concat (co.organisation_name, '.', co.management_company, '.', co.corporate_number, '.', " + 
			"co.registration_date, '.', co.address, '.', co.city, '.', co.province, '.', co.postal_code, '.', " + 
			"co.country_code, '.', co.phone_number, '.', co.fax_number or co.units, '.', " + 
			"co.voting_units, '.', co.manager_name, '.', co.manager_email, '.', co.manager_phone, '.', " + 
			"co.board_email, '.', co.general_email, '.', co.management_email, '.', co.created_at, '.', co.modified_date )" + 
			" LIKE (?1)", nativeQuery = true)
    List<ClientOrganisation> findUnApprovedOrganisationsByKeyword(String keyword);

	@Query(value="Select co.* FROM condonuitydev.client_organisation co where ( co.delete_status = 1 or co.delete_status = 2) and ( co.active_status = 1 );", nativeQuery = true)
	List<ClientOrganisation> findAllActiveInActiveOrganisations();

	@Query(value="Select co.* FROM condonuitydev.client_organisation co where ( co.organisation_name LIKE (?1) and ( co.delete_status = 1 or co.delete_status = 2) and ( co.active_status = 1 ));", nativeQuery = true)
	List<ClientOrganisation> findAllActiveInActiveOrganisationsByOrganisationName(String keyword);

	@Query(value="Select co.* FROM condonuitydev.client_organisation co where ( co.corporate_number LIKE (?1) and ( co.delete_status = 1 or co.delete_status = 2) and ( co.active_status = 1 ));", nativeQuery = true)
	List<ClientOrganisation> findAllActiveInActiveOrganisationsByCorporationNumber(String keyword);
	
	@Query(value="Select co.* FROM condonuitydev.client_organisation co where ( co.organisation_name LIKE (?1) and ( co.active_status = 0 or co.active_status = 2 ));", nativeQuery = true)
	List<ClientOrganisation> findAllUnApproveRejectOrganisationsByOrganisationName(String keyword);

	@Query(value="Select co.* FROM condonuitydev.client_organisation co where ( co.corporate_number LIKE (?1) and ( co.active_status = 0 or co.active_status = 2 ));", nativeQuery = true)
	List<ClientOrganisation> findAllUnApproveRejectOrganisationsByCorporationNumber(String keyword);
	
	List<ClientOrganisation> findByOrganisationName(String organisationName);
	
	List<ClientOrganisation> findByCorporateNumber(String corporationNumber);
}

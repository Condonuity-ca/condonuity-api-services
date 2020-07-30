package tech.torbay.userservice.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tech.torbay.userservice.entity.ClientAssociation;

@Repository
public interface ClientAssociationRepository extends JpaRepository<ClientAssociation, Integer> {

    List<ClientAssociation> findAll();

    
	List<ClientAssociation> findClientOrganisationIdByClientId(Integer clientUserId);


	List<ClientAssociation> findClientIdByClientOrganisationId(Integer clientOrgId);
	

	ClientAssociation findByClientIdAndClientOrganisationId(Integer clientId, Integer clientOrgId);
    
	@Modifying
	@Transactional
	@Query(value="UPDATE condonuitydev.client_association SET delete_status = (?1) WHERE client_organisation_id=(?2) ", nativeQuery = true)
	int setDeleteStatusByClientOrganisationId(Integer deleteStatus,Integer clientOrgId);
	
	@Modifying
	@Transactional
	@Query(value="UPDATE condonuitydev.client_association SET user_account_Status = (?1) WHERE client_organisation_id=(?2) ", nativeQuery = true)
	int setUserAccountStatusByClientOrganisationId(Integer userAccountStatus,Integer clientOrgId);
	
	//single
	@Modifying
	@Transactional
	@Query(value="UPDATE condonuitydev.client_association SET delete_status = (?1) WHERE client_id=(?2) AND client_organisation_id = (?3)", nativeQuery = true)
	int setDeleteStatusByClientIdAndClientOrganisationId(Integer deleteStatus, Integer clientId, Integer clientOrganisationId);

	//all
	@Modifying
	@Transactional
	@Query(value="UPDATE condonuitydev.client_association SET delete_status = (?1) WHERE client_id=(?2)", nativeQuery = true)
	int setDeleteStatusByClientId(Integer deleteStatus, Integer clientId);
	
	@Modifying
	@Transactional
	@Query(value="UPDATE condonuitydev.client_association SET user_role = (?1), client_user_type = (?2) WHERE client_id=(?3) AND client_organisation_id = (?4)", nativeQuery = true)
	int setUserRoleAndClientUserTypeByClientIdAndClientOrganisationId(Integer userRole, Integer clientUserType,
			Integer userId, Integer organisationId);

	List<ClientAssociation> findAllByClientId(Integer clientId);
	
	@Query(value = "select ca.* from condonuitydev.client_association ca where client_organisation_id = (?1)  AND ( user_account_status = 1 OR user_account_status = 0 )", nativeQuery = true)//only active users
    List<ClientAssociation> findAllByClientOrganisationId(Integer clientOrganisationId);
	
	@Query(value = "select ca.* from condonuitydev.client_association ca where client_organisation_id = (?1)  AND ( user_account_status = 1 )", nativeQuery = true)//only active users
    List<ClientAssociation> findAllActiveUsersByClientOrganisationId(Integer clientOrganisationId);
	
	@Query(value = "select ca.* from condonuitydev.client_association ca where client_id = (?1)  AND ( user_account_status = 1 )", nativeQuery = true)//only active users
    List<ClientAssociation> findAllActiveUsersByClientUserId(Integer clientUserId);
}

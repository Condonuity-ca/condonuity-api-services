package tech.torbay.userservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tech.torbay.userservice.entity.RegistrationLogs;

@Repository
public interface RegistrationLogsRepository extends JpaRepository<RegistrationLogs, Integer> {

    List<RegistrationLogs> findAll();
    
	RegistrationLogs save(RegistrationLogs registrationLogs);

	RegistrationLogs findOneById(Integer id);

	List<RegistrationLogs> findByUserIdAndUserTypeAndHash(Integer clientUserId, Integer userType, String hash);
	
	List<RegistrationLogs> findByUserTypeAndOrganisationId(Integer userType, Integer organisationId);
}

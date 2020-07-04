package tech.torbay.securityservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.torbay.securityservice.entity.ClientUser;
import tech.torbay.securityservice.entity.RegistrationLogs;
import tech.torbay.securityservice.entity.SupportUser;
import tech.torbay.securityservice.entity.VendorOrganisation;
import tech.torbay.securityservice.entity.VendorUser;

import java.util.List;

@Repository
public interface RegistrationLogsRepository extends JpaRepository<RegistrationLogs, Integer> {

    List<RegistrationLogs> findAll();
    
	RegistrationLogs save(RegistrationLogs registrationLogs);

	RegistrationLogs findOneById(Integer id);
}

package tech.torbay.securityservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tech.torbay.securityservice.entity.UserInviteLogs;

@Repository
public interface UserInviteLogsRepository extends JpaRepository<UserInviteLogs, Integer> {

    List<UserInviteLogs> findAll();
    
    UserInviteLogs save(UserInviteLogs registrationLogs);

    UserInviteLogs findOneById(Integer id);

	List<UserInviteLogs> findByUserIdAndUserTypeAndOrganisationIdAndHash(Integer clientUserId, Integer userType, Integer organisationId, String hash);
}

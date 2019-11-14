package tech.torbay.securityservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.torbay.securityservice.entity.ClientUser;

import java.util.List;

@Repository
public interface ClientUserRepository extends JpaRepository<ClientUser, Integer> {

    List<ClientUser> findAll();

    ClientUser findByClientId(Integer Id);
    
    ClientUser findByEmail(String email);   
}

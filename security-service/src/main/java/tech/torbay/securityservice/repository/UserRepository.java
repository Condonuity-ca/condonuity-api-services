package tech.torbay.securityservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.torbay.securityservice.entity.ClientUser;
import tech.torbay.securityservice.entity.User;
import tech.torbay.securityservice.entity.VendorUser;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    List<User> findAll();

	User findByUsername(String email);
	
	User findByUserIdAndUserType(Integer id, Integer userType);

	User findByUsernameAndPassword(String username, String password);

	User save(User user);
	
}

package tech.torbay.clientservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tech.torbay.clientservice.entity.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    List<User> findAll();

	User findByUsername(String email);
	
	User findByUserIdAndUserType(Integer id, Integer userType);

	User findByUsernameAndPassword(String username, String password);

	User save(User user);

	void deleteByUserIdAndUserType(Integer clientId, int value);
	
}

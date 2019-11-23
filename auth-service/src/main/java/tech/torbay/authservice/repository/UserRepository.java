package tech.torbay.authservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.torbay.authservice.entity.LoginUser;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<LoginUser, Integer> {

    List<LoginUser> findAll();

//	LoginUser findByUsername(String email);
//	
//	LoginUser findByUserIdAndUserType(Integer id, Integer userType);
//
//	LoginUser findByUsernameAndPassword(String username, String password);
//
//	LoginUser save(LoginUser user);
	
}

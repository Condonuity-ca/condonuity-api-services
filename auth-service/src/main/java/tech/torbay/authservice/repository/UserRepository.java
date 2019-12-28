package tech.torbay.authservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.torbay.authservice.entity.LoginUser;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<LoginUser, Integer> {
	LoginUser findByUsername(String email);
}

package com.bezkoder.springjwt.repository;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.bezkoder.springjwt.models.CustomUser;

@Repository
public interface CustomUserRepository extends JpaRepository<CustomUser, Long> {
	Optional<CustomUser> findByUsername(String username);
	Boolean existsByUsername(String username);
	Boolean existsByEmail(String email);
}

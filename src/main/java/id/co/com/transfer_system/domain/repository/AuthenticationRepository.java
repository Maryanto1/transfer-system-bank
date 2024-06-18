package id.co.com.transfer_system.domain.repository;

import id.co.com.transfer_system.domain.entity.UserAuthentication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthenticationRepository extends JpaRepository<UserAuthentication, Long> {
}

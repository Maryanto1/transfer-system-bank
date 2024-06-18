package id.co.com.transfer_system.domain.repository;

import id.co.com.transfer_system.domain.entity.Inbox;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InboxRepository extends JpaRepository<Inbox, Long> {
}

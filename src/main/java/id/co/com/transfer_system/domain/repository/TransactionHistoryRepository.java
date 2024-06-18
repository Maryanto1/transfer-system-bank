package id.co.com.transfer_system.domain.repository;

import id.co.com.transfer_system.domain.entity.TransactionHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionHistoryRepository extends JpaRepository<TransactionHistory, Long> {
}

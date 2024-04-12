package tech.projects.financialinvestments.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.projects.financialinvestments.entity.account.AccountStockID;

public interface AccountStockRepository extends JpaRepository<AccountStockRepository, AccountStockID> {
}

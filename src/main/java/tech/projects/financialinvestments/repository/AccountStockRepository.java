package tech.projects.financialinvestments.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.projects.financialinvestments.entity.account.AccountStock;
import tech.projects.financialinvestments.entity.account.AccountStockID;

@Repository
public interface AccountStockRepository extends JpaRepository<AccountStock, AccountStockID> {
}

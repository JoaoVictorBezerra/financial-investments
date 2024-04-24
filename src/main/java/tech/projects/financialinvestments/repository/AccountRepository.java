package tech.projects.financialinvestments.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.projects.financialinvestments.entity.account.Account;

public interface AccountRepository extends JpaRepository<Account, String> {
}

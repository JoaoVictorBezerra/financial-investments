package tech.projects.financialinvestments.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.projects.financialinvestments.entity.BillingAddress;

public interface BillingAddressRepository extends JpaRepository<BillingAddress, String> {
}

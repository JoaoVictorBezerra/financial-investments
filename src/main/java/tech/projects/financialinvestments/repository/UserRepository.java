package tech.projects.financialinvestments.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.projects.financialinvestments.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
}

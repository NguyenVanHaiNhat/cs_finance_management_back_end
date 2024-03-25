package org.example.cs_finance_management_back_end.repository;
import org.example.cs_finance_management_back_end.model.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
public interface IUsersRepository extends JpaRepository<Users,Long> {
    Users findByUsername(String name);
}

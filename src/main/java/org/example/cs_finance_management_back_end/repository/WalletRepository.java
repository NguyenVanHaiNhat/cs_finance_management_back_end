package org.example.cs_finance_management_back_end.repository;

import org.example.cs_finance_management_back_end.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<Wallet, Long> {
}

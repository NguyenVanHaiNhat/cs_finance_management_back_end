package org.example.cs_finance_management_back_end.repository;

import org.example.cs_finance_management_back_end.model.entity.Users;
import org.example.cs_finance_management_back_end.model.entity.Wallet;
import org.example.cs_finance_management_back_end.model.entity.Walletdetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletdetailRepository extends JpaRepository<Walletdetails, Long> {
    Iterable<Walletdetails> findAllByWallet(Wallet wallet);
    Iterable<Walletdetails> findAllByUsers( Users users);

    Page<Walletdetails> findAllByUsers(Pageable pageable, Users users);
}

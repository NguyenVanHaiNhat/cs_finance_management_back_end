package org.example.cs_finance_management_back_end.repository;

import org.example.cs_finance_management_back_end.model.entity.Users;
import org.example.cs_finance_management_back_end.model.entity.Wallet;
import org.example.cs_finance_management_back_end.model.entity.Walletdetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface WalletdetailRepository extends JpaRepository<Walletdetails, Long> {
    Iterable<Walletdetails> findAllByWallet(Wallet wallet);
    Iterable<Walletdetails> findAllByUsers( Users users);

    Page<Walletdetails> findAllByUsers(Pageable pageable, Users users);
    @Query(nativeQuery = true, value = "select sum(amount) as totalAmount from finance_management.walletdetails where user_id = ?1")
    double totalAmount(Long user_id);

    @Query(nativeQuery = true, value = "select sum(deposit_amount) as totalDeposit from finance_management.walletdetails where user_id = ?1")
    double totalDeposit(Long user_id);

    @Query(nativeQuery = true, value = "select count(id_wallet) as countWallet from finance_management.walletdetails where user_id = ?1")
    int countWallet(Long user_id);
}

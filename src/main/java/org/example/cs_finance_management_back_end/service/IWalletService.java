package org.example.cs_finance_management_back_end.service;


import org.example.cs_finance_management_back_end.model.entity.Users;
import org.example.cs_finance_management_back_end.model.entity.Wallet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IWalletService {
    Page<Wallet> findAllByUser(Pageable pageable, Users users) ;
    Optional<Wallet> findByIdUserAndWalletId(Long user_id, Long id_wallet);
    Wallet save(Wallet wallet);
    void remove(Long walletId);
}

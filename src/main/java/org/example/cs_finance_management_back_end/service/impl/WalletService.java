package org.example.cs_finance_management_back_end.service.impl;

import org.example.cs_finance_management_back_end.model.entity.Users;
import org.example.cs_finance_management_back_end.model.entity.Wallet;
import org.example.cs_finance_management_back_end.repository.WalletRepository;
import org.example.cs_finance_management_back_end.service.IWalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WalletService implements IWalletService {
    @Autowired
    private WalletRepository walletRepository;


    @Override
    public Page<Wallet> findAllByUser(Pageable pageable, Users users) {
        return walletRepository.findAllByUsers(pageable, users);
    }

    @Override
    public Optional<Wallet> findByIdUserAndWalletId(Long user_id, Long id_wallet) {
        return Optional.empty();
    }

    @Override
    public Wallet save(Wallet wallet) {
        return null;
    }

    @Override
    public void remove(Long walletId) {

    }
}

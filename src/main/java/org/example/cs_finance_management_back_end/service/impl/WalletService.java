package org.example.cs_finance_management_back_end.service.impl;

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
    public Page<Wallet> findAll(Pageable pageable) {
        return walletRepository.findAll(pageable);
    }

    @Override
    public Optional<Wallet> findById(Long id) {
        return walletRepository.findById(id);
    }

    @Override
    public Wallet save(Wallet wallet) {
        return walletRepository.save(wallet);
    }

    @Override
    public void remove(Long id) {
        walletRepository.deleteById(id);
    }
}

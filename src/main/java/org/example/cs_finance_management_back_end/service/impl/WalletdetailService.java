package org.example.cs_finance_management_back_end.service.impl;

import org.example.cs_finance_management_back_end.model.entity.Users;
import org.example.cs_finance_management_back_end.model.entity.Wallet;
import org.example.cs_finance_management_back_end.model.entity.Walletdetails;
import org.example.cs_finance_management_back_end.repository.WalletdetailRepository;
import org.example.cs_finance_management_back_end.service.IWalletdetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class WalletdetailService implements IWalletdetailService {
    @Autowired
    private WalletdetailRepository walletdetailRepository;
    @Override
    public Page<Walletdetails> findAll(Pageable pageable) {
        return walletdetailRepository.findAll(pageable);
    }

    @Override
    public Optional<Walletdetails> findById(Long id) {
        return walletdetailRepository.findById(id);
    }

    @Override
    public Walletdetails save(Walletdetails walletdetails) {
        return walletdetailRepository.save(walletdetails);
    }

    @Override
    public void remove(Long id) {
        walletdetailRepository.deleteById(id);
    }

    @Override
    public Page<Walletdetails> findAllByUser(Pageable pageable, Users users) {
        return walletdetailRepository.findAllByUsers(pageable, users);
    }

    @Override
    public Iterable<Walletdetails> findAllByUser(Users users) {
        return walletdetailRepository.findAllByUsers(users);
    }

    @Override
    public Iterable<Walletdetails> findAllByWallet(Wallet wallet) {
        return walletdetailRepository.findAllByWallet(wallet);
    }
}

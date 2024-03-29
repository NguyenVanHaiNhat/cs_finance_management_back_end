package org.example.cs_finance_management_back_end.service;

import org.example.cs_finance_management_back_end.model.entity.Users;
import org.example.cs_finance_management_back_end.model.entity.Wallet;
import org.example.cs_finance_management_back_end.model.entity.Walletdetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IWalletdetailService extends IGeneralService<Walletdetails> {
    Page<Walletdetails> findAllByUser(Pageable pageable, Users users);
    Iterable<Walletdetails> findAllByUser(Users users);
    Iterable<Walletdetails> findAllByWallet(Wallet wallet);
    Iterable<Walletdetails> findAll();
    double totalAmount(Long user_id);
    double totalDeposit(Long user_id);
    int countWallet(Long user_id);

}

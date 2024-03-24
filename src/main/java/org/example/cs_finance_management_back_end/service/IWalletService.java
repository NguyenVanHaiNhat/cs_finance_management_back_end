package org.example.cs_finance_management_back_end.service;


import org.example.cs_finance_management_back_end.model.entity.Users;
import org.example.cs_finance_management_back_end.model.entity.Wallet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface IWalletService extends IGeneralService<Wallet>{
    Page<Wallet> findAllByUser(Pageable pageable, Users users) ;
    Iterable<Wallet> findAllByUser(Users users) ;
}

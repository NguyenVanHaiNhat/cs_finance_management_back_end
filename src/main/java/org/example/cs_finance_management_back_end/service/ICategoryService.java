package org.example.cs_finance_management_back_end.service;

import org.example.cs_finance_management_back_end.model.entity.Category;
import org.example.cs_finance_management_back_end.model.entity.Users;
import org.example.cs_finance_management_back_end.model.entity.Walletdetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ICategoryService extends IGeneralService<Category> {
    Page<Category> findAllByUsers(Pageable pageable, Users users);
}

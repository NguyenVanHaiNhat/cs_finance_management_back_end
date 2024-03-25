package org.example.cs_finance_management_back_end.service.impl;

import org.apache.coyote.BadRequestException;


import org.example.cs_finance_management_back_end.model.entity.Users;
import org.example.cs_finance_management_back_end.repository.IUsersRepository;
import org.example.cs_finance_management_back_end.service.IUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserServiceImpl implements IUsersService {
    @Autowired
    private IUsersRepository iUsersRepository;

    @Override
    public Page<Users> findAll(Pageable pageable) {
        return iUsersRepository.findAll(pageable);
    }

    @Override
    public Optional<Users> findById(Long id) {
        return iUsersRepository.findById(id);
    }

    @Override
    public Users save(Users users) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        users.setPassword(bCryptPasswordEncoder.encode(users.getPassword()));
        return iUsersRepository.save(users);
    }

    @Override
    public void remove(Long id) {
        iUsersRepository.deleteById(id);
    }
}

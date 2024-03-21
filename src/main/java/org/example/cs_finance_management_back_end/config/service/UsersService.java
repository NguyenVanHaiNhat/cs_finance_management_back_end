package org.example.cs_finance_management_back_end.config.service;

import org.example.cs_finance_management_back_end.config.UsersPrinciple;
import org.example.cs_finance_management_back_end.model.entity.Users;
import org.example.cs_finance_management_back_end.repository.IUsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class UsersService implements UserDetailsService {
    @Autowired
    private IUsersRepository iUsersRepository;

    public Users findByUsername(String name) {
        return iUsersRepository.findByUsername(name);
    }

    public UserDetails loadUserByUsername(String username) {
        return UsersPrinciple.build(iUsersRepository.findByUsername(username));
    }
}

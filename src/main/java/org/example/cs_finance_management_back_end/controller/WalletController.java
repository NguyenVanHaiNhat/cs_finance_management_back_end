package org.example.cs_finance_management_back_end.controller;

import org.example.cs_finance_management_back_end.config.service.JwtService;
import org.example.cs_finance_management_back_end.model.entity.Users;
import org.example.cs_finance_management_back_end.model.entity.Wallet;
import org.example.cs_finance_management_back_end.repository.IUsersRepository;
import org.example.cs_finance_management_back_end.service.impl.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/wallets")
public class WalletController {
    @Autowired
    private WalletService walletService;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private IUsersRepository iUsersRepository;
    @GetMapping
    public ResponseEntity<Page<Wallet>> getAllWallet(Pageable pageable, @RequestHeader("Authorization") String tokenHeader) {
        String token = tokenHeader.substring(7); // Loại bỏ phần "Bearer "
        String users = jwtService.getUsernameFromJwtToken(token);
        Users user = iUsersRepository.findByUsername(users);
        Page<Wallet> wallets = walletService.findAllByUser(pageable, user);
        return new ResponseEntity<>(wallets, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Wallet> findById(@PathVariable Long id){
        Optional<Wallet> wallet = walletService.findById(id);
        return new ResponseEntity<>(wallet.get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Wallet> save(@RequestBody Wallet wallet, @RequestHeader("Authorization") String tokenHeader) {
        String token = tokenHeader.substring(7);
        String users = jwtService.getUsernameFromJwtToken(token);
        wallet.setUsers(iUsersRepository.findByUsername(users));
        Wallet savedWallet = walletService.save(wallet);
        return new ResponseEntity<>(savedWallet, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Wallet> update(@PathVariable Long id, @RequestBody Wallet wallet, @RequestHeader("Authorization") String tokenHeader){
        String token = tokenHeader.substring(7);
        String users = jwtService.getUsernameFromJwtToken(token);
        if (!walletService.findById(id).isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        wallet.setId(id);
        wallet.setUsers(iUsersRepository.findByUsername(users));
        Wallet updateWallet = walletService.save(wallet);
        return new ResponseEntity<>(updateWallet, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        walletService.remove(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

}

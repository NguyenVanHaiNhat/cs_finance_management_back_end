package org.example.cs_finance_management_back_end.controller;

import org.example.cs_finance_management_back_end.model.Wallet;
import org.example.cs_finance_management_back_end.service.impl.WalletService;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
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
    @GetMapping
    public ResponseEntity<Page<Wallet>> getAllWallet(Pageable pageable) {
        Page<Wallet> wallets = walletService.findAll(pageable);
        return new ResponseEntity<>(wallets, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Wallet> getWalletById(@PathVariable Long id) {
        Optional<Wallet> walletOptional = walletService.findById(id);
        return walletOptional.map(wallet -> new ResponseEntity<>(wallet, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Wallet> createWallet(@RequestBody Wallet wallet) {
        Wallet saveWallet = walletService.save(wallet);
        return new ResponseEntity<>(saveWallet, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Wallet> updateWallet(@PathVariable Long id, @RequestBody Wallet wallet) {
        if (!walletService.findById(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        wallet.setId(id);
        Wallet updatedWallet = walletService.save(wallet);
        return new ResponseEntity<>(updatedWallet, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteWallet(@PathVariable Long id) {
        if (!walletService.findById(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        walletService.remove(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

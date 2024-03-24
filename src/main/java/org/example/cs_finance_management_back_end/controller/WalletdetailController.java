package org.example.cs_finance_management_back_end.controller;

import java.io.File;
import java.io.IOException;

import org.example.cs_finance_management_back_end.config.service.JwtService;
import org.example.cs_finance_management_back_end.model.DTO.WalletdetailsForm;
import org.example.cs_finance_management_back_end.model.entity.Users;
import org.example.cs_finance_management_back_end.model.entity.Wallet;
import org.example.cs_finance_management_back_end.model.entity.Walletdetails;
import org.example.cs_finance_management_back_end.repository.IUsersRepository;
import org.example.cs_finance_management_back_end.repository.WalletdetailRepository;
import org.example.cs_finance_management_back_end.service.IWalletService;
import org.example.cs_finance_management_back_end.service.impl.WalletdetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.nio.file.StandardCopyOption;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/walletdetails")
@PropertySource("classpath:upload_file.properties")
public class WalletdetailController {
    @Autowired
    private WalletdetailService walletdetailService;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private IUsersRepository iUsersRepository;
    @Value("${upload}")
    private String upload;
    @GetMapping
    public ResponseEntity<Page<Walletdetails>> getAllWalletdetails(Pageable pageable, @RequestHeader("Authorization") String tokenHeader) {
        String token = tokenHeader.substring(7); // Loại bỏ phần "Bearer "
        String users = jwtService.getUsernameFromJwtToken(token);
        Users user = iUsersRepository.findByUsername(users);
        Page<Walletdetails> walletdetails = walletdetailService.findAllByUser(pageable,user);
        return new ResponseEntity<>(walletdetails, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Walletdetails> getWalletdetailsById(@PathVariable Long id) {
        Optional<Walletdetails> walletdetailsOptional = walletdetailService.findById(id);
        return walletdetailsOptional.map(walletdetails -> new ResponseEntity<>(walletdetails, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Walletdetails> createWalletdetails(@RequestBody WalletdetailsForm walletdetailsForm, @RequestHeader("Authorization") String tokenHeader) {
        MultipartFile file = walletdetailsForm.getIcon();
        String fileName = file.getOriginalFilename();
        System.out.println(fileName);
        try {
            FileCopyUtils.copy(file.getBytes(), new File(upload+fileName));
        } catch (IOException e){
            e.printStackTrace();
        }
        Walletdetails walletdetails = new Walletdetails();
        String token = tokenHeader.substring(7);
        String users = jwtService.getUsernameFromJwtToken(token);
        walletdetails.setIcon(fileName);
        walletdetails.setDeposit_amount(walletdetailsForm.getDeposit_amount());
        walletdetails.setAmount(walletdetailsForm.getAmount());
        walletdetails.setNote(walletdetailsForm.getNote());
        walletdetails.setWallet(walletdetailsForm.getWallet());
        walletdetails.setUsers(iUsersRepository.findByUsername(users));
        walletdetailService.save(walletdetails);
        return new ResponseEntity<>(walletdetails, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Walletdetails> updateWalletdetails(@PathVariable Long id, @RequestBody Walletdetails walletdetails, @RequestHeader("Authorization") String tokenHeader) {
        String token = tokenHeader.substring(7);
        String users = jwtService.getUsernameFromJwtToken(token);
        if (!walletdetailService.findById(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        walletdetails.setId(id);
        walletdetails.setUsers(iUsersRepository.findByUsername(users));
        Walletdetails updatedWalletDetails = walletdetailService.save(walletdetails);
        return new ResponseEntity<>(updatedWalletDetails, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteWalletdetails(@PathVariable Long id) {
        if (!walletdetailService.findById(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        walletdetailService.remove(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

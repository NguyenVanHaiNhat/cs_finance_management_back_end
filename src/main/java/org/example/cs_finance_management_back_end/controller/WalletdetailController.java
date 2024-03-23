package org.example.cs_finance_management_back_end.controller;

import java.io.IOException;

import org.example.cs_finance_management_back_end.config.service.JwtService;
import org.example.cs_finance_management_back_end.model.entity.Users;
import org.example.cs_finance_management_back_end.model.entity.Wallet;
import org.example.cs_finance_management_back_end.model.entity.Walletdetails;
import org.example.cs_finance_management_back_end.repository.IUsersRepository;
import org.example.cs_finance_management_back_end.repository.WalletdetailRepository;
import org.example.cs_finance_management_back_end.service.impl.WalletdetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
public class WalletdetailController {
    @Autowired
    private WalletdetailService walletdetailService;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private IUsersRepository iUsersRepository;

    @PostMapping("/upload-icon")
    public ResponseEntity<String> uploadIcon(@RequestParam("file") MultipartFile file) {
        try {
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            String uploadDir = "E:\\Bootcamp_Java_Fullstack\\C1023H1-JV101-NguyenVanHaiNhat\\Module_4\\case_study\\back_end\\cs_finance_management_back_end\\img";
            Path uploadPath = Paths.get(uploadDir);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }
            try (InputStream inputStream = file.getInputStream()) {
                Path filePath = uploadPath.resolve(fileName);
                Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
            }

            String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/uploads/")
                    .path(fileName)
                    .toUriString();

            return ResponseEntity.ok(fileDownloadUri);
        } catch (IOException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Could not upload file: " + file.getOriginalFilename());
        }
    }
    @GetMapping
    public ResponseEntity<Page<Walletdetails>> getAllWalletdetails(Pageable pageable, @RequestHeader("Authorization") String tokenHeader) {
        String token = tokenHeader.substring(7); // Loại bỏ phần "Bearer "
        String users = jwtService.getUsernameFromJwtToken(token);
        Users user = iUsersRepository.findByUsername(users);
        Page<Walletdetails> walletdetails = walletdetailService.findAllByUser(pageable,user);
        return new ResponseEntity<>(walletdetails, HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<Iterable<Walletdetails>> getAllWalletdetail(@RequestHeader("Authorization") String tokenHeader) {
        String token = tokenHeader.substring(7); // Loại bỏ phần "Bearer "
        String users = jwtService.getUsernameFromJwtToken(token);
        Users user = iUsersRepository.findByUsername(users);
        Iterable<Walletdetails> walletdetails = walletdetailService.findAll();
        return new ResponseEntity<>(walletdetails, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Walletdetails> getWalletdetailsById(@PathVariable Long id) {
        Optional<Walletdetails> walletdetailsOptional = walletdetailService.findById(id);
        return walletdetailsOptional.map(walletdetails -> new ResponseEntity<>(walletdetails, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Walletdetails> createWalletdetails(@RequestBody Walletdetails walletdetails) {
        Walletdetails saveWalletDetails = walletdetailService.save(walletdetails);
        return new ResponseEntity<>(saveWalletDetails, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Walletdetails> updateWalletdetails(@PathVariable Long id, @RequestBody Walletdetails walletdetails) {
        if (!walletdetailService.findById(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        walletdetails.setId(id);
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

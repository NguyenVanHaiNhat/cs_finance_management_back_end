package org.example.cs_finance_management_back_end.controller;

import org.example.cs_finance_management_back_end.config.service.JwtService;
import org.example.cs_finance_management_back_end.model.entity.Users;
import org.example.cs_finance_management_back_end.repository.IUsersRepository;
import org.example.cs_finance_management_back_end.service.IUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/users")
public class UsersController {
    @Autowired
    private IUsersService iUsersService;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private IUsersRepository iUsersRepository;


    @GetMapping
    public ResponseEntity<Users> getAllUser(@RequestHeader("Authorization") String tokenHeader) {
        String token = tokenHeader.substring(7); // Loại bỏ phần "Bearer "
        String user = jwtService.getUsernameFromJwtToken(token);
        Users users = iUsersRepository.findByUsername(user);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Users> getUsersById(@PathVariable Long id) {
        Optional<Users> usersOptional = iUsersService.findById(id);
        return usersOptional.map(users -> new ResponseEntity<>(users, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/create")
    public ResponseEntity<Users> createUser(@RequestBody Users users){
        iUsersService.save(users);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Users> updateUsers(@PathVariable Long id, @RequestBody Users users) {
        if (!iUsersService.findById(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        users.setId(id);
        Users users1 = iUsersService.save(users);
        return new ResponseEntity<>(users1, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteWallet(@PathVariable Long id) {
        if (!iUsersService.findById(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        iUsersService.remove(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

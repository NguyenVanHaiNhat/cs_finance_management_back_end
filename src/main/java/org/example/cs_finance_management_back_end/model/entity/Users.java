package org.example.cs_finance_management_back_end.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
public class Users {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String first_name;
    private String last_name;
    private String gender;
    private String email;
    private int age;
    private int phone;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Roles> roles;


}

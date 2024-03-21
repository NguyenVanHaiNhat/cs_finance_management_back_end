package org.example.cs_finance_management_back_end.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "wallet")
public class Wallet {
    @Id
    @GeneratedValue
    private Long id;
    private String name_wallet;
    private String note;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users users;
}

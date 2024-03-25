package org.example.cs_finance_management_back_end.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "walletdetails")
public class Walletdetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double deposit_amount;
    private double amount;
    private String note;
    @ManyToOne
    @JoinColumn(name = "id_wallet")
    private Wallet wallet;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users users;
}

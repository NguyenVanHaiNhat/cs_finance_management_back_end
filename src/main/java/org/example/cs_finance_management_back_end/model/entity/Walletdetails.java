package org.example.cs_finance_management_back_end.model;

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
    private String icon;
    private double deposit_amount;
    private double amount;
    private String note;
    @ManyToOne
    @JoinColumn(name = "id_wallet")
    private Wallet id_wallet;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user_id;
}

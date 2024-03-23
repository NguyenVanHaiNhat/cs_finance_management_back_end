package org.example.cs_finance_management_back_end.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "expense")
@NoArgsConstructor
@Data
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double amount;
    private String note;
    private LocalDate time_now;
    @ManyToOne
    @JoinColumn(name = "id_walletdetails")
    private Walletdetails walletdetails;

    @ManyToOne
    @JoinColumn(name = "id_category")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users users;

}

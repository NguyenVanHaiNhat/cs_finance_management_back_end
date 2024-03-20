package org.example.cs_finance_management_back_end.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private String time_now;
    @ManyToOne
    @JoinColumn(name = "id_walletDetails")
    private Walletdetails id_walletDetails;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category id_category;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user_id;

}

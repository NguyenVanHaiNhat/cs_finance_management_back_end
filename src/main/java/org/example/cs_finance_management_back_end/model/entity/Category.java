package org.example.cs_finance_management_back_end.model.entity;

import jakarta.persistence.*;

import lombok.Data;




@Entity
@Data

@Table(name = "category")

public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;




    private String name_category;
    private String note;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users users;
}



package org.example.cs_finance_management_back_end.model.entity;

import jakarta.persistence.*;

import lombok.Data;

import java.util.List;


@Entity
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String note;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user_id;


}



package org.example.cs_finance_management_back_end.model.DTO;

import org.example.cs_finance_management_back_end.model.entity.Wallet;

public class WalletdetailsForm {
    private Long id;
    private double deposit_amount;
    private double amount;
    private String note;
    private Wallet wallet;

    public WalletdetailsForm(Long id, double deposit_amount, double amount, String note, Wallet wallet) {
        this.id = id;
        this.deposit_amount = deposit_amount;
        this.amount = amount;
        this.note = note;
        this.wallet = wallet;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public double getDeposit_amount() {
        return deposit_amount;
    }

    public void setDeposit_amount(double deposit_amount) {
        this.deposit_amount = deposit_amount;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }
}

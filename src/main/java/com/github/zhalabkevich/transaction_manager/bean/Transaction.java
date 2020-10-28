package com.github.zhalabkevich.transaction_manager.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class Transaction implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private LocalDateTime date;
    private BigDecimal amount;
    private String merchant;
    private Type type;
    private Transaction relatedTransaction;

    public Transaction() {
    }

    public Transaction(String id) {
        this.id = id;
    }

    public Transaction(String id, LocalDateTime date, BigDecimal amount, String merchant, Type type) {
        this.id = id;
        this.date = date;
        this.amount = amount;
        this.merchant = merchant;
        this.type = type;
    }

    public Transaction(String id, LocalDateTime date, BigDecimal amount, String merchant, Type type, Transaction relatedTransaction) {
        this.id = id;
        this.date = date;
        this.amount = amount;
        this.merchant = merchant;
        this.type = type;
        this.relatedTransaction = relatedTransaction;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getMerchant() {
        return merchant;
    }

    public void setMerchant(String merchant) {
        this.merchant = merchant;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Transaction getRelatedTransaction() {
        return relatedTransaction;
    }

    public void setRelatedTransaction(Transaction relatedTransaction) {
        this.relatedTransaction = relatedTransaction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return getId().equals(that.getId()) &&
                getDate().equals(that.getDate()) &&
                getAmount().equals(that.getAmount()) &&
                getMerchant().equals(that.getMerchant()) &&
                getType() == that.getType();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDate(), getAmount(), getMerchant(), getType(), getRelatedTransaction());
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id='" + id + '\'' +
                ", date=" + date +
                ", amount=" + amount +
                ", merchant='" + merchant + '\'' +
                ", type=" + type +
                ", relatedTransaction=" + relatedTransaction +
                '}';
    }
}

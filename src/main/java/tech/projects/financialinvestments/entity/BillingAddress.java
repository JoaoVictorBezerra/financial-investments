package tech.projects.financialinvestments.entity;

import jakarta.persistence.*;
import tech.projects.financialinvestments.entity.account.Account;

@Entity
@Table(name = "billing_address")
public class BillingAddress {
    @Id
    @Column(name = "account_id")
    private String accountId;
    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    @JoinColumn(name = "account_id")
    private Account account;
    @Column(nullable = false)
    private String street;
    @Column(nullable = false)
    private Integer number;

    public BillingAddress() {
    }

    public BillingAddress(String accountId, Account account, String street, Integer number) {
        this.accountId = accountId;
        this.account = account;
        this.street = street;
        this.number = number;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}


package tech.projects.financialinvestments.entity.account;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class AccountStockID {
    @Column(name = "account_id")
    private String accountId;
    @Column(name = "stock_id")
    private String stockId;

    public AccountStockID() {
    }

    public AccountStockID(String accountId, String stockId) {
        this.accountId = accountId;
        this.stockId = stockId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getStockId() {
        return stockId;
    }

    public void setStockId(String stockId) {
        this.stockId = stockId;
    }
}

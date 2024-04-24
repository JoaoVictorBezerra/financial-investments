package tech.projects.financialinvestments.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import tech.projects.financialinvestments.client.BrapiClient;
import tech.projects.financialinvestments.client.dto.BrapiResponseDTO;
import tech.projects.financialinvestments.dto.AccountStockResponseDTO;
import tech.projects.financialinvestments.entity.Stock;
import tech.projects.financialinvestments.entity.account.Account;
import tech.projects.financialinvestments.entity.account.AccountStock;
import tech.projects.financialinvestments.entity.account.AccountStockID;
import tech.projects.financialinvestments.repository.AccountStockRepository;

import java.util.List;

@Service
public class AccountStockService {

    @Value("#{environment.token}")
    private String token;
    private final AccountStockRepository accountStockRepository;
    private final BrapiClient brapiClient;

    public AccountStockService(AccountStockRepository accountStockRepository, BrapiClient brapiClient) {
        this.accountStockRepository = accountStockRepository;
        this.brapiClient = brapiClient;
    }

    private AccountStockID createEmbedId(String accountId, String stockId) {
        return new AccountStockID(accountId, stockId);
    }

    public void createAccountStock(Account account, Stock stock, Integer quantity) {
        AccountStockID id = createEmbedId(account.getId(), stock.getId());
        AccountStock accountStock = new AccountStock(id, account, stock, quantity);
        accountStockRepository.save(accountStock);
    }

    public List<AccountStockResponseDTO> getStocksByAccount(Account account) {
        return account.getAccountStocks()
                .stream()
                .map(accountStock -> new AccountStockResponseDTO(accountStock.getStock().getId(),
                        accountStock.getQuantity(),
                        getTotalValue(accountStock.getQuantity(), accountStock.getId().getStockId())))
                .toList();
    }

    private double getTotalValue(Integer quantity, String stockId) {
        BrapiResponseDTO response = brapiClient.getQuote(token, stockId);
        double stockValue = response.results().stream().findFirst().get().regularMarketPrice();
        return quantity * stockValue;
    }
}

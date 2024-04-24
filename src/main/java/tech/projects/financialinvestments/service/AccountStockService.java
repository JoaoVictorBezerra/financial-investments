package tech.projects.financialinvestments.service;

import org.springframework.stereotype.Service;
import tech.projects.financialinvestments.dto.AccountStockResponseDTO;
import tech.projects.financialinvestments.entity.Stock;
import tech.projects.financialinvestments.entity.account.Account;
import tech.projects.financialinvestments.entity.account.AccountStock;
import tech.projects.financialinvestments.entity.account.AccountStockID;
import tech.projects.financialinvestments.repository.AccountStockRepository;

import java.util.List;

@Service
public class AccountStockService {
    private final AccountStockRepository accountStockRepository;

    public AccountStockService(AccountStockRepository accountStockRepository) {
        this.accountStockRepository = accountStockRepository;
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
                .map(accountStock -> new AccountStockResponseDTO(accountStock.getStock().getId(), accountStock.getQuantity(), 0.0))
                .toList();
    }
}

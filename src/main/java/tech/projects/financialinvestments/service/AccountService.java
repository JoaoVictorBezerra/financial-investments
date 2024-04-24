package tech.projects.financialinvestments.service;

import org.springframework.stereotype.Service;
import tech.projects.financialinvestments.dto.AccountStockResponseDTO;
import tech.projects.financialinvestments.dto.AssociateAccountStockDTO;
import tech.projects.financialinvestments.entity.Stock;
import tech.projects.financialinvestments.entity.account.Account;
import tech.projects.financialinvestments.repository.AccountRepository;

import java.util.List;

@Service
public class AccountService {
    private final AccountRepository accountRepository;
    private final StockService stockService;
    private final AccountStockService accountStockService;

    public AccountService(AccountRepository accountRepository, StockService stockService, AccountStockService accountStockService) {
        this.accountRepository = accountRepository;
        this.stockService = stockService;
        this.accountStockService = accountStockService;
    }

    public Account findAccountById(String accountId) {
        return accountRepository.findById(accountId).orElseThrow(() -> new RuntimeException("Account not found."));
    }

    public Account createAccount(Account account) {
        accountRepository.save(account);
        return account;
    }

    public void associateStock(String accountId, AssociateAccountStockDTO associateAccountStockDTO) {
        Account account = findAccountById(accountId);
        Stock stock = stockService.findStockById(associateAccountStockDTO.stockId());
        accountStockService.createAccountStock(account, stock, associateAccountStockDTO.quantity());
    }

    public List<AccountStockResponseDTO> getStockListByAccountId(String accountId) {
        Account account = findAccountById(accountId);
        return accountStockService.getStocksByAccount(account);
    }
}

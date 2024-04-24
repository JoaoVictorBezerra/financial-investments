package tech.projects.financialinvestments.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.projects.financialinvestments.dto.AccountStockResponseDTO;
import tech.projects.financialinvestments.dto.AssociateAccountStockDTO;
import tech.projects.financialinvestments.service.AccountService;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("{accountId}/stock")
    public ResponseEntity<Void> associateStocks(@PathVariable("accountId") String accountId, @RequestBody AssociateAccountStockDTO associateAccountStockDTO) {
        accountService.associateStock(accountId, associateAccountStockDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping("{accountId}/stock")
    public ResponseEntity<List<AccountStockResponseDTO>> getAccountStocksByAccountId(@PathVariable("accountId") String accountId) {
        List<AccountStockResponseDTO> stockList = accountService.getStockListByAccountId(accountId);
        return ResponseEntity.ok().body(stockList);
    }
}

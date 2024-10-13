package retail_banking_deposit.com.wallet.service;

import org.springframework.beans.factory.annotation.Autowired;
import retail_banking_deposit.com.wallet.entity.AccountBalance;
import retail_banking_deposit.com.wallet.repository.AccountBalanceRepository;

import java.util.List;

public class AccountBalanceService {

    @Autowired
    private AccountBalanceRepository accountBalanceRepository;

    public List<AccountBalance> findAllAccountBalance() {
        return accountBalanceRepository.findAll();
    }
}

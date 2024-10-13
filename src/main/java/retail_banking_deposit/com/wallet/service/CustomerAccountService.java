package retail_banking_deposit.com.wallet.service;

import retail_banking_deposit.com.wallet.entity.CustomerAccount;
import retail_banking_deposit.com.wallet.repository.CustomerAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerAccountService {

    @Autowired
    private CustomerAccountRepository repository;

    public CustomerAccount createAccount(CustomerAccount account) {
        return repository.save(account);
    }
}

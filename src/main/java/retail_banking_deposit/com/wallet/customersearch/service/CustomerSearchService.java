package retail_banking_deposit.com.wallet.customersearch.service;

import retail_banking_deposit.com.wallet.customersearch.entity.CustomerSearch;
import retail_banking_deposit.com.wallet.customersearch.repository.CustomerSearchRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerSearchService {

    @Autowired
    private CustomerSearchRepository customerSearchRepository;

    public CustomerSearch createAccountSearch(CustomerSearch customerSearch) {
        return customerSearchRepository.save(customerSearch);
    }
}
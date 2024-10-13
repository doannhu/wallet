package retail_banking_deposit.com.wallet.customersearch.repository;

import retail_banking_deposit.com.wallet.customersearch.entity.CustomerSearch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerSearchRepository extends JpaRepository<CustomerSearch, String> {
}

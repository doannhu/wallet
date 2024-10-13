package retail_banking_deposit.com.wallet.repository;

import retail_banking_deposit.com.wallet.entity.CustomerAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerAccountRepository extends JpaRepository<CustomerAccount, Long> {
}

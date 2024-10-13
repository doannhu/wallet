package retail_banking_deposit.com.wallet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import retail_banking_deposit.com.wallet.entity.AccountBalance;

import java.util.Optional;

public interface AccountBalanceRepository extends JpaRepository<AccountBalance, Long> {

}

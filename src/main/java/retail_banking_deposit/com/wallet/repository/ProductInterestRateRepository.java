package retail_banking_deposit.com.wallet.repository;

import org.springframework.data.jpa.repository.Query;
import retail_banking_deposit.com.wallet.entity.ProductInterestRate;
import org.springframework.data.jpa.repository.JpaRepository;
import retail_banking_deposit.com.wallet.product.DepositProduct;

import java.time.LocalDate;
import java.util.Optional;

public interface ProductInterestRateRepository extends JpaRepository<ProductInterestRate, Long> {

    // Query to find interest rate by product and date
    @Query("SELECT p FROM ProductInterestRate p WHERE p.product = :product AND p.updatedDate = :date")
    Optional<ProductInterestRate> findByProductAndDate(DepositProduct product, LocalDate date);

    // Query to find the latest interest rate for a specific product
    // LIMIT 1 is implied by returning an Optional<ProductInterestRate>
    @Query("SELECT p FROM ProductInterestRate p WHERE p.product = :product ORDER BY p.updatedDate DESC")
    Optional<ProductInterestRate> findLatestByProduct(DepositProduct product);
}
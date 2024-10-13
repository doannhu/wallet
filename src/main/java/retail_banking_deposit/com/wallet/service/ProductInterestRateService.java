package retail_banking_deposit.com.wallet.service;

import retail_banking_deposit.com.wallet.entity.ProductInterestRate;
import retail_banking_deposit.com.wallet.product.DepositProduct;
import retail_banking_deposit.com.wallet.repository.ProductInterestRateRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ProductInterestRateService {

    @Autowired
    private ProductInterestRateRepository productInterestRateRepository;

    public ProductInterestRate createInterestRate(ProductInterestRate productInterestRate) {
        return productInterestRateRepository.save(productInterestRate);
    }

    public List<ProductInterestRate> findAllProductInterestRate() {
        return productInterestRateRepository.findAll();
    }

    // Service method to get the interest rate based on product and date
    public Optional<BigDecimal> getInterestRate(DepositProduct product, LocalDate date) {
        Optional<ProductInterestRate> rate = productInterestRateRepository.findByProductAndDate(product, date);
        return rate.map(ProductInterestRate::getInterestRate); // Return the interest rate if found
    }

    // Optional method to only return the interest rate (BigDecimal) if needed
    public Optional<ProductInterestRate> getLatestInterestRateValue(DepositProduct product) {
        Optional<ProductInterestRate> latestRate = productInterestRateRepository.findLatestByProduct(product);
        return latestRate;  // Map to interest rate if present
    }
}
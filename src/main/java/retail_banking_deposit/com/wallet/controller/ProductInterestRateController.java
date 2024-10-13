package retail_banking_deposit.com.wallet.controller;

import retail_banking_deposit.com.wallet.entity.ProductInterestRate;
import retail_banking_deposit.com.wallet.product.DepositProduct;
import retail_banking_deposit.com.wallet.service.ProductInterestRateService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/interest-rate")
public class ProductInterestRateController {

    @Autowired
    private ProductInterestRateService productInterestRateService;

    // Create a new product interest rate
    @PostMapping("/create")
    public ResponseEntity<ProductInterestRate> createProductInterestRate(@RequestBody ProductInterestRate productInterestRate) {
        productInterestRate.setUpdatedDate(LocalDate.now()); // Set the current date as updatedDate
        ProductInterestRate savedProductInterestRate = productInterestRateService.createInterestRate(productInterestRate);
        return ResponseEntity.ok(savedProductInterestRate);
    }

    // Get all product interest rates
    @GetMapping("/all")
    public ResponseEntity<List<ProductInterestRate>> getAllProductInterestRates() {
        List<ProductInterestRate> rates = productInterestRateService.findAllProductInterestRate();
        return ResponseEntity.ok(rates);
    }

    // Endpoint to get the interest rate by product and date
    @GetMapping("/get")
    public ResponseEntity<String> getInterestRate(@RequestParam DepositProduct product, @RequestParam String date) {
        LocalDate parsedDate = LocalDate.parse(date); // Parse the date from string input
        Optional<BigDecimal> interestRate = productInterestRateService.getInterestRate(product, parsedDate);
        
        if (interestRate.isPresent()) {
            return ResponseEntity.ok("Interest Rate: " + interestRate.get().toString());
        } else {
            return ResponseEntity.status(404).body("Interest rate not found for the given product and date.");
        }
    }

    // Endpoint to get the latest interest rate by product
    @GetMapping("/latest")
    public ResponseEntity<String> getLatestInterestRate(@RequestParam DepositProduct product) {
        Optional<ProductInterestRate> latestRate = productInterestRateService.getLatestInterestRateValue(product);
        
        if (latestRate.isPresent()) {
            ProductInterestRate rate = latestRate.get();
            return ResponseEntity.ok("Product: " + product + ", Interest Rate: " + rate.getInterestRate() +
                    ", Updated Date: " + rate.getUpdatedDate());
        } else {
            return ResponseEntity.status(404).body("No interest rate found for the given product.");
        }
    }
}

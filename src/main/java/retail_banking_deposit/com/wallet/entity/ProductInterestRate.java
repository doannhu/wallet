package retail_banking_deposit.com.wallet.entity;

import retail_banking_deposit.com.wallet.product.DepositProduct;


import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "product_interest_rate")
public class ProductInterestRate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DepositProduct product;

    @Column(name = "updated_date", nullable = false)
    private LocalDate updatedDate;

    @Column(name = "interest_rate", nullable = false)
    private BigDecimal interestRate;

    public ProductInterestRate(DepositProduct product, LocalDate updatedDate, BigDecimal interestRate) {
        this.product = product;
        this.updatedDate = updatedDate;
        this.interestRate = interestRate;
    }

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DepositProduct getProduct() {
        return product;
    }

    public void setProduct(DepositProduct product) {
        this.product = product;
    }

    public LocalDate getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }
}

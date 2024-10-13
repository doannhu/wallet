package retail_banking_deposit.com.wallet.customersearch.entity;

import jakarta.persistence.*;

@Entity
public class CustomerSearch {

    @Id
    private String customerNumber;  // Customer number as the key

    private String customerName;    // Customer name (first and last)

    // Constructors
    public CustomerSearch() {}

    public CustomerSearch(String customerNumber, String customerName) {
        this.customerNumber = customerNumber;
        this.customerName = customerName;
    }

    // Getters and Setters
    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}

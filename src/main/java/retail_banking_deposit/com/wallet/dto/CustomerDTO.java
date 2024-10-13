package retail_banking_deposit.com.wallet.dto;

public class CustomerDTO {
    private String firstName;
    private String lastName;
    private String customerNumber;

    // Constructors
    public CustomerDTO() {}

    public CustomerDTO(String firstName, String lastName, String customerNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.customerNumber = customerNumber;
    }

    // Getters and Setters
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }
}
